package com.intgrah.algorithms.heap;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class HeapTest {

    @Test
    public void randomTest() {
        Heap<Integer> pq = getInstance(Integer::compareTo);

        int k = 712;
        for (int i = 0; i < 1000; i++) {
            pq.push(k);
            k = (401 * k + 417) % 1000;
        }
        for (int i = 0; i < 1000; i++)
            assertEquals(i, pq.popMin());
    }

    protected abstract <T> Heap<T> getInstance(Comparator<T> ord);

    @Test
    public void mixedTest() {
        Heap<Integer> pq = getInstance(Integer::compareTo);

        int k = 712;
        for (int i = 0; i < 1000; i++) {
            pq.push(k);
            k = (401 * k + 417) % 1000;
        }
        for (int i = 0; i < 1000; i++)
            assertEquals(i, pq.popMin());
    }

    @Test
    public void duplicateTest() {
        Heap<Integer> pq = getInstance(Integer::compareTo);

        pq.push(4);
        pq.push(4);
        pq.push(3);
        assertEquals(3, pq.popMin());
        assertEquals(4, pq.popMin());
        pq.push(2);
        pq.push(5);
        pq.push(2);
        pq.push(5);
        assertEquals(2, pq.popMin());
        assertEquals(2, pq.popMin());
        assertEquals(4, pq.popMin());
        pq.push(0);
        pq.push(1);
        pq.push(2);
        assertEquals(0, pq.popMin());
        pq.push(0);
        pq.push(0);
        pq.push(0);
        assertEquals(0, pq.popMin());
        assertEquals(0, pq.popMin());
        assertEquals(0, pq.popMin());
        assertEquals(1, pq.popMin());
        assertEquals(2, pq.popMin());
        assertEquals(5, pq.popMin());
        assertEquals(5, pq.popMin());
        assertTrue(pq.isEmpty());
    }

    @Test
    public void allSameTest() {
        Heap<Integer> pq = getInstance(Integer::compareTo);

        for (int i = 0; i < 1000; i++)
            pq.push(0);
        for (int i = 0; i < 1000; i++)
            assertEquals(0, pq.popMin());
        assertTrue(pq.isEmpty());
    }

    @Test
    public void emptyExceptionTest() {
        Heap<Integer> pq = getInstance(Integer::compareTo);

        pq.push(4);
        assertEquals(4, pq.getMin());
        pq.clear();
        assertTrue(pq.isEmpty());
        assertThrows(AssertionError.class, pq::getMin);
        assertThrows(AssertionError.class, pq::popMin);
    }

}
