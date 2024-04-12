package com.intgrah.algorithms.graph.mst;

import java.util.Comparator;

public class KruskalTest extends MinimumSpanningTreeTest {

    public Kruskal<Integer, Integer> getInstance() {
        return new Kruskal<Integer, Integer>(Comparator.naturalOrder());
    }

}
