package com.intgrah.algorithms.list;

public interface Stack<T> extends List {

    T getFront();

    AbstractList<T>.Node pushFront(T v);

    T popFront();

}
