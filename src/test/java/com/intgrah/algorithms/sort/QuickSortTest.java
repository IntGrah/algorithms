package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class QuickSortTest extends QuasiLinearSorterTest {

    @Override
    protected <T> QuickSort<T> getInstance(Comparator<T> ord) {
        return new QuickSort<>(ord);
    }

}
