package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class BinaryHeapTest extends HeapTest {

    @Override
    protected BinaryHeap<Integer> getInstance() {
        return new BinaryHeap<Integer>(Comparator.naturalOrder());
    }

}
