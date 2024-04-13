package com.intgrah.algorithms.graph.mst;

import java.util.Comparator;

public class PrimTest extends MinimumSpanningTreeTest {

    protected Prim<Integer, Integer> getInstance() {
        return new Prim<Integer, Integer>(Comparator.naturalOrder());
    }

}
