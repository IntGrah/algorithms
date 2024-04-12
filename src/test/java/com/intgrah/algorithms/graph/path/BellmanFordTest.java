package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.AdjacencyListGraph;
import com.intgrah.algorithms.graph.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BellmanFordTest extends ShortestPathTest {

    @Test
    public void negativeWeightCycleTest() {
        BellmanFord<Integer, Integer> bmf = getInstance();

        Graph<Integer, Integer> g = new AdjacencyListGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putEdge(0, 1, 4);
        g.putEdge(1, 2, -3);
        g.putEdge(2, 3, 5);
        g.putEdge(3, 1, -3);

        assertThrows(BellmanFord.NegativeWeightCycleException.class, () -> bmf.path(g, 0));
    }

    @Override
    public BellmanFord<Integer, Integer> getInstance() {
        return new BellmanFord<>(getOSG());
    }

}
