package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class InsertionSortTest extends SorterTest {

    @Override
    protected InsertionSort<Integer> getInstance(Comparator<Integer> ord) {
        return new InsertionSort<>(ord);
    }

}
