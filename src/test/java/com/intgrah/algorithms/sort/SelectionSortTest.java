package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class SelectionSortTest extends SorterTest {

    @Override
    public SelectionSort<Integer> getInstance(Comparator<Integer> ord) {
        return new SelectionSort<>(ord);
    }

}
