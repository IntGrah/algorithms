package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

public class FloydWarshallTest extends AllShortestPathsTest {

    @Override
    protected <V, W> FloydWarshall<V, W> getInstance(Graph<V, W> g, OrderedSemigroup<W> osg) {
        return new FloydWarshall<>(g, osg);
    }

}
