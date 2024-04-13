package com.intgrah.algorithms.heap;

import java.util.Comparator;

public abstract class Heap<K> {

    protected final Comparator<K> ord;

    public Heap(Comparator<K> ord) { this.ord = ord; }

    public abstract void push(K k);

    public K popMin() {
        K min = getMin();
        deleteMin();
        return min;
    }

    abstract K getMin();

    abstract void deleteMin();

    public abstract boolean isEmpty();

    public abstract void clear();

}
