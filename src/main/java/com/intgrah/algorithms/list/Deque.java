package com.intgrah.algorithms.list;

public interface Deque<T> extends Queue<T>, Stack<T> {

    T getBack();

    T popBack();

}
