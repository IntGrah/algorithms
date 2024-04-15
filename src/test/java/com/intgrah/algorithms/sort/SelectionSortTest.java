package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class SelectionSortTest extends SorterTest {

    @Override
    protected <T> SelectionSort<T> getInstance(Comparator<T> ord) {
        return new SelectionSort<>(ord);
    }

}
