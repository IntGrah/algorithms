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
        Graph<V, W> flow = new HashMapGraph<>();
        Graph<V, W> residual = new GraphDecorator<>(flow) {
            @Override
            public W getEdge(V u, V v) {
                W f = super.getEdge(u, v);
                W c = capacity.getEdge(u, v);
                if (c == null) return og.neg(f);
                else return og.sub(c, f);
            }
        };
        for (V u : capacity.getVertices())
            flow.putVertex(u);
        for (V u : capacity.getVertices()) {
            for (V v : capacity.getNeighbors(u)) {
                flow.putEdge(u, v, og.zero());
                flow.putEdge(v, u, og.zero());
            }
        }
        while (true) {
            Iterable<V> path = augmentingPath(residual, s, t);
            if (path == null)
                return flow;
            W df = null;
            Iterator<V> us = path.iterator();
            Iterator<V> vs = path.iterator();
            vs.next();
            while (vs.hasNext()) {
                V u = us.next();
                V v = vs.next();
                W altDf = residual.getEdge(u, v);
                if (df == null || og.compare(altDf, df) < 0)
                    df = altDf;
            }

            us = path.iterator();
            vs = path.iterator();
            vs.next();
            while (vs.hasNext()) {
                V u = us.next();
                V v = vs.next();
                flow.putEdge(u, v, og.add(flow.getEdge(u, v), df));
                flow.putEdge(v, u, og.sub(flow.getEdge(v, u), df));
            }
        }
    }

    protected abstract Iterable<V> augmentingPath(
            Graph<V, W> residual, V s, V t
    );

}
