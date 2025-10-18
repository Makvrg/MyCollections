package com.example.mycollections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private E[] elementData;

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
    }

    @SuppressWarnings("unchecked")
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
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);

        E removedElement = elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index,
                size - index - 1);
        size--;
        elementData[size] = null;

        return removedElement;
    }

    public void addAll(Collection<? extends E> collection) {
        for (E e : collection) {
            add(e);
        }
    }

    public void set(int index, E e) {
        Objects.checkIndex(index, size);

        elementData[index] = e;
    }

    public int length() {
        return size;
    }


    @Override
    public Iterator<E> iterator() {
        return new myArrayListIterator();
    }

    private class myArrayListIterator implements Iterator<E> {

        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            return elementData[currentIndex++];
        }
    }

}
