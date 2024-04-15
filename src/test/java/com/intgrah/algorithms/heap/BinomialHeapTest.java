package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class BinomialHeapTest extends DecreasableHeapTest {

    @Override
    public <T> BinomialHeap<T> getInstance(Comparator<T> ord) {
        return new BinomialHeap<>(ord);
    }

}
