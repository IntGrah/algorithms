package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.util.EmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class BasicHeapTest {

    @Test
    public void randomTest() {
        AbstractHeap<Integer, ?> pq = getInstance();

        int k = 712;
        for (int i = 0; i < 1000; i++) {
            pq.push(k);
            k = (401 * k + 417) % 1000;
        }
        for (int i = 0; i < 1000; i++)
            assertEquals(i, pq.popMin());
    }

    protected abstract AbstractHeap<Integer, ?> getInstance();

    @Test
    public void mixedTest() {
        AbstractHeap<Integer, ?> pq = getInstance();

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
        AbstractHeap<Integer, ?> pq = getInstance();

        pq.push(4);
        assertEquals(4, pq.getMin());
        pq.clear();
        assertTrue(pq.isEmpty());
        assertThrows(EmptyException.class, pq::getMin);
        assertThrows(EmptyException.class, pq::popMin);
    }

}
