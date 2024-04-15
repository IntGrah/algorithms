package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;

import java.util.Comparator;

public class PurePrimTest extends MinimumSpanningTreeTest {

    protected <V, W> PurePrim<V, W> getInstance(Graph<V, W> g, Comparator<W> ord) {
        return new PurePrim<>(g, ord);
    }

}
