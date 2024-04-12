package com.intgrah.algorithms.sort;

import java.util.Comparator;

import static com.intgrah.algorithms.util.Util.swap;

public class SelectionSort<T> extends Sorter<T> {

    public SelectionSort(Comparator<T> ord) {
        super(ord);
    }

    @Override
    public void sort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++)
                if (ord.compare(a[j], a[min]) < 0) {
                    min = j;
                }
            swap(a, i, min);
        }
    }

}
