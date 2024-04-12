package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.heap.PairingHeap;

import java.util.Comparator;

public class PrimTest extends MinimumSpanningTreeTest {

    public Prim<Integer, Integer> getInstance() {
        return new Prim<Integer, Integer>(new PairingHeap<>(), Comparator.naturalOrder());
    }

}
