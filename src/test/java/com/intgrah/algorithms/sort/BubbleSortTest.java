package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class BubbleSortTest extends SorterTest {

    @Override
    protected BubbleSort<Integer> getInstance(Comparator<Integer> ord) {
        return new BubbleSort<>(ord);
    }

}
