package com.intgrah.algorithms.heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciHeapTest extends DecreasableHeapTest {

    @Test
    public void cascadingCutTest() {
        FibonacciHeap<Integer> pq = getInstance();

        int n = 100;
        List<DecreasableHeap<Integer>.Decreasable> cutMeOff = new ArrayList<>();

        DecreasableHeap<Integer>.Decreasable bingo = pq.pushRef(n);
        DecreasableHeap<Integer>.Decreasable ouch = pq.pushRef(1000);
        pq.push(0);
        pq.popMin();
        pq.push(--n);
        cutMeOff.add(pq.pushRef(1000));
        pq.push(0);
        pq.popMin();
        ouch.decreaseKey(0);
        pq.popMin();
        while (--n > 0) {
            pq.push(n);
            DecreasableHeap<Integer>.Decreasable a = pq.pushRef(1000);
            pq.push(0);
            pq.popMin();
            cutMeOff.add(pq.pushRef(1000));
            DecreasableHeap<Integer>.Decreasable b = pq.pushRef(2000);
            pq.push(0);
            pq.popMin();
            a.decreaseKey(0);
            pq.popMin();
            b.decreaseKey(0);
            pq.popMin();
        }
        for (DecreasableHeap<Integer>.Decreasable byeBye : cutMeOff) {
            byeBye.decreaseKey(0);
            pq.popMin();
        }
        bingo.decreaseKey(0);
        for (int i = 0; !pq.isEmpty(); i++)
            assertEquals(i, pq.popMin());
    }

    @Override
    protected FibonacciHeap<Integer> getInstance() {
        return new FibonacciHeap<Integer>(Comparator.naturalOrder());
    }

}
