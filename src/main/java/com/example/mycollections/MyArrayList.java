package com.example.mycollections;

import java.util.*;

public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private E[] elementData;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int sizeOfArray) {
        elementData = (E[]) new Object[sizeOfArray];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(Collection<? extends E> collection) {
        elementData = (E[]) collection.toArray();
        size = collection.size();
    }

    public MyArrayList(Collection<? extends E> collection, int sizeOfArray) {
        this(sizeOfArray);
        addAll(collection);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(E e) {
        if (size + 1 >= elementData.length) {
            Object[] newElementData = new Object[elementData.length
                                                 + (elementData.length >> 1) + 3];
            System.arraycopy(elementData, 0,
                             newElementData, 0,
                             elementData.length);
            elementData = (E[]) newElementData;
        }
        elementData[size] = e;
        size++;
        modCount++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(int index, E e) {
        Objects.checkIndex(index, size + 1);

        if (size + 1 >= elementData.length) {
            Object[] newElementData = new Object[elementData.length +
                                                 (elementData.length >> 1) + 3];
            System.arraycopy(elementData, 0,
                             newElementData, 0,
                             index);
            newElementData[index] = e;
            System.arraycopy(elementData, index,
                             newElementData, index + 1,
                             size - index);
            elementData = (E[]) newElementData;
        } else {
            System.arraycopy(elementData, index,
                             elementData, index + 1,
                             size - index);
            elementData[index] = e;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);

        E removedElement = elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index,
                size - index - 1);
        size--;
        elementData[size] = null;
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

        elementData[index] = e;
    }

    @Override
    public int length() {
        return size;
    }


    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        int cursor;
        int lastReturned = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            checkForModification();
            return cursor < size;
        }

        @Override
        public E next() {
            checkForModification();
            lastReturned = cursor;
            return elementData[cursor++];
        }

        @Override
        public void remove() {
            if (lastReturned < 0) {
                throw new IllegalStateException();
            }
            checkForModification();
            MyArrayList.this.remove(lastReturned);
            cursor = lastReturned;
            lastReturned = -1;
            expectedModCount = modCount;
        }

        final void checkForModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

    }


    @Override
    public ListIterator<E> listIterator(Integer index) {
        return new ListItr(index);
    }

    private class ListItr extends Itr implements ListIterator<E> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            checkForModification();
            return cursor > 0;
        }

        @Override
        public E previous() {
            checkForModification();
            lastReturned = cursor - 1;
            return elementData[--cursor];
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
            if (lastReturned < 0)
                throw new IllegalStateException();
            checkForModification();
            MyArrayList.this.set(lastReturned, e);
        }

        @Override
        public void add(E e) {
            checkForModification();
            MyArrayList.this.add(cursor, e);
            cursor++;
            lastReturned = -1;
            expectedModCount = modCount;
        }

    }


    private boolean equalsRange(MyList<?> list) {
        if (size != list.length()) {
            return false;
        }
        Iterator<?> selfIt = this.iterator();
        Iterator<?> listIt = list.iterator();
        while (selfIt.hasNext() && listIt.hasNext()) {
            if (!Objects.equals(selfIt.next(), listIt.next())) {
                return false;
            }
        }
        if (size != list.length()) {
            return false;
        }
        return !selfIt.hasNext() && !listIt.hasNext();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof MyList<?> that)) {
            return false;
        }
        return equalsRange(that);
    }

    @Override
    public int hashCode() {
        Object[] subElementData = new Object[size];
        System.arraycopy(elementData, 0, subElementData, 0, size);
        return Objects.hash(size, Arrays.hashCode(subElementData));
    }

    @Override
    public String toString() {
        Object[] subElementData = new Object[size];
        System.arraycopy(elementData, 0, subElementData, 0, size);

        StringBuilder sb = new StringBuilder();
        sb.append("MyArrayList{")
          .append(Arrays.toString(subElementData))
          .append('}');
        return sb.toString();
    }

}

