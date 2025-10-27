package com.example.mycollections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class MyLinkedList<E> implements MyList<E> {

    private MyNode<E> first;
    private MyNode<E> last;
    private int size;

    public MyLinkedList() {}

    public MyLinkedList(Collection<? extends E> collection) {
        addAll(collection);
    }

    public void add(E e) {
        MyNode<E> newMyNode;
        if (last == null) {
            newMyNode = new MyNode<>(null, e, null);
            first = newMyNode;
        } else {
            newMyNode = new MyNode<>(last, e, null);
            last.next = newMyNode;
        }
        last = newMyNode;
        size++;
    }

    public void add(int index, E e) {
        Objects.checkIndex(index, size + 1);

        if (size == 0) {
            add(e);
            return;
        }

        if (index == 0) {
            MyNode<E> newMyNode = new MyNode<>(null, e, first);
            first.prev = newMyNode;
            first = newMyNode;
            size++;
            return;
        }

        if (index == size) {
            add(e);
            return;
        }

        MyNode<E> currentMyNode;
        if (index < size >> 1) {
            currentMyNode = getMyNode(index - 1);
        } else {
            currentMyNode = getMyNodeReversed(index - 1);
        }
        MyNode<E> newMyNode = new MyNode<>(currentMyNode,
                                           e, currentMyNode.next);
        currentMyNode.next = newMyNode;
        currentMyNode = newMyNode.next;
        currentMyNode.prev = newMyNode;
        size++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);

        if (index == 0) {
            return first.item;
        }

        if (index == size - 1) {
            return last.item;
        }

        if (index < size >> 1) {
            return getMyNode(index).item;
        } else {
            return getMyNodeReversed(index).item;
        }
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);

        if (size == 1) {
            E removedElement = first.item;
            first = null;
            last = null;

            size--;
            return removedElement;
        }

        if (index == 0) {
            E removedElement = first.item;
            first = first.next;
            first.prev = null;

            size--;
            return removedElement;
        }

        if (index == size - 1) {
            E removedElement = last.item;
            last = last.prev;
            last.next = null;

            size--;
            return removedElement;
        }

        E removedElement;
        MyNode<E> currentMyNode;
        if (index < size >> 1) {
            currentMyNode = getMyNode(index - 1);
        } else {
            currentMyNode = getMyNodeReversed(index - 1);
        }
        removedElement = currentMyNode.next.item;
        currentMyNode.next = currentMyNode.next.next;
        currentMyNode.next.prev = currentMyNode;
        size--;
        return removedElement;
    }

    public void addAll(Collection<? extends E> collection) {
        for (E e : collection) {
            add(e);
        }
    }

    public void set(int index, E e) {
        Objects.checkIndex(index, size);

        if (index == 0) {
            first.item = e;
            return;
        }

        if (index == size - 1) {
            last.item = e;
            return;
        }

        if (index < size >> 1) {
            getMyNode(index).item = e;
        } else {
            getMyNodeReversed(index).item = e;
        }
    }

    public int length() {
        return size;
    }

    private MyNode<E> getMyNode(int index) {
        MyNode<E> currentMyNode = first;
        for (int i = 1; i <= index; i++) {
            currentMyNode = currentMyNode.next;
        }
        return currentMyNode;
    }

    private MyNode<E> getMyNodeReversed(int index) {
        MyNode<E> currentMyNode = last;
        for (int i = size - 2; i >= index; i--) {
            currentMyNode = currentMyNode.prev;
        }
        return currentMyNode;
    }

    @Override
    public Iterator<E> iterator() {
        return new myLinkedListIterator();
    }


    private static class MyNode<E> {
        MyNode<E> prev;
        E item;
        MyNode<E> next;

        MyNode(MyNode<E> prev, E item, MyNode<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

    }

    private class myLinkedListIterator implements Iterator<E> {

        private MyNode<E> currentMyNode;
        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (currentIndex == 0) {
                currentMyNode = first;
            } else {
                currentMyNode = currentMyNode.next;
            }
            currentIndex++;
            return currentMyNode.item;
        }

    }

}
