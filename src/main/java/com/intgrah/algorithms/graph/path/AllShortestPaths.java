package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

public abstract class AllShortestPaths<V, W> {

    protected final Graph<V, W> graph;
    protected final OrderedSemigroup<W> osg;

    public AllShortestPaths(Graph<V, W> g, OrderedSemigroup<W> osg) {
        graph = g;
        this.osg = osg;
    }

    public abstract Graph<V, W> allPaths();

}
