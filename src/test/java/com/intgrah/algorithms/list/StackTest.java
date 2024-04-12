package com.intgrah.algorithms.list;

import com.intgrah.algorithms.util.EmptyException;
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

        assertEquals(3, stack.size());

        int i = 0;
        for (int x : stack)
            assertEquals(i++, x);

        assertEquals(0, stack.getFront());

        assertEquals(0, stack.popFront());
        assertEquals(1, stack.popFront());
        assertEquals(2, stack.popFront());
    }

    public abstract Stack<Integer> getInstance();

    @Test
    public void nodeTest() {
        Stack<Integer> list = getInstance();

        List.Node<Integer> ref = list.pushFront(1);

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

        assertThrows(EmptyException.class, list::popFront);
    }

}
