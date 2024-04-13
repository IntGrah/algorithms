package com.intgrah.algorithms.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class StackTest {

    @Test
    public void simpleTest() {
        Stack<Integer> stack = getInstance();

        stack.pushFront(2);
        stack.pushFront(1);
        stack.pushFront(0);

        assertEquals(0, stack.getFront());

        assertEquals(0, stack.popFront());
        assertEquals(1, stack.popFront());
        assertEquals(2, stack.popFront());
    }

    protected abstract Stack<Integer> getInstance();

    @Test
    public void nodeTest() {
        Stack<Integer> list = getInstance();

        AbstractList<Integer>.Node ref = list.pushFront(1);

        assertEquals(1, ref.getValue());

        ref.setValue(2);

        assertEquals(2, ref.getValue());
        assertEquals(2, list.popFront());
    }

    @Test
    public void emptyExceptionTest() {
        Stack<Integer> list = getInstance();

        list.pushFront(1);

        list.clear();

        assertThrows(AssertionError.class, list::popFront);
    }

}
