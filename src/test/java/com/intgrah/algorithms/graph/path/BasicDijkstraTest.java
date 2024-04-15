package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

public class BasicDijkstraTest extends ShortestPathTest {

    @Override
    protected <V, W> BasicDijkstra<V, W> getInstance(Graph<V, W> g, OrderedSemigroup<W> osg) {
        return new BasicDijkstra<>(g, osg);
    }

}
