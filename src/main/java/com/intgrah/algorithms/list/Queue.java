package com.intgrah.algorithms.list;

public interface Queue<K, R> extends List<K> {

    K getFront();

    K popFront();

    K getBack();

    R pushBack(K v);

}
