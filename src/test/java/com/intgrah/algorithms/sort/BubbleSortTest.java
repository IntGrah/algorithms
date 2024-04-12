package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class BubbleSortTest extends SorterTest {

    @Override
    public BubbleSort<Integer> getInstance(Comparator<Integer> ord) {
        return new BubbleSort<>(ord);
    }

}
