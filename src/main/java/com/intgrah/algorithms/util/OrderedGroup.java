package com.intgrah.algorithms.util;

public interface OrderedGroup<T> extends OrderedSemigroup<T> {

    default T sub(T a, T b) {
        return add(a, neg(b));
    }

    T neg(T a);

}
