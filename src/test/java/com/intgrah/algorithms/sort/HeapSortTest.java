package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class HeapSortTest extends QuasiLinearSorterTest {

    @Override
    protected <T> HeapSort<T> getInstance(Comparator<T> ord) {
        return new HeapSort<>(ord);
    }

}
