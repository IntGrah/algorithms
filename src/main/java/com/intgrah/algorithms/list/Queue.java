package com.intgrah.algorithms.list;

public interface Queue<T> extends List {

    T getFront();

    T popFront();

    T getBack();

    AbstractList<T>.Node pushBack(T v);

}
