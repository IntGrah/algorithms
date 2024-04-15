package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.heap.DecreasableHeap;
import com.intgrah.algorithms.heap.PairingHeap;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra<V, W> extends ShortestPath<V, W> {

    private final DecreasableHeap<Item> heap;
    private final Map<V, W> dist = new HashMap<>();
    private final Map<V, DecreasableHeap<Item>.Decreasable> node = new HashMap<>();

    public Dijkstra(Graph<V, W> g, OrderedSemigroup<W> osg) {
        super(g, osg);
        heap = new PairingHeap<>(Comparator.comparing(i -> i.distance, osg));
    }

    public Map<V, W> path(V s) {
        heap.clear();
        node.clear();
        node.put(s, heap.pushRef(new Item(s, osg.zero())));
        dist.clear();
        dist.put(s, osg.zero());
        while (!heap.isEmpty()) {
            Item item = heap.popMin();
            V u = item.vertex;
            W du = item.distance;
            for (V v : graph.getNeighbors(u)) {
                W dv = dist.get(v);
                W dvAlt = osg.add(du, graph.getEdge(u, v));
                if (dv == null || osg.compare(dvAlt, dv) < 0) {
                    dist.put(v, dvAlt);
                    if (dv == null)
                        node.put(v, heap.pushRef(new Item(v, dvAlt)));
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
