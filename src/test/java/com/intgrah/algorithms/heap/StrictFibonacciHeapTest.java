package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class StrictFibonacciHeapTest extends DecreasableHeapTest {

    @Override
    protected <T> StrictFibonacciHeap<T> getInstance(Comparator<T> ord) {
        return new StrictFibonacciHeap<>(ord);
    }

}
