package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.GraphTest;
import com.intgrah.algorithms.util.OrderedSemigroup;
import com.intgrah.algorithms.util.OrderedSemigroupTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ShortestPathTest {

    @Test
    public void simpleTest() {
        Graph<Integer, Integer> g = GraphTest.simple();
        OrderedSemigroup<Integer> osg = OrderedSemigroupTest.integer();
        ShortestPath<Integer, Integer> shortestPath = getInstance(g, osg);
        Map<Integer, Integer> d = shortestPath.path(0);
        assertEquals(0, d.get(0));
        assertEquals(8, d.get(1));
        assertEquals(9, d.get(2));
        assertEquals(5, d.get(3));
        assertEquals(7, d.get(4));
    }

    protected abstract <V, W> ShortestPath<V, W> getInstance(
            Graph<V, W> g, OrderedSemigroup<W> osg
    );

    @Test
    public void zeroWeightCycleTest() {
        Graph<Integer, Integer> g = GraphTest.zeroWeightCycle();
        OrderedSemigroup<Integer> osg = OrderedSemigroupTest.integer();
        ShortestPath<Integer, Integer> shortestPath = getInstance(g, osg);
        Map<Integer, Integer> d = shortestPath.path(0);
        assertEquals(0, d.get(0));
        assertEquals(4, d.get(1));
        assertEquals(1, d.get(2));
        assertEquals(7, d.get(3));
    }

}
