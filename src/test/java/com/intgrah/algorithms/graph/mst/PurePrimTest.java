package com.intgrah.algorithms.graph.mst;

import java.util.Comparator;

public class PurePrimTest extends MinimumSpanningTreeTest {

    protected PurePrim<Integer, Integer> getInstance() {
        return new PurePrim<Integer, Integer>(Comparator.naturalOrder());
    }

}
