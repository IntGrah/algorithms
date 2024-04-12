package com.intgrah.algorithms.set;

public interface DisjointSet<T, R extends DisjointSet.Reference<T, R>> {

    R push(T v);

    void clear();

    interface Reference<T, R> {

        T getValue();

        R find();

        R unite(R r);

    }

}
