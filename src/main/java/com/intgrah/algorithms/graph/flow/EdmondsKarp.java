package com.intgrah.algorithms.graph.flow;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.list.LinkedList;
import com.intgrah.algorithms.util.OrderedGroup;

import java.util.HashMap;
import java.util.Map;

public class EdmondsKarp<V, W> extends FordFulkerson<V, W> {

    public EdmondsKarp(OrderedGroup<W> og) { super(og); }

    @Override
    protected Iterable<V> augmentingPath(Graph<V, W> residual, V s, V t) {
        LinkedList<V> q = new LinkedList<>();
        q.pushBack(s);
        Map<V, V> prev = new HashMap<>();
        prev.put(s, null);
        while (true) {
            if (q.isEmpty())
                return null;
            if (prev.containsKey(t))
                break;
            V u = q.popFront();
            for (V v : residual.getNeighbors(u)) {
                if (v == s || prev.containsKey(v))
                    continue;
                if (og.compare(residual.getEdge(u, v), og.zero()) > 0) {
                    prev.put(v, u);
                    q.pushBack(v);
                }
            }
        }
        LinkedList<V> path = new LinkedList<>();
        for (V v = t; v != null; v = prev.get(v))
            path.pushFront(v);
        return path;
    }

}
