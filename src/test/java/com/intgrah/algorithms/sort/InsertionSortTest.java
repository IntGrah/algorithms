package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class InsertionSortTest extends SorterTest {

    @Override
    protected <T> InsertionSort<T> getInstance(Comparator<T> ord) {
        return new InsertionSort<>(ord);
    }

}
