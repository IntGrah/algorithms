package com.intgrah.algorithms.sort;

import java.util.Comparator;

public class InsertionSort<T> extends Sorter<T> {

    public InsertionSort(Comparator<T> ord) {
        super(ord);
    }

    @Override
    public void sort(T[] a) {
        for (int j = 1; j < a.length; j++) {
            T x = a[j];
            int i = j;
            while (i > 0 && ord.compare(a[i - 1], x) > 0)
                a[i--] = a[i];
            a[i] = x;
        }
    }

}
