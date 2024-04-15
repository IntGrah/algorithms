package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;

import java.util.Comparator;

public abstract class MinimumSpanningTree<V, W> {

    protected final Graph<V, W> graph;
    protected final Comparator<W> ord;

    public MinimumSpanningTree(Graph<V, W> g, Comparator<W> ord) {
        graph = g;
        this.ord = ord;
    }

    public abstract Graph<V, W> minimumSpanningTree(V s);

}
