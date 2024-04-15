package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.heap.BinaryHeap;
import com.intgrah.algorithms.heap.Heap;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BasicDijkstra<V, W> extends ShortestPath<V, W> {

    private final Heap<Item> heap;
    private final Map<V, W> dist = new HashMap<>();

    public BasicDijkstra(Graph<V, W> g, OrderedSemigroup<W> osg) {
        super(g, osg);
        heap = new BinaryHeap<>(Comparator.comparing(i -> i.distance, osg));
    }

    public Map<V, W> path(V s) {
        heap.clear();
        heap.push(new Item(s, osg.zero()));
        dist.clear();
        dist.put(s, osg.zero());
        while (!heap.isEmpty()) {
            Item item = heap.popMin();
            V u = item.vertex;
            W du = item.distance;
            if (osg.compare(du, dist.get(u)) > 0)
                continue;
            for (V v : graph.getNeighbors(u)) {
                W dv = dist.get(v);
                W dvAlt = osg.add(du, graph.getEdge(u, v));
                if (dv == null || osg.compare(dvAlt, dv) < 0) {
                    dist.put(v, dvAlt);
                    heap.push(new Item(v, dvAlt));
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
