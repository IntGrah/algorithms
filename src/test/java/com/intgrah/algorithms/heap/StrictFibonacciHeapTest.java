package com.intgrah.algorithms.heap;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrictFibonacciHeapTest extends DecreasableHeapTest {

    @Override
    protected <T> StrictFibonacciHeap<T> getInstance(Comparator<T> ord) {
        return new StrictFibonacciHeap<>(ord);
    }

    @Test
    public void mergeTest() {
        StrictFibonacciHeap<Integer> h = getInstance(Integer::compareTo);

        h.push(3);
        h.push(1);
        h.push(4);
        h.push(1);
        h.push(5);
        h.push(9);
        h.push(2);
        h.push(6);
        h.push(5);
        h.push(3);
        h.push(5);
        h.push(8);
        h.push(9);
        h.push(7);
        h.push(9);
        h.push(3);
        h.push(2);
        h.push(3);
        h.push(8);
        h.push(4);

        assertEquals(1, h.popMin());
        assertEquals(1, h.popMin());
        assertEquals(2, h.popMin());
        assertEquals(2, h.popMin());
        assertEquals(3, h.popMin());
        assertEquals(3, h.popMin());
        assertEquals(3, h.popMin());
        assertEquals(3, h.popMin());
        assertEquals(4, h.popMin());
        assertEquals(4, h.popMin());
        assertEquals(5, h.popMin());
        assertEquals(5, h.popMin());
        assertEquals(5, h.popMin());
        assertEquals(6, h.popMin());
        assertEquals(7, h.popMin());
        assertEquals(8, h.popMin());
        assertEquals(8, h.popMin());
        assertEquals(9, h.popMin());
        assertEquals(9, h.popMin());
        assertEquals(9, h.popMin());
    }

    @Test
    public void bugTest() {
        StrictFibonacciHeap<Integer> h = getInstance(Integer::compareTo);

        h.push(33);
        h.push(12);
        h.push(1);
        h.push(14);
        h.push(2);
        h.push(4);
        h.push(27);
        h.push(5);
        h.push(8);
        h.push(16);
        h.push(31);
        h.push(17);
        h.push(3);
        h.push(30);
        h.push(10);
        h.push(32);
        h.push(36);
        h.push(29);
        h.push(9);
        h.push(23);
        h.push(38);
        h.push(19);
        h.push(26);
        h.push(25);
        h.push(21);
        h.push(18);
        h.push(20);
        h.push(7);
        h.push(11);
        h.push(22);
        h.push(15);
        h.push(6);
        h.push(39);
        h.push(34);
        h.push(37);
        h.push(0);
        h.push(28);
        h.push(13);
        h.push(24);
        h.push(35);
    }

}
