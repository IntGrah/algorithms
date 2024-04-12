package com.intgrah.algorithms.graph;

public class UndirectedAdjacencyListGraph<V, W> extends AdjacencyListGraph<V, W> {

    @Override
    public void putEdge(V u, V v, W w) {
        super.putEdge(u, v, w);
        super.putEdge(v, u, w);
    }

}
