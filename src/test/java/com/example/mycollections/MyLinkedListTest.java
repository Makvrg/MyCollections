package com.example.mycollections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Iterator;
import java.util.List;

public class MyLinkedListTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/dataStringForLinked.csv")
    void get(String el1, String el2, String el3, String el4, String el5, String el6) {
        MyList<String> myLinkedList = new MyLinkedList<>(List.of(el1, el2, el3, el4, el5, el6));
        Assertions.assertEquals("ceo0", myLinkedList.get(1));
        Assertions.assertEquals("ceo1", myLinkedList.get(2));
        Assertions.assertEquals("ceo2", myLinkedList.get(3));
        Assertions.assertEquals("ceo3", myLinkedList.get(4));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myLinkedList.get(6)
        );
    }

    @Test
    void addToEnd() {
        MyList<Integer> bigMyLinkedList = new MyLinkedList<>(List.of(-1, 1, 2, 3, 4, 5));
        MyList<Integer> mediumMyLinkedList = new MyLinkedList<>(List.of(-1, 1));
        MyList<Integer> smallMyLinkedList = new MyLinkedList<>(List.of(-1));
        MyList<Integer> nanoMyLinkedList = new MyLinkedList<>(List.of());
        MyList<Integer> nanoNanoMyLinkedList = new MyLinkedList<>();

        Assertions.assertEquals(6, bigMyLinkedList.length());
        bigMyLinkedList.add(-9999);
        Assertions.assertEquals(7, bigMyLinkedList.length());
        Assertions.assertEquals(-9999, bigMyLinkedList.get(6));
        Assertions.assertEquals(-1, bigMyLinkedList.get(0));
        Assertions.assertEquals(2, bigMyLinkedList.get(2));
        Assertions.assertEquals(5, bigMyLinkedList.get(5));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.get(7)
        );

        Assertions.assertEquals(2, mediumMyLinkedList.length());
        mediumMyLinkedList.add(-9999);
        Assertions.assertEquals(3, mediumMyLinkedList.length());
        Assertions.assertEquals(-9999, mediumMyLinkedList.get(2));
        Assertions.assertEquals(-1, mediumMyLinkedList.get(0));
        Assertions.assertEquals(1, mediumMyLinkedList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> mediumMyLinkedList.get(-1)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> mediumMyLinkedList.get(3)
        );

        Assertions.assertEquals(1, smallMyLinkedList.length());
        smallMyLinkedList.add(-9999);
        Assertions.assertEquals(2, smallMyLinkedList.length());
        Assertions.assertEquals(-9999, smallMyLinkedList.get(1));
        Assertions.assertEquals(-1, smallMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(7)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(2)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(5000/9)
        );

        Assertions.assertEquals(0, nanoMyLinkedList.length());
        nanoMyLinkedList.add(-9999);
        Assertions.assertEquals(1, nanoMyLinkedList.length());
        Assertions.assertEquals(-9999, nanoMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyLinkedList.get(1)
        );

        Assertions.assertEquals(0, nanoNanoMyLinkedList.length());
        nanoNanoMyLinkedList.add(-9999);
        Assertions.assertEquals(1, nanoNanoMyLinkedList.length());
        Assertions.assertEquals(-9999, nanoNanoMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoNanoMyLinkedList.get(1)
        );
    }

    @Test
    void addToIndex() {
        MyList<Integer> bigMyLinkedList = new MyLinkedList<>(List.of(-1, 1, 2, 3, 4, 5));
        MyList<Integer> mediumMyLinkedList = new MyLinkedList<>(List.of(-1, 1));
        MyList<Integer> smallMyLinkedList = new MyLinkedList<>(List.of(-1));
        MyList<Integer> nanoMyLinkedList = new MyLinkedList<>(List.of());
        MyList<Integer> nanoNanoMyLinkedList = new MyLinkedList<>();

        Assertions.assertEquals(6, bigMyLinkedList.length());
        bigMyLinkedList.add(2, -9999);
        bigMyLinkedList.add(5, -111);
        // bigMyLinkedList = [-1, 1, -9999, 2, 3, -111, 4, 5]
        Assertions.assertEquals(8, bigMyLinkedList.length());
        Assertions.assertEquals(5, bigMyLinkedList.get(7));
        Assertions.assertEquals(4, bigMyLinkedList.get(6));
        Assertions.assertEquals(-111, bigMyLinkedList.get(5));
        Assertions.assertEquals(3, bigMyLinkedList.get(4));
        Assertions.assertEquals(2, bigMyLinkedList.get(3));
        Assertions.assertEquals(-9999, bigMyLinkedList.get(2));
        Assertions.assertEquals(-9999, bigMyLinkedList.get(2));
        Assertions.assertEquals(1, bigMyLinkedList.get(1));
        Assertions.assertEquals(-1, bigMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.get(8)
        );

        Assertions.assertEquals(2, mediumMyLinkedList.length());
        mediumMyLinkedList.add(0, -9999);
        Assertions.assertEquals(3, mediumMyLinkedList.length());
        Assertions.assertEquals(1, mediumMyLinkedList.get(2));
        Assertions.assertEquals(-9999, mediumMyLinkedList.get(0));
        Assertions.assertEquals(-1, mediumMyLinkedList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> mediumMyLinkedList.get(-1)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> mediumMyLinkedList.get(3)
        );

        Assertions.assertEquals(1, smallMyLinkedList.length());
        smallMyLinkedList.add(1, -9999);
        Assertions.assertEquals(2, smallMyLinkedList.length());
        Assertions.assertEquals(-9999, smallMyLinkedList.get(1));
        Assertions.assertEquals(-1, smallMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.add(3, 0)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(7)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(2)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(5000/9)
        );

        Assertions.assertEquals(0, nanoMyLinkedList.length());
        nanoMyLinkedList.add(0, -9999);
        Assertions.assertEquals(1, nanoMyLinkedList.length());
        Assertions.assertEquals(-9999, nanoMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyLinkedList.add(2, 0)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyLinkedList.get(1)
        );

        Assertions.assertEquals(0, nanoNanoMyLinkedList.length());
        nanoNanoMyLinkedList.add(0, -9999);
        nanoNanoMyLinkedList.add(1, -1111);
        Assertions.assertEquals(2, nanoNanoMyLinkedList.length());
        Assertions.assertEquals(-9999, nanoNanoMyLinkedList.get(0));
        Assertions.assertEquals(-1111, nanoNanoMyLinkedList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoNanoMyLinkedList.get(2)
        );
    }

    @Test
    void remove() {
        MyList<Integer> bigMyLinkedList = new MyLinkedList<>(List.of(0, 1, 2, 3, 4, 5));

        Assertions.assertEquals(6, bigMyLinkedList.length());
        Assertions.assertEquals(2, bigMyLinkedList.remove(2));
        // bigMyLinkedList = [0, 1, 3, 4, 5]
        Assertions.assertEquals(5, bigMyLinkedList.length());
        Assertions.assertEquals(3, bigMyLinkedList.get(2));
        Assertions.assertEquals(1, bigMyLinkedList.get(1));
        Assertions.assertEquals(0, bigMyLinkedList.get(0));
        Assertions.assertEquals(5, bigMyLinkedList.get(4));
        Assertions.assertEquals(4, bigMyLinkedList.get(3));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.remove(5)
        );

        Assertions.assertEquals(0, bigMyLinkedList.remove(0));
        // bigMyLinkedList = [1, 3, 4, 5]
        Assertions.assertEquals(4, bigMyLinkedList.length());
        Assertions.assertEquals(1, bigMyLinkedList.get(0));
        Assertions.assertEquals(3, bigMyLinkedList.get(1));
        Assertions.assertEquals(4, bigMyLinkedList.get(2));
        Assertions.assertEquals(5, bigMyLinkedList.get(3));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.get(4)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.remove(4)
        );

        Assertions.assertEquals(4, bigMyLinkedList.remove(2));
        Assertions.assertEquals(5, bigMyLinkedList.remove(2));
        // bigMyLinkedList = [1, 3]
        Assertions.assertEquals(2, bigMyLinkedList.length());
        Assertions.assertEquals(1, bigMyLinkedList.get(0));
        Assertions.assertEquals(3, bigMyLinkedList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.get(2)
        );

        Assertions.assertEquals(3, bigMyLinkedList.remove(1));
        Assertions.assertEquals(1, bigMyLinkedList.remove(0));
        // bigMyLinkedList = []
        Assertions.assertEquals(0, bigMyLinkedList.length());
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.get(0)
        );
    }

    @Test
    void set() {
        MyList<Integer> bigMyLinkedList = new MyLinkedList<>(List.of(0, 1, 2, 3, 4, 5));
        MyList<String> smallMyLinkedList = new MyLinkedList<>(List.of("Hello"));

        Assertions.assertEquals(6, bigMyLinkedList.length());
        bigMyLinkedList.set(0, 777);
        bigMyLinkedList.set(5, -99);
        bigMyLinkedList.set(2, 777);
        // bigMyLinkedList = [777, 1, 777, 3, 4, -99]
        Assertions.assertEquals(6, bigMyLinkedList.length());
        Assertions.assertEquals(777, bigMyLinkedList.get(0));
        Assertions.assertEquals(1, bigMyLinkedList.get(1));
        Assertions.assertEquals(777, bigMyLinkedList.get(2));
        Assertions.assertEquals(3, bigMyLinkedList.get(3));
        Assertions.assertEquals(4, bigMyLinkedList.get(4));
        Assertions.assertEquals(-99, bigMyLinkedList.get(5));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.set(6, 111)
        );

        Assertions.assertEquals(1, smallMyLinkedList.length());
        smallMyLinkedList.set(0, "aaa");
        // smallMyLinkedList = ["aaa"]
        Assertions.assertEquals(1, smallMyLinkedList.length());
        Assertions.assertEquals("aaa", smallMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.set(1, "aaa")
        );
        Assertions.assertEquals("aaa", smallMyLinkedList.remove(0));
        // smallMyLinkedList = []
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.set(0, "aaa")
        );
    }

    @Test
    void addAll() {
        MyList<Integer> bigMyLinkedList = new MyLinkedList<>();
        MyList<Integer> smallMyLinkedList = new MyLinkedList<>();
        MyList<Integer> nanoMyLinkedList = new MyLinkedList<>();

        bigMyLinkedList.addAll(List.of(0, 1, 2, 3));
        smallMyLinkedList.addAll(List.of(0));
        nanoMyLinkedList.addAll(List.of());

        Assertions.assertEquals(4, bigMyLinkedList.length());
        Assertions.assertEquals(1, smallMyLinkedList.length());
        Assertions.assertEquals(0, nanoMyLinkedList.length());

        Assertions.assertEquals(0, bigMyLinkedList.get(0));
        Assertions.assertEquals(1, bigMyLinkedList.get(1));
        Assertions.assertEquals(2, bigMyLinkedList.get(2));
        Assertions.assertEquals(3, bigMyLinkedList.get(3));

        Assertions.assertEquals(0, smallMyLinkedList.get(0));
        smallMyLinkedList.add(999);
        Assertions.assertEquals(2, smallMyLinkedList.length());
        Assertions.assertEquals(999, smallMyLinkedList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(2)
        );

        Assertions.assertEquals(0, nanoMyLinkedList.length());
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyLinkedList.get(0)
        );
        nanoMyLinkedList.add(0, 777);
        Assertions.assertEquals(1, nanoMyLinkedList.length());
        Assertions.assertEquals(777, nanoMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyLinkedList.get(1)
        );
    }

    @Test
    void bubbleSort() {
        MyList<Integer> bigMyLinkedList = new MyLinkedList<>();
        MyList<Integer> smallMyLinkedList = new MyLinkedList<>();
        MyList<Integer> nanoMyLinkedList = new MyLinkedList<>();

        bigMyLinkedList.addAll(List.of(1, 3, 0, 1, 2, 9, -1));
        smallMyLinkedList.addAll(List.of(9));
        nanoMyLinkedList.addAll(List.of());

        MyList.bubbleSort(bigMyLinkedList);
        // bigMyLinkedList = [-1, 0, 1, 1, 2, 3, 9]
        Assertions.assertEquals(7, bigMyLinkedList.length());
        Assertions.assertEquals(-1, bigMyLinkedList.get(0));
        Assertions.assertEquals(0, bigMyLinkedList.get(1));
        Assertions.assertEquals(1, bigMyLinkedList.get(2));
        Assertions.assertEquals(1, bigMyLinkedList.get(3));
        Assertions.assertEquals(2, bigMyLinkedList.get(4));
        Assertions.assertEquals(3, bigMyLinkedList.get(5));
        Assertions.assertEquals(9, bigMyLinkedList.get(6));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.get(7)
        );
        MyList.bubbleSort(bigMyLinkedList);
        // bigMyLinkedList = [-1, 0, 1, 1, 2, 3, 9]
        Assertions.assertEquals(7, bigMyLinkedList.length());
        Assertions.assertEquals(-1, bigMyLinkedList.get(0));
        Assertions.assertEquals(0, bigMyLinkedList.get(1));
        Assertions.assertEquals(1, bigMyLinkedList.get(2));
        Assertions.assertEquals(1, bigMyLinkedList.get(3));
        Assertions.assertEquals(2, bigMyLinkedList.get(4));
        Assertions.assertEquals(3, bigMyLinkedList.get(5));
        Assertions.assertEquals(9, bigMyLinkedList.get(6));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyLinkedList.get(7)
        );

        MyList.bubbleSort(smallMyLinkedList);
        // smallMyLinkedList = [9]
        Assertions.assertEquals(1, smallMyLinkedList.length());
        Assertions.assertEquals(9, smallMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(1)
        );
        MyList.bubbleSort(smallMyLinkedList);
        // smallMyLinkedList = [9]
        Assertions.assertEquals(1, smallMyLinkedList.length());
        Assertions.assertEquals(9, smallMyLinkedList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyLinkedList.get(1)
        );

        MyList.bubbleSort(nanoMyLinkedList);
        // nanoMyLinkedList = []
        Assertions.assertEquals(0, nanoMyLinkedList.length());
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyLinkedList.get(0)
        );
        MyList.bubbleSort(nanoMyLinkedList);
        // nanoMyLinkedList = []
        Assertions.assertEquals(0, nanoMyLinkedList.length());
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyLinkedList.get(0)
        );
    }

    @Test
    void forEach() {
        MyList<Integer> bigMyLinkedList = new MyLinkedList<>(List.of(0, 1, 2, 3, 4));
        Integer[] bigArray = new Integer[5];

        for (Integer i : bigMyLinkedList) {
            bigArray[i] = i;
        }
        Assertions.assertEquals(0, bigArray[0]);
        Assertions.assertEquals(1, bigArray[1]);
        Assertions.assertEquals(2, bigArray[2]);
        Assertions.assertEquals(3, bigArray[3]);
        Assertions.assertEquals(4, bigArray[4]);
        for (Iterator<Integer> iterator = bigMyLinkedList.iterator(); iterator.hasNext();) {
            Integer el = iterator.next();
            bigArray[el] = el;
        }
        Assertions.assertEquals(0, bigArray[0]);
        Assertions.assertEquals(1, bigArray[1]);
        Assertions.assertEquals(2, bigArray[2]);
        Assertions.assertEquals(3, bigArray[3]);
        Assertions.assertEquals(4, bigArray[4]);


        MyList<Integer> smallMyLinkedList = new MyLinkedList<>(List.of(0));
        Integer[] smallArray = new Integer[1];

        for (Integer i : smallMyLinkedList) {
            smallArray[i] = i;
        }
        Assertions.assertEquals(0, smallArray[0]);
        for (Iterator<Integer> iterator = smallMyLinkedList.iterator(); iterator.hasNext();) {
            Integer el = iterator.next();
            smallArray[el] = el;
        }
        Assertions.assertEquals(0, smallArray[0]);


        MyList<Integer> nanoMyLinkedList = new MyLinkedList<>();
        Integer[] nanoArray = new Integer[0];

        for (Integer i : nanoMyLinkedList) {
            nanoArray[i] = i;
        }
        for (Iterator<Integer> iterator = nanoMyLinkedList.iterator(); iterator.hasNext();) {
            Integer el = iterator.next();
            nanoArray[el] = el;
        }
    }

}
