package com.intgrah.algorithms.heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.intgrah.algorithms.heap.Heap.Decreasable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class HeapTest extends BasicHeapTest {

    @Test
    public void simpleTest() {
        Heap<Integer> pq = getInstance();

        Decreasable<Integer> n5 = pq.push(5);
        pq.push(6);
        pq.push(8);
        Decreasable<Integer> n7 = pq.push(7);
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
    protected abstract Heap<Integer> getInstance();

    @Test
    public void test() {
        Heap<Integer> pq = getInstance();

        List<Decreasable<Integer>> nodes = new ArrayList<>(1000);
        for (int i = 0, k = 712; i < 1000; i++) {
            nodes.add(pq.push(k % 2 == 0 ? k : k + 500));
            k = (401 * k + 417) % 1000;
        }
        for (Decreasable<Integer> node : nodes) {
            int k = node.getKey();
            if (k % 2 == 1) { node.decreaseKey(k - 500); }
        }
        for (int i = 0; i < 1000; i++)
            assertEquals(i, pq.popMin());
    }

    @Test
    public void keyExceptionTest() {
        Heap<Integer> pq = getInstance();

        Decreasable<Integer> n4 = pq.push(4);

        assertThrows(Heap.KeyException.class, () -> n4.decreaseKey(5));
    }

}
