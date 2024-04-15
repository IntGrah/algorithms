package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.Map;

public abstract class ShortestPath<V, W> {

    protected final Graph<V, W> graph;
    protected final OrderedSemigroup<W> osg;

    public ShortestPath(Graph<V, W> g, OrderedSemigroup<W> osg) {
        graph = g;
        this.osg = osg;
    }

    public abstract Map<V, W> path(V s);

}
