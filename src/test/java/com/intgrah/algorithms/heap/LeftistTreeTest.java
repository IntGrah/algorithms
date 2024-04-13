package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class LeftistTreeTest extends HeapTest {

    @Override
    protected LeftistTree<Integer> getInstance() {
        return new LeftistTree<Integer>(Comparator.naturalOrder());
    }

}
