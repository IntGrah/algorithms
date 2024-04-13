package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class SkewHeapTest extends HeapTest {

    @Override
    protected SkewHeap<Integer> getInstance() {
        return new SkewHeap<Integer>(Comparator.naturalOrder());
    }

}
