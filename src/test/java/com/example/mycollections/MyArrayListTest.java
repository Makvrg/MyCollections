package com.example.mycollections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.*;

public class MyArrayListTest {

    @Test
    void toString_toStringBigLists() {
        MyList<Integer> bigIntegersList = new MyArrayList<>(
                List.of(1, 2, 3, 4, 5)
        );
        MyList<String> bigStringsList = new MyArrayList<>(
                List.of(" abc", "he llo", "world!  ")
        );
        String expectedIntegers = "MyArrayList{[1, 2, 3, 4, 5]}";
        String expectedStrings = "MyArrayList{[ abc, he llo, world!  ]}";

        Assertions.assertEquals(expectedIntegers, bigIntegersList.toString());
        Assertions.assertEquals(expectedStrings, bigStringsList.toString());
    }

    @Test
    void toString_toStringSmallLists() {
        MyList<Integer> smallIntegersList = new MyArrayList<>(
                List.of(1)
        );
        MyList<String> smallStringsList = new MyArrayList<>(
                List.of("abc")
        );
        String expectedIntegers = "MyArrayList{[1]}";
        String expectedStrings = "MyArrayList{[abc]}";

        Assertions.assertEquals(expectedIntegers, smallIntegersList.toString());
        Assertions.assertEquals(expectedStrings, smallStringsList.toString());
    }

    @Test
    void toString_toStringEmptyLists() {
        MyList<Integer> emptyIntegersList = new MyArrayList<>();
        MyList<String> emptyStringsList = new MyArrayList<>();
        String expectedIntegers = "MyArrayList{[]}";
        String expectedStrings = "MyArrayList{[]}";

        Assertions.assertEquals(expectedIntegers, emptyIntegersList.toString());
        Assertions.assertEquals(expectedStrings, emptyStringsList.toString());
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/StringData.csv")
    void get_getElementsFromLists(String el1, String el2, String el3,
             String el4, String el5, String el6) {
        MyList<String> list = new MyArrayList<>(
                List.of(el1, el2, el3, el4, el5, el6)
        );

        MyList<String> expectedMyList = new MyArrayList<>(
                List.of(el1, "ceo0", "ceo1", "ceo2", "ceo3", el6)
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(6)
        );
    }


    @Test
    void addToEnd_AddElementToBigList() {
        MyList<Integer> bigList = new MyArrayList<>(List.of(-1, 1, 2, 3, 4, 5), 1);
        Assertions.assertEquals(6, bigList.length());

        bigList.add(-9999);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(-1, 1, 2, 3, 4, 5, -9999)
        );
        Assertions.assertEquals(expectedMyList, bigList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> bigList.get(7));
    }

