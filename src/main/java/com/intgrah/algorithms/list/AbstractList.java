package com.intgrah.algorithms.list;

public abstract class AbstractList<T> implements Iterable<T> {

    protected transient int size = 0;

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    abstract void clear();

}
