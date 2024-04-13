package com.intgrah.algorithms.graph;

import java.util.Map;
import java.util.Set;

public interface Graph<V, W> {

    void putVertex(V v);

    Set<V> getVertices();

    void removeVertex(V v);

    Set<V> getNeighbors(V v);

    Map<V, W> getEdges(V u);

    W getEdge(V u, V v);

    void putEdge(V u, V v, W w);

}
