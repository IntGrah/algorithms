package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

public class DijkstraTest extends ShortestPathTest {

    @Override
    protected <V, W> Dijkstra<V, W> getInstance(Graph<V, W> g, OrderedSemigroup<W> osg) {
        return new Dijkstra<>(g, osg);
    }

}
