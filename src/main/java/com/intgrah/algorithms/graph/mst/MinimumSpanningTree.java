package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;

import java.util.Comparator;

public abstract class MinimumSpanningTree<V, W> {

    final Comparator<W> comp;

    public MinimumSpanningTree(Comparator<W> c) {
        comp = c;
    }

    public abstract Graph<V, W> minimumSpanningTree(Graph<V, W> g, V s);

}
