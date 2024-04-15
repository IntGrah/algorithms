package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class BubbleSortTest extends SorterTest {

    @Override
    protected <T> BubbleSort<T> getInstance(Comparator<T> ord) {
        return new BubbleSort<>(ord);
    }

}
