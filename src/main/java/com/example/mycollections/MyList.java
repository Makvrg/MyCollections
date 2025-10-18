package com.example.mycollections;

import java.util.Collection;

public interface MyList<E> extends Iterable<E> {

    void add(E e);

    void add(int index, E e);

    E get(int index);

    E remove(int index);

    void addAll(Collection<? extends E> collection);

    void set(int index, E e);

    int length();

    static <E extends Comparable<? super E>> void bubbleSort(MyList<E> myList) {
        for (int i = 0; i < myList.length() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < myList.length() - 1 - i; j++) {
                E currentElement = myList.get(j);
                E nextElement = myList.get(j + 1);
                if (currentElement.compareTo(nextElement) > 0) {
                    myList.set(j, nextElement);
                    myList.set(j + 1, currentElement);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

}
