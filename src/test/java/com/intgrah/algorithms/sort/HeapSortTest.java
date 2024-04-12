package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class HeapSortTest extends QuasiLinearSorterTest {

    @Override
    public HeapSort<Integer> getInstance(Comparator<Integer> ord) { return new HeapSort<>(ord); }

}
