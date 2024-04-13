package com.intgrah.algorithms.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinkedListTest extends StackTest {

    @Test
    public void appendTest() {
        LinkedList<Integer> list1 = getInstance();
        LinkedList<Integer> list2 = getInstance();

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
    protected LinkedList<Integer> getInstance() {return new LinkedList<>(); }

    @Test
    public void appendEmptyTest() {
        LinkedList<Integer> list1 = getInstance();
        LinkedList<Integer> list2 = getInstance();

        list1.pushFront(1);
        list1.append(list2);

        assertEquals(1, list1.size());

        LinkedList<Integer> list3 = getInstance();
        LinkedList<Integer> list4 = getInstance();

        list3.append(list4);

        assertEquals(0, list3.size());
        assertThrows(AssertionError.class, list3::getFront);
        assertThrows(AssertionError.class, list3::getBack);

        assertEquals(0, list4.size());
        assertThrows(AssertionError.class, list4::getFront);
        assertThrows(AssertionError.class, list4::getBack);
    }

    @Test
    public void backTest() {
        LinkedList<Integer> list = getInstance();

        assertThrows(AssertionError.class, list::getBack);

        list.pushFront(1);

        assertEquals(1, list.getBack());

        list.pushBack(2);

        assertEquals(2, list.size());
        assertEquals(2, list.getBack());
        assertEquals(1, list.popFront());
        assertEquals(2, list.popFront());
    }

}
