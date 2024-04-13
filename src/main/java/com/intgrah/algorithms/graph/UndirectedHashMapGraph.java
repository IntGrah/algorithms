package com.intgrah.algorithms.graph;

public class UndirectedHashMapGraph<V, W> extends HashMapGraph<V, W> {

    @Override
    public void putEdge(V u, V v, W w) {
        super.putEdge(u, v, w);
        super.putEdge(v, u, w);
    }

}
