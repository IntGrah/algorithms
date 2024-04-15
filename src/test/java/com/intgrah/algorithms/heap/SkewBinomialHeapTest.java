package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class SkewBinomialHeapTest extends HeapTest {

    @Override
    public <T> SkewBinomialHeap<T> getInstance(Comparator<T> ord) {
        return new SkewBinomialHeap<>(ord);
    }

}
