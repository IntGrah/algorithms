package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class QuickSortTest extends QuasiLinearSorterTest {

    @Override
    public QuickSort<Integer> getInstance(Comparator<Integer> ord) { return new QuickSort<>(ord); }

}
