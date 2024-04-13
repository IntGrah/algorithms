package com.intgrah.algorithms.graph.flow;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.HashMapGraph;
import com.intgrah.algorithms.util.OrderedGroup;
import org.junit.jupiter.api.Test;

public abstract class FordFulkersonTest {

    @Test
    public void simpleTest() {
        FordFulkerson<Integer, Integer> ff = getInstance();
        Graph<Integer, Integer> g = new HashMapGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putEdge(0, 1, 40);
        g.putEdge(0, 3, 20);
        g.putEdge(1, 3, 20);
        g.putEdge(1, 2, 30);
        g.putEdge(2, 3, 10);

        Graph<Integer, Integer> flow = ff.maxFlow(g, 0, 3);
        for (int u : flow.getVertices()) {
            for (int v : flow.getNeighbors(u)) {
                System.out.printf("%d, %d: %d\n", u, v, flow.getEdge(u, v));
            }
        }
    }

    protected abstract FordFulkerson<Integer, Integer> getInstance();

    protected OrderedGroup<Integer> getOSG() {
        return new OrderedGroup<>() {
            @Override
            public Integer neg(Integer a) { return -a; }

            @Override
            public Integer zero() { return 0; }

            @Override
            public Integer add(Integer a, Integer b) { return a + b; }

            @Override
            public int compare(Integer a, Integer b) { return a - b; }
        };
    }

}
