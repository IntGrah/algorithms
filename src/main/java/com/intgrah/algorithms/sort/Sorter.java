package com.intgrah.algorithms.sort;

import java.util.Comparator;

public abstract class Sorter<T> {

    final Comparator<T> ord;

    public Sorter(Comparator<T> ord) {
        this.ord = ord;
    }

    public abstract void sort(T[] a);

}