    @Test
    void addToEnd_AddElementToMediumList() {
        MyList<Integer> mediumList = new MyArrayList<>(List.of(-1, 1), 0);
        Assertions.assertEquals(2, mediumList.length());

        mediumList.add(-9999);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(-1, 1, -9999)
        );
        Assertions.assertEquals(expectedMyList, mediumList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mediumList.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mediumList.get(3));
    }

    @Test
    void addToEnd_AddElementToSmallList() {
        MyList<Integer> smallList = new MyArrayList<>(List.of(-1), 20);
        Assertions.assertEquals(1, smallList.length());

        smallList.add(-9999);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(-1, -9999)
        );
        Assertions.assertEquals(expectedMyList, smallList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(7));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(2));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(5000 / 9));
    }

    @Test
    void addToEnd_AddElementToEmptyLists() {
        List<MyList<Integer>> listWithArrayLists = List.of(
                new MyArrayList<>(), new MyArrayList<>(List.of()),
                new MyArrayList<>(25)
        );
        for (MyList<Integer> list : listWithArrayLists) {
            Assertions.assertEquals(0, list.length());
            list.add(-9999);

            MyList<Integer> expectedMyList = new MyArrayList<>(
                    List.of(-9999)
            );
            Assertions.assertEquals(expectedMyList, list);

            Assertions.assertThrows(IndexOutOfBoundsException.class,
                    () -> list.get(1));
        }
    }


    @Test
    void addToIndex_AddsElementToBigList() {
        MyList<Integer> bigList = new MyArrayList<>(List.of(-1, 1, 2, 3, 4, 5), 0);
        Assertions.assertEquals(6, bigList.length());

        bigList.add(2, -9999); // [-1, 1, -9999, 2, 3, 4, 5]

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(-1, 1, -9999, 2, 3, 4, 5)
        );
        Assertions.assertEquals(expectedMyList, bigList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> bigList.get(7));
    }

    @Test
    void addToIndex_AddElementToMediumList() {
        MyList<Integer> mediumList = new MyArrayList<>(List.of(-1, 1), 0);
        Assertions.assertEquals(2, mediumList.length());

        mediumList.add(0, -9999);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(-9999, -1, 1)
        );
        Assertions.assertEquals(expectedMyList, mediumList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mediumList.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mediumList.get(3));
    }

    @Test
    void addToIndex_AddElementToSmallList() {
        MyList<Integer> smallList = new MyArrayList<>(List.of(-1), 20);
        Assertions.assertEquals(1, smallList.length());

        smallList.add(1, -9999);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(-1, -9999)
        );
        Assertions.assertEquals(expectedMyList, smallList);

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
        List<MyList<Integer>> listWithArrayLists = List.of(
                new MyArrayList<>(), new MyArrayList<>(List.of()),
                new MyArrayList<>(1)
        );
        for (MyList<Integer> list : listWithArrayLists) {
            list.add(0, -9999);
            list.add(1, -1111);

            MyList<Integer> expectedMyList = new MyArrayList<>(
                    List.of(-9999, -1111)
            );
            Assertions.assertEquals(expectedMyList, list);

            Assertions.assertThrows(IndexOutOfBoundsException.class,
                    () -> list.get(2));
            Assertions.assertThrows(IndexOutOfBoundsException.class,
                    () -> list.add(3, 0));
        }
    }


    @Test
    void remove_removeElementFromMiddle() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4, 5));
        Assertions.assertEquals(6, list.length());

        int removed = list.remove(2); // [0, 1, 3, 4, 5]
        Assertions.assertEquals(2, removed);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(0, 1, 3, 4, 5)
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(5));
    }

    @Test
    void remove_removeElementsFromStart() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4, 5));
        list.remove(0); // [1, 2, 3, 4, 5]
        int removed = list.remove(0); // [2, 3, 4, 5]
        Assertions.assertEquals(1, removed);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(2, 3, 4, 5)
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(4));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(4));
    }

    @Test
    void remove_removeElementsFromEnd() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4, 5));
        list.remove(list.length() - 1); // [0, 1, 2, 3, 4]
        list.remove(list.length() - 1); // [0, 1, 2, 3]

        int removedLast = list.remove(3); // [0, 1, 2]
        Assertions.assertEquals(3, removedLast);
        int removedFinal = list.remove(2); // [0, 1]
        Assertions.assertEquals(2, removedFinal);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(0, 1)
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(2));
    }

    @Test
    void remove_removeAllElements() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4, 5));
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
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4, 5));
        Assertions.assertEquals(6, list.length());

        list.set(0, 777);
        list.set(5, -99);
        list.set(2, 777);
        // [777, 1, 777, 3, 4, -99]

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(777, 1, 777, 3, 4, -99)
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.set(6, 111));
    }

    @Test
    void set_setSingleElement() {
        MyList<String> list = new MyArrayList<>(List.of("Hello"));
        Assertions.assertEquals(1, list.length());

        list.set(0, "aaa");

        MyList<String> expectedMyList = new MyArrayList<>(
                List.of("aaa")
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.set(1, "bbb"));
    }

    @Test
    void set_setOnEmptyList() {
        MyList<String> list = new MyArrayList<>();

        Assertions.assertEquals(0, list.length());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.set(0, "aaa"));
    }


    @Test
    void addAll_addMultipleElements() {
        MyList<Integer> list = new MyArrayList<>();
        list.addAll(List.of(0, 1, 2, 3));

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(0, 1, 2, 3)
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertEquals(4, list.length());
    }

    @Test
    void addAll_addSingleElement() {
        MyList<Integer> list = new MyArrayList<>();
        list.addAll(List.of(0));

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(0)
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertEquals(1, list.length());

        list.add(999);

        expectedMyList = new MyArrayList<>(
                List.of(0, 999)
        );
        Assertions.assertEquals(expectedMyList, list);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(2));
    }

    @Test
    void addAll_addToEmptyList() {
        MyList<Integer> emptyList = new MyArrayList<>();
        emptyList.addAll(List.of());

        MyList<Integer> expectedMyList = new MyArrayList<>();
        Assertions.assertEquals(expectedMyList, emptyList);

        Assertions.assertEquals(0, emptyList.length());
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(0));

        emptyList.add(0, 777);

        expectedMyList = new MyArrayList<>(
                List.of(777)
        );
        Assertions.assertEquals(expectedMyList, emptyList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(1));
    }


    @Test
    void bubbleSort_sortBigList() {
        MyList<Integer> bigList = new MyArrayList<>();
        bigList.addAll(List.of(1, 3, 0, 1, 2, 9, -1));

        MyList.bubbleSort(bigList);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(-1, 0, 1, 1, 2, 3, 9)
        );
        Assertions.assertEquals(expectedMyList, bigList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> bigList.get(7));

        MyList.bubbleSort(bigList);

        Assertions.assertEquals(expectedMyList, bigList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> bigList.get(7));
    }

    @Test
    void bubbleSort_sortSmallList() {
        MyList<Integer> smallList = new MyArrayList<>();
        smallList.addAll(List.of(9));

        MyList.bubbleSort(smallList);

        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(9)
        );
        Assertions.assertEquals(expectedMyList, smallList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(1));

        MyList.bubbleSort(smallList);

        Assertions.assertEquals(expectedMyList, smallList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> smallList.get(1));
    }

    @Test
    void bubbleSort_sortEmptyList() {
        MyList<Integer> emptyList = new MyArrayList<>();

        MyList.bubbleSort(emptyList);

        MyList<Integer> expectedMyList = new MyArrayList<>();
        Assertions.assertEquals(expectedMyList, emptyList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(0));

        MyList.bubbleSort(emptyList);

        Assertions.assertEquals(expectedMyList, emptyList);

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(0));
    }


    @Test
    void forEach_iterationBigList() {
        MyList<Integer> bigList = new MyArrayList<>(List.of(0, 1, 2, 3, 4));
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
        MyList<Integer> smallList = new MyArrayList<>(List.of(0));
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
        MyList<Integer> emptyList = new MyArrayList<>();
        Integer[] result = new Integer[0];

        for (Integer el : emptyList) {
            Assertions.fail("Цикл for-each не должен выполняться для пустого списка");
        }

        for (Iterator<Integer> it = emptyList.iterator(); it.hasNext(); ) {
            Assertions.fail("Итератор не должен иметь элементов в пустом списке");
        }

        Assertions.assertEquals(0, result.length);
    }


    @Test
    void removeInIterator_removeElementsFromStart() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));
        Integer[] result = new Integer[5];

        for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
            Integer el = it.next();
            if (el == 0 || el == 1) {
                it.remove();
                Assertions.assertThrows(IllegalStateException.class,
                                        it::remove);
            }
        }
        for (Integer i : list) {
            result[i] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{null, null, 2, 3, 4},
                                     result);
    }

    @Test
    void removeInIterator_removeElementsFromMiddle() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));
        Integer[] result = new Integer[5];

        for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
            Integer el = it.next();
            if (el == 1 || el == 3) {
                it.remove();
                Assertions.assertThrows(IllegalStateException.class,
                                        it::remove);
            }
        }
        for (Integer i : list) {
            result[i] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{0, null, 2, null, 4},
                                     result);
    }

    @Test
    void removeInIterator_removeElementsFromEnd() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));
        Integer[] result = new Integer[5];

        for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
            Integer el = it.next();
            if (el == 3 || el == 4) {
                it.remove();
                Assertions.assertThrows(IllegalStateException.class,
                                        it::remove);
            }
        }
        for (Integer i : list) {
            result[i] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{0, 1, 2, null, null},
                                     result);
    }

    @Test
    void removeInIterator_removeAllElements() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));
        Integer[] result = new Integer[5];

        for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
            it.next();
            it.remove();
            Assertions.assertThrows(IllegalStateException.class,
                                    it::remove);
        }
        for (Integer i : list) {
            result[i] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{null, null, null, null, null},
                                     result);
    }

    @Test
    void removeInIterator_expectConcurrentModificationException() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));

        Iterator<Integer> it = list.iterator();
        if (it.hasNext()) {
            it.next();
            list.remove(0);
            Assertions.assertThrows(ConcurrentModificationException.class,
                                    it::remove);
        }
    }


    @Test
    void forEachListIterator_iterationList() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));
        MyList<Integer> result = new MyArrayList<>();

        for (ListIterator<Integer> it = list.listIterator(1); it.hasNext(); ) {
            Integer el = it.next();
            result.add(el);
            Assertions.assertTrue(it.hasPrevious());
            Assertions.assertEquals(el + 1, it.nextIndex());
            Assertions.assertEquals(el, it.previousIndex());
            if (el == 4) {
                for (int i = 0; i < list.length(); i++) {
                    Integer backEl = it.previous();
                    result.add(backEl);
                }
                Assertions.assertFalse(it.hasPrevious());
                break;
            }
        }
        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(1, 2, 3, 4, 4, 3, 2, 1, 0)
        );
        Assertions.assertEquals(expectedMyList, result);
    }


    @Test
    void addInListIterator_addElementsToBigList() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 3, 4, 5));
        Integer[] result = new Integer[9];

        ListIterator<Integer> it = list.listIterator(0);
        it.add(-2);
        it.add(-1);
        if (it.hasNext()) {
            Integer el = it.next();
            Assertions.assertEquals(0, el);
        }
        if (it.hasNext()) {
            Integer el = it.next();
            Assertions.assertEquals(1, el);
        }
        it.add(2);
        Integer el = 0;
        while (it.hasNext()) {
            el = it.next();
        }
        Assertions.assertEquals(5, el);
        it.add(6);
        for (Integer i : list) {
            result[i + 2] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{-2, -1, 0, 1, 2, 3, 4, 5, 6},
                                     result);
    }

    @Test
    void addInListIterator_addElementsToSmallList() {
        MyList<Integer> list = new MyArrayList<>(List.of(0));
        Integer[] result = new Integer[4];

        ListIterator<Integer> it = list.listIterator(0);
        it.add(-2);
        it.add(-1);
        if (it.hasNext()) {
            Integer el = it.next();
            Assertions.assertEquals(0, el);
        }
        if (it.hasNext()) {
            Assertions.fail("Не должно быть дальше элементов");
        }
        it.add(1);
        for (Integer i : list) {
            result[i + 2] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{-2, -1, 0, 1},
                                     result);
    }

    @Test
    void addInListIterator_addElementsToEmptyList() {
        MyList<Integer> list = new MyArrayList<>();
        Integer[] result = new Integer[3];

        ListIterator<Integer> it = list.listIterator(0);
        it.add(-2);
        it.add(-1);
        if (it.hasNext()) {
            Assertions.fail("Не должно быть дальше элементов");
        }
        it.add(0);
        for (Integer i : list) {
            result[i + 2] = i;
        }
        Assertions.assertArrayEquals(new Integer[]{-2, -1, 0},
                                     result);
    }

    @Test
    void addInIterator_expectConcurrentModificationException() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));

        ListIterator<Integer> it = list.listIterator(0);
        if (it.hasNext()) {
            it.next();
            list.add(0);
            Assertions.assertThrows(ConcurrentModificationException.class,
                    () -> it.add(9));
        }
    }


    @Test
    void setInListIterator_setMultipleElements() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));
        MyList<Integer> result = new MyArrayList<>();

        ListIterator<Integer> it = list.listIterator(0);

        Assertions.assertThrows(IllegalStateException.class,
                () -> it.set(2));

        if (it.hasNext()) {
            Integer el = it.next();
            Assertions.assertEquals(0, el);
        }
        it.set(-2);
        if (it.hasNext()) {
            Integer el = it.next();
            Assertions.assertEquals(1, el);
        }
        it.set(-1);
        Integer el = 0;
        while (it.hasNext()) {
            el = it.next();
        }
        Assertions.assertEquals(4, el);
        it.set(9);
        for (Integer i : list) {
            result.add(i);
        }
        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(-2, -1, 2, 3, 9)
        );
        Assertions.assertEquals(expectedMyList, result);
    }

    @Test
    void setInListIterator_setSingleElement() {
        MyList<Integer> list = new MyArrayList<>(List.of(0));
        MyList<Integer> result = new MyArrayList<>();

        ListIterator<Integer> it = list.listIterator(0);

        if (it.hasNext()) {
            Integer el = it.next();
            Assertions.assertEquals(0, el);
        }
        it.set(99);
        for (Integer i : list) {
            result.add(i);
        }
        MyList<Integer> expectedMyList = new MyArrayList<>(
                List.of(99)
        );
        Assertions.assertEquals(expectedMyList, result);
    }

    @Test
    void setInIterator_expectConcurrentModificationException() {
        MyList<Integer> list = new MyArrayList<>(List.of(0, 1, 2, 3, 4));

        ListIterator<Integer> it = list.listIterator(0);
        if (it.hasNext()) {
            it.next();
            list.add(99);
            Assertions.assertThrows(ConcurrentModificationException.class,
                    () -> it.set(9));
        }
    }

}
