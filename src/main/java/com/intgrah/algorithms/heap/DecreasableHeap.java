package com.intgrah.algorithms.heap;

import java.util.Comparator;

public abstract class DecreasableHeap<K> extends Heap<K> {

    public DecreasableHeap(Comparator<K> ord) { super(ord); }

    public void push(K k) { pushRef(k); }

    public abstract Decreasable pushRef(K k);

    public abstract class Decreasable {

        protected K key;

        protected Decreasable(K k) { key = k; }

        public K getKey() { return key; }

        public abstract void decreaseKey(K k);

    }

}
