package com.intgrah.algorithms.list;

import com.intgrah.algorithms.util.EmptyException;

public interface Queue<T> extends List<T> {

    T getFront() throws EmptyException;

    T popFront() throws EmptyException;

    T getBack();

    List.Node<T> pushBack(T v);

    int size();

    void clear();

}
