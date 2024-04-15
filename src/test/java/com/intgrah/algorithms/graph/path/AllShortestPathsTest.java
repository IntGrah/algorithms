package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.GraphTest;
import com.intgrah.algorithms.util.OrderedSemigroup;
import com.intgrah.algorithms.util.OrderedSemigroupTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AllShortestPathsTest {

    @Test
    public void simpleTest() {
        Graph<Integer, Integer> g = GraphTest.simple();
        OrderedSemigroup<Integer> osg = OrderedSemigroupTest.integer();
        AllShortestPaths<Integer, Integer> allShortestPaths = getInstance(g, osg);
        ShortestPath<Integer, Integer> shortestPath = new Dijkstra<>(g, osg);

        Graph<Integer, Integer> d = allShortestPaths.allPaths();

        assertEquals(shortestPath.path(0), d.getEdges(0));
        assertEquals(shortestPath.path(1), d.getEdges(1));
        assertEquals(shortestPath.path(2), d.getEdges(2));
        assertEquals(shortestPath.path(3), d.getEdges(3));
        assertEquals(shortestPath.path(4), d.getEdges(4));
    }

    protected abstract <V, W> AllShortestPaths<V, W> getInstance(
            Graph<V, W> g, OrderedSemigroup<W> osg
    );

    @Test
    public void zeroWeightCycleTest() {
        Graph<Integer, Integer> g = GraphTest.zeroWeightCycle();
        OrderedSemigroup<Integer> osg = OrderedSemigroupTest.integer();
        AllShortestPaths<Integer, Integer> allShortestPaths = getInstance(g, osg);
        ShortestPath<Integer, Integer> shortestPath = new BellmanFord<>(g, osg);

        Graph<Integer, Integer> d = allShortestPaths.allPaths();

        assertEquals(shortestPath.path(0), d.getEdges(0));
        assertEquals(shortestPath.path(1), d.getEdges(1));
        assertEquals(shortestPath.path(2), d.getEdges(2));
        assertEquals(shortestPath.path(3), d.getEdges(3));
    }

}
