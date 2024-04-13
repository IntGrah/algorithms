package com.intgrah.algorithms.heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class DecreasableHeapTest extends HeapTest {

    @Test
    public void simpleTest() {
        DecreasableHeap<Integer> pq = getInstance();

        DecreasableHeap<Integer>.Decreasable n5 = pq.pushRef(5);
        pq.push(6);
        pq.push(8);
        DecreasableHeap<Integer>.Decreasable n7 = pq.pushRef(7);
        assertEquals(5, pq.getMin());
        pq.push(1);
        pq.push(3);
        pq.push(2);
        pq.push(4);
        assertEquals(1, pq.popMin());
        n5.decreaseKey(0);
        assertEquals(0, n5.getKey());
        assertEquals(0, pq.popMin());
        assertEquals(2, pq.popMin());
        assertEquals(3, pq.popMin());
        n7.decreaseKey(5);
        assertEquals(4, pq.popMin());
        assertEquals(5, pq.getMin());
        assertEquals(5, pq.popMin());
        assertEquals(6, pq.popMin());
        assertEquals(8, pq.popMin());
    }

    @Override
    protected abstract DecreasableHeap<Integer> getInstance();

    @Test
    public void test() {
        DecreasableHeap<Integer> pq = getInstance();

        List<DecreasableHeap<Integer>.Decreasable> nodes = new ArrayList<>(1000);
        for (int i = 0, k = 712; i < 1000; i++) {
            nodes.add(pq.pushRef(k % 2 == 0 ? k : k + 500));
            k = (401 * k + 417) % 1000;
        }
        for (DecreasableHeap<Integer>.Decreasable node : nodes) {
            int k = node.getKey();
            if (k % 2 == 1) { node.decreaseKey(k - 500); }
        }
        for (int i = 0; i < 1000; i++)
            assertEquals(i, pq.popMin());
    }

    @Test
    public void keyExceptionTest() {
        DecreasableHeap<Integer> pq = getInstance();

        DecreasableHeap<Integer>.Decreasable n4 = pq.pushRef(4);

        assertThrows(AssertionError.class, () -> n4.decreaseKey(5));
    }

}
