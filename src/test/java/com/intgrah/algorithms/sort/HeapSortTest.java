package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class HeapSortTest extends QuasiLinearSorterTest {

    @Override
    protected HeapSort<Integer> getInstance(Comparator<Integer> ord) { return new HeapSort<>(ord); }

}
