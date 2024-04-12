package com.intgrah.algorithms.list;

import com.intgrah.algorithms.util.EmptyException;

public interface Stack<T> extends List<T> {

    T getFront() throws EmptyException;

    List.Node<T> pushFront(T v);

    T popFront() throws EmptyException;

    int size();

    void clear();

}
