package com.intgrah.algorithms.tree;

public interface SearchTree<K extends Comparable<K>> {

    void insert(K k);

    K search(Comparable<K> k);

    void delete(Comparable<K> k);

}
