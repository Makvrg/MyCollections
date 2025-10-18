package com.example.mycollections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Iterator;
import java.util.List;

public class MyArrayListTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/dataStringForArray.csv")
    void get(String el1, String el2, String el3, String el4, String el5, String el6) {
        MyList<String> myArrayList = new MyArrayList<>(List.of(el1, el2, el3, el4, el5, el6));
        Assertions.assertEquals("ceo", myArrayList.get(3));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myArrayList.get(6)
        );
    }

    @Test
    void addToEnd() {
        MyList<Integer> bigMyArrayList = new MyArrayList<>(List.of(-1, 1, 2, 3, 4, 5), 0);
        MyList<Integer> mediumMyArrayList = new MyArrayList<>(List.of(-1, 1), 0);
        MyList<Integer> smallMyArrayList = new MyArrayList<>(List.of(-1), 20);
        MyList<Integer> nanoMyArrayList = new MyArrayList<>(List.of());
        MyList<Integer> nanoMyArrayList1 = new MyArrayList<>();
        MyList<Integer> myArrayList = new MyArrayList<>(1);

        Assertions.assertEquals(6, bigMyArrayList.length());
        bigMyArrayList.add(-9999);
        Assertions.assertEquals(7, bigMyArrayList.length());
        Assertions.assertEquals(-9999, bigMyArrayList.get(6));
        Assertions.assertEquals(-1, bigMyArrayList.get(0));
        Assertions.assertEquals(2, bigMyArrayList.get(2));
        Assertions.assertEquals(5, bigMyArrayList.get(5));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.get(7)
        );

        Assertions.assertEquals(2, mediumMyArrayList.length());
        mediumMyArrayList.add(-9999);
        Assertions.assertEquals(3, mediumMyArrayList.length());
        Assertions.assertEquals(-9999, mediumMyArrayList.get(2));
        Assertions.assertEquals(-1, mediumMyArrayList.get(0));
        Assertions.assertEquals(1, mediumMyArrayList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> mediumMyArrayList.get(-1)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> mediumMyArrayList.get(3)
        );

        Assertions.assertEquals(1, smallMyArrayList.length());
        smallMyArrayList.add(-9999);
        Assertions.assertEquals(2, smallMyArrayList.length());
        Assertions.assertEquals(-9999, smallMyArrayList.get(1));
        Assertions.assertEquals(-1, smallMyArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(7)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(2)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(5000/9)
        );

        Assertions.assertEquals(0, nanoMyArrayList.length());
        nanoMyArrayList.add(-9999);
        Assertions.assertEquals(1, nanoMyArrayList.length());
        Assertions.assertEquals(-9999, nanoMyArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList.get(1)
        );

        Assertions.assertEquals(0, nanoMyArrayList1.length());
        nanoMyArrayList1.add(-9999);
        Assertions.assertEquals(1, nanoMyArrayList1.length());
        Assertions.assertEquals(-9999, nanoMyArrayList1.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList1.get(1)
        );

        Assertions.assertEquals(0, myArrayList.length());
        myArrayList.add(-9999);
        Assertions.assertEquals(1, myArrayList.length());
        Assertions.assertEquals(-9999, myArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myArrayList.get(1)
        );
    }

    @Test
    void addToIndex() {
        MyList<Integer> bigMyArrayList = new MyArrayList<>(List.of(-1, 1, 2, 3, 4, 5), 0);
        MyList<Integer> mediumMyArrayList = new MyArrayList<>(List.of(-1, 1), 0);
        MyList<Integer> smallMyArrayList = new MyArrayList<>(List.of(-1), 20);
        MyList<Integer> nanoMyArrayList = new MyArrayList<>(List.of());
        MyList<Integer> nanoMyArrayList1 = new MyArrayList<>();
        MyList<Integer> myArrayList = new MyArrayList<>(1);

        Assertions.assertEquals(6, bigMyArrayList.length());
        bigMyArrayList.add(2, -9999);
        Assertions.assertEquals(7, bigMyArrayList.length());
        Assertions.assertEquals(5, bigMyArrayList.get(6));
        Assertions.assertEquals(-1, bigMyArrayList.get(0));
        Assertions.assertEquals(-9999, bigMyArrayList.get(2));
        Assertions.assertEquals(1, bigMyArrayList.get(1));
        Assertions.assertEquals(2, bigMyArrayList.get(3));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.get(7)
        );

        Assertions.assertEquals(2, mediumMyArrayList.length());
        mediumMyArrayList.add(0, -9999);
        Assertions.assertEquals(3, mediumMyArrayList.length());
        Assertions.assertEquals(1, mediumMyArrayList.get(2));
        Assertions.assertEquals(-9999, mediumMyArrayList.get(0));
        Assertions.assertEquals(-1, mediumMyArrayList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> mediumMyArrayList.get(-1)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> mediumMyArrayList.get(3)
        );

        Assertions.assertEquals(1, smallMyArrayList.length());
        smallMyArrayList.add(1, -9999);
        Assertions.assertEquals(2, smallMyArrayList.length());
        Assertions.assertEquals(-9999, smallMyArrayList.get(1));
        Assertions.assertEquals(-1, smallMyArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.add(3, 0)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(7)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(2)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(5000/9)
        );

        Assertions.assertEquals(0, nanoMyArrayList.length());
        nanoMyArrayList.add(0, -9999);
        Assertions.assertEquals(1, nanoMyArrayList.length());
        Assertions.assertEquals(-9999, nanoMyArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList.add(2, 0)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList.get(1)
        );

        Assertions.assertEquals(0, nanoMyArrayList1.length());
        nanoMyArrayList1.add(0, -9999);
        nanoMyArrayList1.add(1, -1111);
        Assertions.assertEquals(2, nanoMyArrayList1.length());
        Assertions.assertEquals(-9999, nanoMyArrayList1.get(0));
        Assertions.assertEquals(-1111, nanoMyArrayList1.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList1.get(2)
        );

        Assertions.assertEquals(0, myArrayList.length());
        myArrayList.add(0, -9999);
        Assertions.assertEquals(1, myArrayList.length());
        Assertions.assertEquals(-9999, myArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myArrayList.get(1)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myArrayList.add(2, 0)
        );
    }

    @Test
    void remove() {
        MyList<Integer> bigMyArrayList = new MyArrayList<>(List.of(0, 1, 2, 3, 4, 5));

        Assertions.assertEquals(6, bigMyArrayList.length());
        Assertions.assertEquals(2, bigMyArrayList.remove(2));
        // bigMyArrayList = [0, 1, 3, 4, 5]
        Assertions.assertEquals(5, bigMyArrayList.length());
        Assertions.assertEquals(3, bigMyArrayList.get(2));
        Assertions.assertEquals(1, bigMyArrayList.get(1));
        Assertions.assertEquals(0, bigMyArrayList.get(0));
        Assertions.assertEquals(5, bigMyArrayList.get(4));
        Assertions.assertEquals(4, bigMyArrayList.get(3));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.remove(5)
        );

        Assertions.assertEquals(0, bigMyArrayList.remove(0));
        // bigMyArrayList = [1, 3, 4, 5]
        Assertions.assertEquals(4, bigMyArrayList.length());
        Assertions.assertEquals(1, bigMyArrayList.get(0));
        Assertions.assertEquals(3, bigMyArrayList.get(1));
        Assertions.assertEquals(4, bigMyArrayList.get(2));
        Assertions.assertEquals(5, bigMyArrayList.get(3));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.get(4)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.remove(4)
        );

        Assertions.assertEquals(1, bigMyArrayList.remove(0));
        Assertions.assertEquals(5, bigMyArrayList.remove(2));
        // bigMyArrayList = [3, 4]
        Assertions.assertEquals(2, bigMyArrayList.length());
        Assertions.assertEquals(3, bigMyArrayList.get(0));
        Assertions.assertEquals(4, bigMyArrayList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.get(2)
        );

        Assertions.assertEquals(4, bigMyArrayList.remove(1));
        Assertions.assertEquals(3, bigMyArrayList.remove(0));
        // bigMyArrayList = []
        Assertions.assertEquals(0, bigMyArrayList.length());
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.get(0)
        );
    }

    @Test
    void set() {
        MyList<Integer> bigMyArrayList = new MyArrayList<>(List.of(0, 1, 2, 3, 4, 5));
        MyList<String> smallMyArrayList = new MyArrayList<>(List.of("Hello"));

        Assertions.assertEquals(6, bigMyArrayList.length());
        bigMyArrayList.set(0, 777);
        bigMyArrayList.set(5, -99);
        bigMyArrayList.set(2, 777);
        // bigMyArrayList = [777, 1, 777, 3, 4, -99]
        Assertions.assertEquals(6, bigMyArrayList.length());
        Assertions.assertEquals(777, bigMyArrayList.get(0));
        Assertions.assertEquals(1, bigMyArrayList.get(1));
        Assertions.assertEquals(777, bigMyArrayList.get(2));
        Assertions.assertEquals(3, bigMyArrayList.get(3));
        Assertions.assertEquals(4, bigMyArrayList.get(4));
        Assertions.assertEquals(-99, bigMyArrayList.get(5));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.set(6, 111)
        );

        Assertions.assertEquals(1, smallMyArrayList.length());
        smallMyArrayList.set(0, "aaa");
        // smallMyArrayList = ["aaa"]
        Assertions.assertEquals(1, smallMyArrayList.length());
        Assertions.assertEquals("aaa", smallMyArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.set(1, "aaa")
        );
        Assertions.assertEquals("aaa", smallMyArrayList.remove(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.set(0, "aaa")
        );
    }

    @Test
    void addAll() {
        MyList<Integer> bigMyArrayList = new MyArrayList<>();
        MyList<Integer> smallMyArrayList = new MyArrayList<>();
        MyList<Integer> nanoMyArrayList = new MyArrayList<>();

        bigMyArrayList.addAll(List.of(0, 1, 2, 3));
        smallMyArrayList.addAll(List.of(0));
        nanoMyArrayList.addAll(List.of());

        Assertions.assertEquals(4, bigMyArrayList.length());
        Assertions.assertEquals(1, smallMyArrayList.length());
        Assertions.assertEquals(0, nanoMyArrayList.length());

        Assertions.assertEquals(0, bigMyArrayList.get(0));
        Assertions.assertEquals(1, bigMyArrayList.get(1));
        Assertions.assertEquals(2, bigMyArrayList.get(2));
        Assertions.assertEquals(3, bigMyArrayList.get(3));

        Assertions.assertEquals(0, smallMyArrayList.get(0));
        smallMyArrayList.add(999);
        Assertions.assertEquals(2, smallMyArrayList.length());
        Assertions.assertEquals(999, smallMyArrayList.get(1));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(2)
        );

        Assertions.assertEquals(0, nanoMyArrayList.length());
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList.get(0)
        );
        nanoMyArrayList.add(0, 777);
        Assertions.assertEquals(1, nanoMyArrayList.length());
        Assertions.assertEquals(777, nanoMyArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList.get(1)
        );
    }

    @Test
    void bubbleSort() {
        MyList<Integer> bigMyArrayList = new MyArrayList<>();
        MyList<Integer> smallMyArrayList = new MyArrayList<>();
        MyList<Integer> nanoMyArrayList = new MyArrayList<>();

        bigMyArrayList.addAll(List.of(1, 3, 0, 1, 2, 9, -1));
        smallMyArrayList.addAll(List.of(9));
        nanoMyArrayList.addAll(List.of());

        MyList.bubbleSort(bigMyArrayList);
        // bigMyArrayList = [-1, 0, 1, 1, 2, 3, 9]
        Assertions.assertEquals(7, bigMyArrayList.length());
        Assertions.assertEquals(-1, bigMyArrayList.get(0));
        Assertions.assertEquals(0, bigMyArrayList.get(1));
        Assertions.assertEquals(1, bigMyArrayList.get(2));
        Assertions.assertEquals(1, bigMyArrayList.get(3));
        Assertions.assertEquals(2, bigMyArrayList.get(4));
        Assertions.assertEquals(3, bigMyArrayList.get(5));
        Assertions.assertEquals(9, bigMyArrayList.get(6));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.get(7)
        );
        MyList.bubbleSort(bigMyArrayList);
        // bigMyArrayList = [-1, 0, 1, 1, 2, 3, 9]
        Assertions.assertEquals(7, bigMyArrayList.length());
        Assertions.assertEquals(-1, bigMyArrayList.get(0));
        Assertions.assertEquals(0, bigMyArrayList.get(1));
        Assertions.assertEquals(1, bigMyArrayList.get(2));
        Assertions.assertEquals(1, bigMyArrayList.get(3));
        Assertions.assertEquals(2, bigMyArrayList.get(4));
        Assertions.assertEquals(3, bigMyArrayList.get(5));
        Assertions.assertEquals(9, bigMyArrayList.get(6));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> bigMyArrayList.get(7)
        );

        MyList.bubbleSort(smallMyArrayList);
        // smallMyArrayList = [9]
        Assertions.assertEquals(1, smallMyArrayList.length());
        Assertions.assertEquals(9, smallMyArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(1)
        );
        MyList.bubbleSort(smallMyArrayList);
        // smallMyArrayList = [9]
        Assertions.assertEquals(1, smallMyArrayList.length());
        Assertions.assertEquals(9, smallMyArrayList.get(0));
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> smallMyArrayList.get(1)
        );

        MyList.bubbleSort(nanoMyArrayList);
        // nanoMyArrayList = []
        Assertions.assertEquals(0, nanoMyArrayList.length());
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList.get(0)
        );
        MyList.bubbleSort(nanoMyArrayList);
        // nanoMyArrayList = []
        Assertions.assertEquals(0, nanoMyArrayList.length());
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> nanoMyArrayList.get(0)
        );
    }

    @Test
    void forEach() {
        MyList<Integer> bigMyArrayList = new MyArrayList<>(List.of(0, 1, 2, 3, 4));
        Integer[] bigArray = new Integer[5];

        for (Integer i : bigMyArrayList) {
            bigArray[i] = i;
        }
        Assertions.assertEquals(0, bigArray[0]);
        Assertions.assertEquals(1, bigArray[1]);
        Assertions.assertEquals(2, bigArray[2]);
        Assertions.assertEquals(3, bigArray[3]);
        Assertions.assertEquals(4, bigArray[4]);
        for (Iterator<Integer> iterator = bigMyArrayList.iterator(); iterator.hasNext();) {
            Integer el = iterator.next();
            bigArray[el] = el;
        }
        Assertions.assertEquals(0, bigArray[0]);
        Assertions.assertEquals(1, bigArray[1]);
        Assertions.assertEquals(2, bigArray[2]);
        Assertions.assertEquals(3, bigArray[3]);
        Assertions.assertEquals(4, bigArray[4]);


        MyList<Integer> smallMyArrayList = new MyArrayList<>(List.of(0));
        Integer[] smallArray = new Integer[1];

        for (Integer i : smallMyArrayList) {
            smallArray[i] = i;
        }
        Assertions.assertEquals(0, smallArray[0]);
        for (Iterator<Integer> iterator = smallMyArrayList.iterator(); iterator.hasNext();) {
            Integer el = iterator.next();
            smallArray[el] = el;
        }
        Assertions.assertEquals(0, smallArray[0]);


        MyList<Integer> nanoMyArrayList = new MyArrayList<>();
        Integer[] nanoArray = new Integer[0];

        for (Integer i : nanoMyArrayList) {
            nanoArray[i] = i;
        }
        for (Iterator<Integer> iterator = nanoMyArrayList.iterator(); iterator.hasNext();) {
            Integer el = iterator.next();
            nanoArray[el] = el;
        }
    }

}
