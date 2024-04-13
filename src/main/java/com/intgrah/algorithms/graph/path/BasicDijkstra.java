package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.heap.BinaryHeap;
import com.intgrah.algorithms.heap.Heap;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BasicDijkstra<V, W> extends ShortestPath<V, W> {

    private final Heap<Item> q;
    private final Map<V, W> dist = new HashMap<>();

    public BasicDijkstra(OrderedSemigroup<W> osg) {
        super(osg);
        q = new BinaryHeap<>(Comparator.comparing(i -> i.distance, osg));
    }

    public Map<V, W> path(Graph<V, W> g, V s) {
        q.clear();
        q.push(new Item(s, osg.zero()));
        dist.clear();
        dist.put(s, osg.zero());
        while (!q.isEmpty()) {
            Item item = q.popMin();
            V u = item.vertex;
            W du = item.distance;
            if (osg.compare(du, dist.get(u)) > 0)
                continue;
            for (V v : g.getNeighbors(u)) {
                W dv = dist.get(v);
                W dvAlt = osg.add(du, g.getEdge(u, v));
                if (dv == null || osg.compare(dvAlt, dv) < 0) {
                    dist.put(v, dvAlt);
                    q.push(new Item(v, dvAlt));
                }
            }
        }
        return dist;
    }

    private class Item {

        private final V vertex;
        private final W distance;

        private Item(V v, W d) {
            vertex = v;
            distance = d;
        }

    }

}
