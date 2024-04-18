package com.intgrah.algorithms.list;

public interface Deque<T, R> extends Queue<T, R>, Stack<T, R> {

    T getBack();

    T popBack();

}
