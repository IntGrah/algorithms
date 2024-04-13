package com.intgrah.algorithms.graph.flow;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.GraphDecorator;
import com.intgrah.algorithms.graph.HashMapGraph;
import com.intgrah.algorithms.util.OrderedGroup;

import java.util.Iterator;

public abstract class FordFulkerson<V, W> {

    protected final OrderedGroup<W> og;

    public FordFulkerson(OrderedGroup<W> og) { this.og = og; }

    public Graph<V, W> maxFlow(Graph<V, W> capacity, V s, V t) {
        assert s != t;
        capacity = new GraphDecorator<>(capacity) {
            @Override
            public W getEdge(V u, V v) {
                W w = super.getEdge(u, v);
                return w == null ? og.zero() : w;
            }
        };
        Graph<V, W> flow = new HashMapGraph<>();
        for (V u : capacity.getVertices())
            flow.putVertex(u);
        for (V u : capacity.getVertices()) {
            for (V v : capacity.getNeighbors(u)) {
                flow.putEdge(u, v, og.zero());
                flow.putEdge(v, u, og.zero());
            }
        }
        while (true) {
            for (V u : capacity.getVertices()) {
                for (V v : capacity.getNeighbors(u)) {
                    System.out.printf("%s %s: %s\n", u, v, flow.getEdge(u, v));
                }
            }
            Iterable<V> path = augmentingPath(flow, capacity, s, t);
            if (path == null)
                return flow;
            W df = null;
            Iterator<V> iter = path.iterator();
            if (iter.hasNext()) {
                V u = iter.next();
                while (iter.hasNext()) {
                    V v = iter.next();
                    W altDf = og.sub(capacity.getEdge(u, v), flow.getEdge(u, v));
                    if (df == null || og.compare(altDf, df) < 0)
                        df = altDf;
                    u = v;
                }
            }

            iter = path.iterator();
            if (iter.hasNext()) {
                V u = iter.next();
                while (iter.hasNext()) {
                    V v = iter.next();
                    flow.putEdge(u, v, og.add(flow.getEdge(u, v), df));
                    flow.putEdge(v, u, og.sub(flow.getEdge(v, u), df));
                    u = v;
                }
            }
        }
    }

    protected abstract Iterable<V> augmentingPath(Graph<V, W> flow, Graph<V, W> capacity, V s, V t);

}
