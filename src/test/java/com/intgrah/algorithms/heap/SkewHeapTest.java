package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class SkewHeapTest extends HeapTest {

    @Override
    protected <T> SkewHeap<T> getInstance(Comparator<T> ord) {
        return new SkewHeap<>(ord);
    }

}
