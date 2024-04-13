package com.intgrah.algorithms.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapGraph<V, W> implements Graph<V, W> {

    private final Map<V, Map<V, W>> g = new HashMap<>();

    @Override
    public void putVertex(V u) { g.put(u, new HashMap<>()); }

    @Override
    public Set<V> getVertices() { return g.keySet(); }

    @Override
    public void removeVertex(V v) { g.remove(v); }

    @Override
    public Set<V> getNeighbors(V u) { return g.get(u).keySet(); }

    public Map<V, W> getEdges(V u) { return g.get(u); }

    @Override
    public W getEdge(V u, V v) { return g.get(u).get(v); }

    @Override
    public void putEdge(V u, V v, W w) { g.get(u).put(v, w); }

}
