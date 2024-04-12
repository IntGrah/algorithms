package com.intgrah.algorithms.list;

import com.intgrah.algorithms.util.EmptyException;

public interface Deque<T> extends Queue<T>, Stack<T> {

    T getBack() throws EmptyException;

    T popBack() throws EmptyException;

}
