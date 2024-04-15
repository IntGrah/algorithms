package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class LeftistTreeTest extends HeapTest {

    @Override
    protected <T> LeftistTree<T> getInstance(Comparator<T> ord) {
        return new LeftistTree<>(ord);
    }

}
