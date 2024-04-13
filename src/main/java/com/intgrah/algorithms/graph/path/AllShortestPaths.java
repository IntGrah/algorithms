package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

public abstract class AllShortestPaths<V, W> {

    final OrderedSemigroup<W> osg;

    public AllShortestPaths(OrderedSemigroup<W> osg) { this.osg = osg; }

    public abstract Graph<V, W> allPaths(Graph<V, W> g);

}
