package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;

import java.util.Comparator;

public class PrimTest extends MinimumSpanningTreeTest {

    protected <V, W> Prim<V, W> getInstance(Graph<V, W> g, Comparator<W> ord) {
        return new Prim<>(g, ord);
    }

}
