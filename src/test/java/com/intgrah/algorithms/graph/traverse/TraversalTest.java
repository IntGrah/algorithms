package com.intgrah.algorithms.graph.traverse;

import com.intgrah.algorithms.graph.AdjacencyListGraph;
import com.intgrah.algorithms.graph.Graph;
import org.junit.jupiter.api.Test;

public abstract class TraversalTest {
    public abstract Traversal<Integer> getInstance();

    @Test
    public void simpleTest() {
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

        Traversal<Integer> t = getInstance();
        for (Integer i : t.traverse(g, 0)) {
            System.out.println(i);
        }
    }
}
