package com.intgrah.algorithms.sort;

import java.util.Comparator;

import static com.intgrah.algorithms.util.Util.swap;

public class BubbleSort<T> extends Sorter<T> {

    public BubbleSort(Comparator<T> ord) {
        super(ord);
    }

    @Override
    public void sort(T[] a) {
        for (int j = a.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (ord.compare(a[i], a[i + 1]) > 0) {
                    swap(a, i, i + 1);
                }
            }
        }
    }

}
