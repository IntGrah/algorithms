package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class BinomialHeapTest extends DecreasableHeapTest {

    @Override
    public BinomialHeap<Integer> getInstance() {
        return new BinomialHeap<Integer>(Comparator.naturalOrder());
    }

}
