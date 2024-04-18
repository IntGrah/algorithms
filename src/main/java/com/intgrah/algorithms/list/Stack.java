package com.intgrah.algorithms.list;

public interface Stack<K, R> extends List<K> {

    K getFront();

    R pushFront(K v);

    K popFront();

}
