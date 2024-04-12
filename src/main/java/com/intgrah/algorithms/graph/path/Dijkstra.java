package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.heap.PairingHeap;
import com.intgrah.algorithms.heap.Heap;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.HashMap;
import java.util.Map;

public class Dijkstra<V, W> extends ShortestPath<V, W> {

    private final Heap<Item> q;
    private final Map<V, W> dist = new HashMap<>();
    private final Map<V, Heap.Decreasable<Item>> node = new HashMap<>();

    public Dijkstra(OrderedSemigroup<W> osg) {
        this(new PairingHeap<>(), osg);
    }

    public Dijkstra(Heap<Item> pq, OrderedSemigroup<W> osg) {
        super(osg);
        q = pq;
    }

    public Map<V, W> path(Graph<V, W> g, V s) {
        q.clear();
        dist.clear();
        node.clear();
        node.put(s, q.push(new Item(s, osg.zero())));
        dist.put(s, osg.zero());
        while (!q.isEmpty()) {
            Item item = q.popMin();
            V u = item.vertex;
            W du = item.distance;
            for (V v : g.getNeighbors(u)) {
                W dv = dist.get(v);
                W dvAlt = osg.add(du, g.getEdge(u, v));
                if (dv == null || osg.compare(dvAlt, dv) < 0) {
                    dist.put(v, dvAlt);
                    Item iv = new Item(v, dvAlt);
                    if (dv == null) {
                        node.put(v, q.push(iv));
                    } else {
                        node.get(v).decreaseKey(iv);
                    }
                }
            }
        }
        return dist;
    }

    public class Item implements Comparable<Item> {

        private final V vertex;
        private final W distance;

        private Item(V v, W d) {
            vertex = v;
            distance = d;
        }

        @Override
        public int compareTo(Dijkstra<V, W>.Item p) {
            return osg.compare(distance, p.distance);
        }

    }

}
