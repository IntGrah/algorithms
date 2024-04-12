package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.Map;

public abstract class AllShortestPaths<V, W> {

    final OrderedSemigroup<W> osg;

    public AllShortestPaths(OrderedSemigroup<W> osg) {
        this.osg = osg;
    }

    public abstract Map<V, Map<V, W>> allPaths(Graph<V, W> g);

}
