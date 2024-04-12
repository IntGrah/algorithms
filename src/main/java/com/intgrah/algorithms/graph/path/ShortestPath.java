package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.Map;

public abstract class ShortestPath<V, W> {

    final OrderedSemigroup<W> osg;

    public ShortestPath(OrderedSemigroup<W> osg) {
        this.osg = osg;
    }

    public abstract Map<V, W> path(Graph<V, W> g, V s);

}
