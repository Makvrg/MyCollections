package com.example.mycollections;

import java.util.*;

public class MyLinkedList<E> implements MyList<E> {

    private MyNode<E> first;
    private MyNode<E> last;
    private int size;
    private int modCount;

    public MyLinkedList() {}

    public MyLinkedList(Collection<? extends E> collection) {
        addAll(collection);
    }

    @Override
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
        modCount++;
    }

    @Override
    public void add(int index, E e) {
        Objects.checkIndex(index, size + 1);

        if (index == size) {
            add(e);
            return;
        }

        if (index == 0) {
            MyNode<E> newMyNode = new MyNode<>(null, e, first);
            first.prev = newMyNode;
            first = newMyNode;
            size++;
            modCount++;
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
        modCount++;
    }

    @Override
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

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);

        if (size == 1) {
            E removedElement = first.item;
            first = null;
            last = null;

            size--;
            modCount++;
            return removedElement;
        }

        if (index == 0) {
            E removedElement = first.item;
            first = first.next;
            first.prev = null;

            size--;
            modCount++;
            return removedElement;
        }

        if (index == size - 1) {
            E removedElement = last.item;
            last = last.prev;
            last.next = null;

            size--;
            modCount++;
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
        modCount++;
        return removedElement;
    }

    @Override
    public void addAll(Collection<? extends E> collection) {
        for (E e : collection) {
            add(e);
        }
    }

    @Override
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

    @Override
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


    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        MyNode<E> currentMyNode;
        int cursor;
        MyNode<E> lastReturned;
        int indexOfLastReturned = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (cursor == 0) {
                currentMyNode = first;
            } else {
                currentMyNode = currentMyNode.next;
            }
            indexOfLastReturned = cursor;
            cursor++;
            lastReturned = currentMyNode;
            return currentMyNode.item;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            checkForModification();
            if (size == 1) {
                first = null;
                last = null;
            } else if (lastReturned.prev == null) {
                first = first.next;
                lastReturned.next.prev = null;
            } else if (lastReturned.next == null) {
                last = last.prev;
                lastReturned.prev.next = null;
            } else {
                lastReturned.prev.next = lastReturned.next;
                lastReturned.next.prev = lastReturned.prev;
            }
            size--;
            modCount++;
            cursor = indexOfLastReturned;
            lastReturned = null;
            indexOfLastReturned = -1;
            expectedModCount = modCount;
        }

        final void checkForModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

    }


    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    private class ListItr extends Itr implements ListIterator<E> {

        ListItr(int index) {
            super();
            cursor = index;
            if (index != 0) {
                currentMyNode = getMyNode(index - 1);
            }
        }

        @Override
        public boolean hasPrevious() {
            checkForModification();
            return cursor > 0;
        }

        @Override
        public E previous() {
            indexOfLastReturned = cursor - 1;
            cursor--;
            lastReturned = currentMyNode;
            currentMyNode = currentMyNode.prev;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForModification();
            lastReturned.item = e;
        }

        @Override
        public void add(E e) {
            checkForModification();
            if (cursor == size) {
                add(e);
            } else if (cursor == 0) {
                MyNode<E> newMyNode = new MyNode<>(null, e, first);
                first.prev = newMyNode;
                first = newMyNode;
                size++;
                modCount++;
            } else {
                MyNode<E> newMyNode = new MyNode<>(currentMyNode, e,
                                                   currentMyNode.next);
                currentMyNode.next = newMyNode;
                currentMyNode = newMyNode;
                currentMyNode.next.prev = currentMyNode;
                size++;
                modCount++;
            }
            cursor++;
            lastReturned = null;
            indexOfLastReturned = -1;
            expectedModCount = modCount;
        }

    }

}
