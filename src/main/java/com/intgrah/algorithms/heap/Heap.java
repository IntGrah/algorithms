package com.intgrah.algorithms.heap;

public interface Heap<K extends Comparable<K>> extends AbstractHeap<K, Heap.Decreasable<K>> {

    interface Decreasable<K> {

        K getKey();

        void decreaseKey(K k) throws KeyException;

    }


    class KeyException extends RuntimeException {

    }

}
