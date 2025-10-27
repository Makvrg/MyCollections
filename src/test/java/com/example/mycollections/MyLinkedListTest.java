package com.example.mycollections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Iterator;
import java.util.List;

public class MyLinkedListTest {

    @SafeVarargs
    private static <T> void assertListContent(MyList<T> list, T... expected) {
        Assertions.assertEquals(expected.length, list.length());
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], list.get(i));
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/StringData.csv")
    void get_getElementsFromLists(String el1, String el2, String el3,
             String el4, String el5, String el6) {
        MyList<String> list = new MyLinkedList<>(
                List.of(el1, el2, el3, el4, el5, el6)
        );
        assertListContent(list, el1, "ceo0", "ceo1",
                          "ceo2", "ceo3", el6);
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(6)
        );
    }


    @Test
    void addToEnd_AddElementToBigList() {
        MyList<Integer> bigList = new MyLinkedList<>(List.of(-1, 1, 2, 3, 4, 5));
        assertListContent(bigList, -1, 1, 2, 3, 4, 5);

        bigList.add(-9999);
        assertListContent(bigList, -1, 1, 2, 3, 4, 5, -9999);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> bigList.get(7));
    }

    @Test
    void addToEnd_AddElementToMediumList() {
        MyList<Integer> mediumList = new MyLinkedList<>(List.of(-1, 1));
        assertListContent(mediumList, -1, 1);

        mediumList.add(-9999);
        assertListContent(mediumList, -1, 1, -9999);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mediumList.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mediumList.get(3));
    }

    @Test
    void addToEnd_AddElementToSmallList() {
        MyList<Integer> smallList = new MyLinkedList<>(List.of(-1));
        assertListContent(smallList, -1);

        smallList.add(-9999);
        assertListContent(smallList, -1, -9999);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(2));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(5000 / 9));
    }

    @Test
    void addToEnd_AddElementToEmptyLists() {
        MyList<Integer> emptyList1 = new MyLinkedList<>();
        MyList<Integer> emptyList2 = new MyLinkedList<>(List.of());
        assertListContent(emptyList1);
        assertListContent(emptyList2);

        emptyList1.add(-9999);
        emptyList2.add(-9999);
        assertListContent(emptyList1, -9999);
        assertListContent(emptyList2, -9999);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList1.get(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList2.get(1));
    }


    @Test
    void addToIndex_AddsElementToBigList() {
        MyList<Integer> bigList = new MyLinkedList<>(List.of(-1, 1, 2, 3, 4, 5));
        assertListContent(bigList, -1, 1, 2, 3, 4, 5);

        bigList.add(2, -9999); // [-1, 1, -9999, 2, 3, 4, 5]
        bigList.add(5, -111);  // [-1, 1, -9999, 2, 3, -111, 4, 5]
        assertListContent(bigList, -1, 1, -9999, 2, 3, -111, 4, 5);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> bigList.get(8));
    }

    @Test
    void addToIndex_AddElementToMediumList() {
        MyList<Integer> mediumList = new MyLinkedList<>(List.of(-1, 1));
        assertListContent(mediumList, -1, 1);

        mediumList.add(0, -9999); // [-9999, -1, 1]
        assertListContent(mediumList, -9999, -1, 1);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mediumList.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mediumList.get(3));
    }

    @Test
    void addToIndex_AddElementToSmallList() {
        MyList<Integer> smallList = new MyLinkedList<>(List.of(-1));
        assertListContent(smallList, -1);

        smallList.add(1, -9999); // [-1, -9999]
        assertListContent(smallList, -1, -9999);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.add(3, 0));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(7));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(2));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(5000 / 9));
    }

    @Test
    void addToIndex_AddElementsToEmptyLists() {
        MyList<Integer> emptyList = new MyLinkedList<>();
        assertListContent(emptyList);

        emptyList.add(0, -9999);
        emptyList.add(1, -1111);
        assertListContent(emptyList, -9999, -1111);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.add(3, 0));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(2));
    }


    @Test
    void remove_removeElementFromMiddle() {
        MyList<Integer> list = new MyLinkedList<>(List.of(0, 1, 2, 3, 4, 5));
        Assertions.assertEquals(6, list.length());

        int removed = list.remove(2); // [0, 1, 3, 4, 5]
        Assertions.assertEquals(2, removed);
        assertListContent(list, 0, 1, 3, 4, 5);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(5));
    }

    @Test
    void remove_removeElementsFromStart() {
        MyList<Integer> list = new MyLinkedList<>(List.of(0, 1, 2, 3, 4, 5));
        list.remove(0); // [1, 2, 3, 4, 5]
        int removed = list.remove(0); // [2, 3, 4, 5]
        Assertions.assertEquals(1, removed);
        assertListContent(list, 2, 3, 4, 5);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(4));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(4));
    }

    @Test
    void remove_removeElementsFromEnd() {
        MyList<Integer> list = new MyLinkedList<>(List.of(0, 1, 2, 3, 4, 5));
        list.remove(list.length() - 1); // [0, 1, 2, 3, 4]
        list.remove(list.length() - 1); // [0, 1, 2, 3]

        int removedLast = list.remove(3); // [0, 1, 2]
        Assertions.assertEquals(3, removedLast);
        int removedFinal = list.remove(2); // [0, 1]
        Assertions.assertEquals(2, removedFinal);

        assertListContent(list, 0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(2));
    }

    @Test
    void remove_removeAllElements() {
        MyList<Integer> list = new MyLinkedList<>(List.of(0, 1, 2, 3, 4, 5));
        list.remove(2); // [0, 1, 3, 4, 5]
        list.remove(0); // [1, 3, 4, 5]
        list.remove(2); // [1, 3, 5]
        list.remove(2); // [1, 3]
        list.remove(1); // [1]
        list.remove(0); // []

        Assertions.assertEquals(0, list.length());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0));
    }


    @Test
    void set_setMultipleElements() {
        MyList<Integer> list = new MyLinkedList<>(List.of(0, 1, 2, 3, 4, 5));
        Assertions.assertEquals(6, list.length());

        list.set(0, 777);
        list.set(5, -99);
        list.set(2, 777);
        // [777, 1, 777, 3, 4, -99]

        assertListContent(list, 777, 1, 777, 3, 4, -99);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.set(6, 111));
    }

    @Test
    void set_setSingleElement() {
        MyList<String> list = new MyLinkedList<>(List.of("Hello"));
        Assertions.assertEquals(1, list.length());

        list.set(0, "aaa");
        assertListContent(list, "aaa");

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.set(1, "bbb"));
    }

    @Test
    void set_setOnEmptyList() {
        MyList<String> list = new MyLinkedList<>();

        Assertions.assertEquals(0, list.length());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.set(0, "aaa"));
    }


    @Test
    void addAll_addMultipleElements() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addAll(List.of(0, 1, 2, 3));

        assertListContent(list, 0, 1, 2, 3);
        Assertions.assertEquals(4, list.length());
    }

    @Test
    void addAll_addSingleElement() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addAll(List.of(0));

        assertListContent(list, 0);
        Assertions.assertEquals(1, list.length());

        list.add(999);
        assertListContent(list, 0, 999);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(2));
    }

    @Test
    void addAll_addToEmptyList() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addAll(List.of());

        assertListContent(list);
        Assertions.assertEquals(0, list.length());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0));

        list.add(0, 777);
        assertListContent(list, 777);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(1));
    }


    @Test
    void bubbleSort_sortBigList() {
        MyList<Integer> bigList = new MyLinkedList<>();
        bigList.addAll(List.of(1, 3, 0, 1, 2, 9, -1));

        MyList.bubbleSort(bigList);
        assertListContent(bigList, -1, 0, 1, 1, 2, 3, 9);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> bigList.get(7));

        MyList.bubbleSort(bigList);
        assertListContent(bigList, -1, 0, 1, 1, 2, 3, 9);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> bigList.get(7));
    }

    @Test
    void bubbleSort_sortSmallList() {
        MyList<Integer> smallList = new MyLinkedList<>();
        smallList.addAll(List.of(9));

        MyList.bubbleSort(smallList);
        assertListContent(smallList, 9);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(1));

        MyList.bubbleSort(smallList);
        assertListContent(smallList, 9);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(1));
    }

    @Test
    void bubbleSort_sortEmptyList() {
        MyList<Integer> emptyList = new MyLinkedList<>();

        MyList.bubbleSort(emptyList);
        assertListContent(emptyList);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(0));

        MyList.bubbleSort(emptyList);
        assertListContent(emptyList);
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(0));
    }


    @Test
    void forEach_iterationBigList() {
        MyList<Integer> bigList = new MyLinkedList<>(List.of(0, 1, 2, 3, 4));
        Integer[] result = new Integer[5];

        for (Integer i : bigList) {
            result[i] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{0, 1, 2, 3, 4}, result);

        for (Iterator<Integer> it = bigList.iterator(); it.hasNext(); ) {
            Integer el = it.next();
            result[el] = el;
        }
        Assertions.assertArrayEquals(new Integer[]{0, 1, 2, 3, 4}, result);
    }

    @Test
    void forEach_iterationSmallList() {
        MyList<Integer> smallList = new MyLinkedList<>(List.of(0));
        Integer[] result = new Integer[1];

        for (Integer i : smallList) {
            result[i] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{0}, result);

        for (Iterator<Integer> it = smallList.iterator(); it.hasNext(); ) {
            Integer el = it.next();
            result[el] = el;
        }
        Assertions.assertArrayEquals(new Integer[]{0}, result);
    }

    @Test
    void forEach_iterationEmptyList() {
        MyList<Integer> emptyList = new MyLinkedList<>();
        Integer[] result = new Integer[0];

        for (Integer el : emptyList) {
            Assertions.fail("Цикл for-each не должен выполняться для пустого списка");
        }

        for (Iterator<Integer> it = emptyList.iterator(); it.hasNext(); ) {
            Assertions.fail("Итератор не должен иметь элементов в пустом списке");
        }

        Assertions.assertEquals(0, result.length);
    }

}
