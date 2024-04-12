package com.intgrah.algorithms.sort;

import java.util.Comparator;

import static com.intgrah.algorithms.util.Util.swap;

public class HeapSort<T> extends Sorter<T> {

    public HeapSort(Comparator<T> ord) {
        super(ord);
    }

    @Override
    public void sort(T[] a) {
        int start = a.length / 2;
        int end = a.length;
        while (end > 1) {
            if (start == 0) {
                end--;
                swap(a, 0, end);
            } else {
                start--;
            }
            int i = start;
            while (true) {
                int l = 2 * i + 1;
                int r = 2 * i + 2;
                if (r < end && ord.compare(a[l], a[r]) < 0 && ord.compare(a[i], a[r]) < 0) {
                    swap(a, i, r);
                    i = r;
                } else if (l < end && ord.compare(a[i], a[l]) < 0) {
                    swap(a, i, l);
                    i = l;
                } else {
                    break;
                }
            }
        }
    }

}
