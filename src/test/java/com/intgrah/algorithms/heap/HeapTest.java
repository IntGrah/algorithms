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
