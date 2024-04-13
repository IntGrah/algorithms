package com.intgrah.algorithms.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoublyLinkedListTest extends StackTest {

    @Test
    public void appendTest() {
        DoublyLinkedList<Integer> list1 = getInstance();
        DoublyLinkedList<Integer> list2 = getInstance();

        list1.pushFront(0);
        list1.pushBack(1);
        list1.pushBack(2);

        list2.pushBack(3);
        list2.pushBack(4);
        list2.pushBack(5);


        assertEquals(3, list1.size());
        assertEquals(3, list2.size());

        list1.append(list2);

        assertEquals(6, list1.size());

        int i = 0;
        for (int x : list1)
            assertEquals(i++, x);
    }

    @Override
    protected DoublyLinkedList<Integer> getInstance() {
        return new DoublyLinkedList<>();
    }

    @Override
    @Test
    public void emptyExceptionTest() {
        DoublyLinkedList<Integer> list = getInstance();

        assertThrows(AssertionError.class, list::popBack);
        assertThrows(AssertionError.class, list::popFront);
    }

    @Test
    public void backTest() {
        DoublyLinkedList<Integer> list = getInstance();

        assertThrows(AssertionError.class, list::getBack);

        list.pushFront(1);

        assertEquals(1, list.getFront());
        assertEquals(1, list.getBack());

        list.pushBack(2);

        assertEquals(2, list.size());
        assertEquals(2, list.getBack());
        assertEquals(2, list.popBack());
        assertEquals(1, list.popBack());
    }

}
