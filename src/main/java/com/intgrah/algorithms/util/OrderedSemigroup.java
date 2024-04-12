package com.intgrah.algorithms.util;

import java.util.Comparator;

public interface OrderedSemigroup<T> extends Comparator<T> {

    T zero();

    T add(T a, T b);

}
