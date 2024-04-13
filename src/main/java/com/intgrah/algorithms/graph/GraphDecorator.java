package com.intgrah.algorithms.graph;

import java.util.Map;
import java.util.Set;

public abstract class GraphDecorator<V, W> implements Graph<V, W> {

    private final Graph<V, W> g;

    protected GraphDecorator(Graph<V, W> g) { this.g = g; }

    @Override
    public void putVertex(V v) { g.putVertex(v); }

    @Override
    public Set<V> getVertices() { return g.getVertices(); }

    @Override
    public void removeVertex(V v) { g.removeVertex(v); }

    @Override
    public Set<V> getNeighbors(V v) { return g.getNeighbors(v); }

    @Override
    public Map<V, W> getEdges(V v) { return g.getEdges(v); }

    @Override
    public W getEdge(V u, V v) { return g.getEdge(u, v); }

    @Override
    public void putEdge(V u, V v, W w) { g.putEdge(u, v, w); }

}
