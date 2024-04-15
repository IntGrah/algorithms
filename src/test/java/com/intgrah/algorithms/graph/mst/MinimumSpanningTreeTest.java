package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.UndirectedGraphTest;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class MinimumSpanningTreeTest {

    @Test
    public void simpleTest() {
        Graph<Integer, Integer> g = UndirectedGraphTest.simple();
        MinimumSpanningTree<Integer, Integer> mstAlg = getInstance(g, Integer::compareTo);

        Graph<Integer, Integer> mst = mstAlg.minimumSpanningTree(0);

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

    protected abstract <V, W> MinimumSpanningTree<V, W> getInstance(
            Graph<V, W> g, Comparator<W> ord
    );

    @Test
    public void anotherTest() {
        Graph<Integer, Integer> g = UndirectedGraphTest.anotherGraph();
        MinimumSpanningTree<Integer, Integer> mstAlg = getInstance(g, Integer::compareTo);

        Graph<Integer, Integer> mst = mstAlg.minimumSpanningTree(0);

        assertEquals(4, mst.getEdge(0, 1));
        assertEquals(7, mst.getEdge(2, 3));
        assertEquals(9, mst.getEdge(3, 4));
        assertEquals(4, mst.getEdge(2, 5));
        assertEquals(2, mst.getEdge(5, 6));
//        assertEquals(1, mst.getEdge(6, 7));
        assertEquals(2, mst.getEdge(2, 8));
        assertNull(mst.getEdge(7, 8));
    }

}
