package com.intgrah.algorithms.graph.flow;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.list.LinkedList;
import com.intgrah.algorithms.list.Queue;
import com.intgrah.algorithms.util.OrderedGroup;

import java.util.HashMap;
import java.util.Map;

public class EdmondsKarp<V, W> extends FordFulkerson<V, W> {

    public EdmondsKarp(OrderedGroup<W> og) { super(og); }

    @Override
    protected Iterable<V> augmentingPath(Graph<V, W> flow, Graph<V, W> capacity, V s, V t) {
        Queue<V> q = new LinkedList<>();
        q.pushBack(s);
        Map<V, V> prev = new HashMap<>();
        prev.put(s, null);
        while (true) {
            if (q.isEmpty())
                return null;
            if (prev.containsKey(t))
                break;
            V u = q.popFront();
            for (V v : flow.getNeighbors(u)) {
                if (v == s || prev.containsKey(v))
                    continue;
                if (og.compare(flow.getEdge(u, v), capacity.getEdge(u, v)) < 0) {
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
