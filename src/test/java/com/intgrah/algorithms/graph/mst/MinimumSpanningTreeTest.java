package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.UndirectedHashMapGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class MinimumSpanningTreeTest {

    @Test
    public void simpleTest() {
        MinimumSpanningTree<Integer, Integer> mstAlg = getInstance();

        Graph<Integer, Integer> g = new UndirectedHashMapGraph<>();
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

        Graph<Integer, Integer> mst = mstAlg.minimumSpanningTree(g, 0);

        assertNull(mst.getEdge(0, 1));
        assertNull(mst.getEdge(0, 2));
        assertEquals(5, mst.getEdge(0, 3));
        assertNull(mst.getEdge(0, 4));
        assertNull(mst.getEdge(1, 0));
        assertEquals(1, mst.getEdge(1, 2));
        assertEquals(3, mst.getEdge(1, 3));
        assertNull(mst.getEdge(1, 4));
        assertNull(mst.getEdge(2, 3));
        assertNull(mst.getEdge(2, 4));
        assertEquals(2, mst.getEdge(3, 4));
    }

    protected abstract MinimumSpanningTree<Integer, Integer> getInstance();

    @Test
    public void anotherTest() {
        MinimumSpanningTree<Integer, Integer> mstAlg = getInstance();

        Graph<Integer, Integer> g = new UndirectedHashMapGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putVertex(4);
        g.putVertex(5);
        g.putVertex(6);
        g.putVertex(7);
        g.putVertex(8);
        g.putEdge(0, 1, 4);
        g.putEdge(0, 7, 8);
        g.putEdge(1, 2, 8);
        g.putEdge(1, 7, 11);
        g.putEdge(2, 3, 7);
        g.putEdge(2, 8, 2);
        g.putEdge(2, 5, 4);
        g.putEdge(3, 4, 9);
        g.putEdge(3, 5, 14);
        g.putEdge(4, 5, 10);
        g.putEdge(5, 6, 2);
        g.putEdge(6, 7, 1);
        g.putEdge(6, 8, 6);
        g.putEdge(7, 8, 7);

        Graph<Integer, Integer> mst = mstAlg.minimumSpanningTree(g, 0);

        assertEquals(4, mst.getEdge(0, 1));
        assertEquals(7, mst.getEdge(2, 3));
        assertEquals(9, mst.getEdge(3, 4));
        assertEquals(4, mst.getEdge(2, 5));
        assertEquals(2, mst.getEdge(5, 6));
        assertEquals(1, mst.getEdge(6, 7));
        assertEquals(2, mst.getEdge(2, 8));

        assertNull(mst.getEdge(7, 8));
    }

}
