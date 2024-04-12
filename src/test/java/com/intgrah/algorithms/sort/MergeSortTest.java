package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class MergeSortTest extends QuasiLinearSorterTest {

    @Override
    public MergeSort<Integer> getInstance(Comparator<Integer> ord) { return new MergeSort<>(ord); }

}
