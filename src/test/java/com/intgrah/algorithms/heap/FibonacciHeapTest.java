package com.intgrah.algorithms.heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.intgrah.algorithms.heap.Heap.Decreasable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciHeapTest extends HeapTest {

    @Test
    public void cascadingCutTest() {
        FibonacciHeap<Integer> pq = getInstance();

        int n = 100;
        List<Decreasable<Integer>> cutMeOff = new ArrayList<>();

        Decreasable<Integer> bingo = pq.push(n);
        Decreasable<Integer> ouch = pq.push(1000);
        pq.push(0);
        pq.popMin();
        pq.push(--n);
        cutMeOff.add(pq.push(1000));
        pq.push(0);
        pq.popMin();
        ouch.decreaseKey(0);
        pq.popMin();
        while (--n > 0) {
            pq.push(n);
            Decreasable<Integer> a = pq.push(1000);
            pq.push(0);
            pq.popMin();
            cutMeOff.add(pq.push(1000));
            Decreasable<Integer> b = pq.push(2000);
            pq.push(0);
            pq.popMin();
            a.decreaseKey(0);
            pq.popMin();
            b.decreaseKey(0);
            pq.popMin();
        }
        for (Decreasable<Integer> byeBye : cutMeOff) {
            byeBye.decreaseKey(0);
            pq.popMin();
        }
        bingo.decreaseKey(0);
        for (int i = 0; !pq.isEmpty(); i++)
            assertEquals(i, pq.popMin());
    }

    @Override
    public FibonacciHeap<Integer> getInstance() {
        return new FibonacciHeap<>();
    }

}
