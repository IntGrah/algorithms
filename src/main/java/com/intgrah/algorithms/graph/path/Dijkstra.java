package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.heap.DecreasableHeap;
import com.intgrah.algorithms.heap.PairingHeap;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra<V, W> extends ShortestPath<V, W> {

    private final DecreasableHeap<Item> q;
    private final Map<V, W> dist = new HashMap<>();
    private final Map<V, DecreasableHeap<Item>.Decreasable> node = new HashMap<>();

    public Dijkstra(OrderedSemigroup<W> osg) {
        this(new PairingHeap<>(Comparator.comparing(i -> i.distance, osg)), osg);
    }

    public Dijkstra(DecreasableHeap<Item> pq, OrderedSemigroup<W> osg) {
        super(osg);
        q = pq;
    }

    public Map<V, W> path(Graph<V, W> g, V s) {
        q.clear();
        node.clear();
        node.put(s, q.pushRef(new Item(s, osg.zero())));
        dist.clear();
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
                    if (dv == null)
                        node.put(v, q.pushRef(new Item(v, dvAlt)));
                    else
                        node.get(v).decreaseKey(new Item(v, dvAlt));
                }
            }
        }
        return dist;
    }

    public class Item {

        private final V vertex;
        private final W distance;

        private Item(V v, W d) {
            vertex = v;
            distance = d;
        }

    }

}
