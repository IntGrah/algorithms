package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class BinaryHeapTest extends HeapTest {

    @Override
    protected <T> BinaryHeap<T> getInstance(Comparator<T> ord) {
        return new BinaryHeap<>(ord);
    }

}
