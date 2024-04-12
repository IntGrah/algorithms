package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.AdjacencyListGraph;
import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AllShortestPathsTest {

    @Test
    public void simpleTest() {
        AllShortestPaths<Integer, Integer> allShortestPaths = getInstance();
        ShortestPath<Integer, Integer> shortestPath = new Dijkstra<>(getOSG());

        Graph<Integer, Integer> g = new AdjacencyListGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putVertex(4);
        g.putEdge(0, 1, 10);
        g.putEdge(0, 3, 5);
        g.putEdge(1, 2, 1);
        g.putEdge(1, 3, 2);
        g.putEdge(2, 4, 4);
        g.putEdge(3, 1, 3);
        g.putEdge(3, 2, 9);
        g.putEdge(3, 4, 2);
        g.putEdge(4, 0, 7);
        g.putEdge(4, 2, 6);

        Map<Integer, Map<Integer, Integer>> d = allShortestPaths.allPaths(g);

        assertEquals(shortestPath.path(g, 0), d.get(0));
        assertEquals(shortestPath.path(g, 1), d.get(1));
        assertEquals(shortestPath.path(g, 2), d.get(2));
        assertEquals(shortestPath.path(g, 3), d.get(3));
        assertEquals(shortestPath.path(g, 4), d.get(4));
    }

    public abstract AllShortestPaths<Integer, Integer> getInstance();

    public OrderedSemigroup<Integer> getOSG() {
        return new OrderedSemigroup<>() {
            @Override
            public Integer zero() {
                return 0;
            }

            @Override
            public Integer add(Integer a, Integer b) {
                return a + b;
            }

            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        };
    }

    @Test
    public void zeroWeightCycleTest() {
        AllShortestPaths<Integer, Integer> allShortestPaths = getInstance();
        ShortestPath<Integer, Integer> shortestPath = new BellmanFord<>(getOSG());

        Graph<Integer, Integer> g = new AdjacencyListGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putEdge(0, 1, 4);
        g.putEdge(1, 2, -3);
        g.putEdge(2, 3, 6);
        g.putEdge(3, 1, -3);

        Map<Integer, Map<Integer, Integer>> d = allShortestPaths.allPaths(g);

        assertEquals(shortestPath.path(g, 0), d.get(0));
        assertEquals(shortestPath.path(g, 1), d.get(1));
        assertEquals(shortestPath.path(g, 2), d.get(2));
        assertEquals(shortestPath.path(g, 3), d.get(3));
    }

}
