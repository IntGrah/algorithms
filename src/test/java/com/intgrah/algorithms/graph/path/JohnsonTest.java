package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedGroup;
import com.intgrah.algorithms.util.OrderedSemigroup;

public class JohnsonTest extends AllShortestPathsTest {

    @Override
    protected <V, W> Johnson<V, W> getInstance(Graph<V, W> g, OrderedSemigroup<W> osg) {
        return new Johnson<>(g, (OrderedGroup<W>) osg);
    }

}
