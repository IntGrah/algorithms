package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.heap.BasicBinaryHeap;

import java.util.Comparator;

public class PurePrimTest extends MinimumSpanningTreeTest {

    public PurePrim<Integer, Integer> getInstance() {
        return new PurePrim<Integer, Integer>(new BasicBinaryHeap<>(), Comparator.naturalOrder());
    }

}
