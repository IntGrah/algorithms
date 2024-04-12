package com.intgrah.algorithms.sort;

import java.util.Comparator;

import static java.util.Arrays.copyOfRange;

public class MergeSort<T> extends Sorter<T> {

    public MergeSort(Comparator<T> ord) {
        super(ord);
    }

    @Override
    public void sort(T[] a) {
        for (int w = 1; w < a.length; w *= 2) {
            for (int r = a.length; r > 0; r -= 2 * w) {
                int m = r - w;
                if (m < 1) {
                    continue;
                }
                int l = m > w ? m - w : 0;
                T[] aux = copyOfRange(a, l, m);
                int lm = m - l;
                int i = 0;
                while (l < r) {
                    if (i < lm && (m == r || ord.compare(aux[i], a[m]) <= 0)) {
                        a[l++] = aux[i++];
                    } else {
                        a[l++] = a[m++];
                    }
                }
            }
        }
    }

}
