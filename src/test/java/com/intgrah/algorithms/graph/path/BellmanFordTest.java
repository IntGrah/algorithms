package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.HashMapGraph;
import com.intgrah.algorithms.util.OrderedSemigroup;
import com.intgrah.algorithms.util.OrderedSemigroupTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BellmanFordTest extends ShortestPathTest {

    @Test
    public void negativeWeightCycleTest() {
        Graph<Integer, Integer> g = negativeWeightGraph();
        BellmanFord<Integer, Integer> bmf = getInstance(g, OrderedSemigroupTest.integer());
        assertThrows(BellmanFord.NegativeCycleException.class, () -> bmf.path(0));
    }

    public static Graph<Integer, Integer> negativeWeightGraph() {
        Graph<Integer, Integer> g = new HashMapGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putEdge(0, 1, 4);
        g.putEdge(1, 2, -3);
        g.putEdge(2, 3, 5);
        g.putEdge(3, 1, -3);
        return g;
    }

    @Override
    protected <V, W> BellmanFord<V, W> getInstance(Graph<V, W> g, OrderedSemigroup<W> osg) {
        return new BellmanFord<>(g, osg);
    }

}
