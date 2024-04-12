package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.util.EmptyException;

public interface AbstractHeap<K extends Comparable<K>, R> {

    R push(K k);

    K popMin() throws EmptyException;

    K getMin() throws EmptyException;

    boolean isEmpty();

    void clear();

}
