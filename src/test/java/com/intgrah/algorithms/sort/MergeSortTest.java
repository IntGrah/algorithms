package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class MergeSortTest extends QuasiLinearSorterTest {

    @Override
    protected <T> MergeSort<T> getInstance(Comparator<T> ord) {
        return new MergeSort<>(ord);
    }

}
