package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;

import java.util.Comparator;

public class KruskalTest extends MinimumSpanningTreeTest {

    protected <V, W> Kruskal<V, W> getInstance(Graph<V, W> g, Comparator<W> ord) {
        return new Kruskal<>(g, ord);
    }

}
