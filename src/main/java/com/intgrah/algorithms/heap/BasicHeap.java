package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.util.EmptyException;

public interface BasicHeap<K extends Comparable<K>> extends AbstractHeap<K, Void> {

    Void push(K k);

    K popMin() throws EmptyException;

    K getMin() throws EmptyException;

    boolean isEmpty();

    void clear();

}
