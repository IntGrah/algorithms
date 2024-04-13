package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.UndirectedHashMapGraph;
import com.intgrah.algorithms.heap.DecreasableHeap;
import com.intgrah.algorithms.heap.PairingHeap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Prim<V, W> extends MinimumSpanningTree<V, W> {

    private final DecreasableHeap<Item> q;
    private final Map<V, W> dist = new HashMap<>();
    private final Map<V, V> prev = new HashMap<>();
    private final Map<V, DecreasableHeap<Item>.Decreasable> node = new HashMap<>();

    public Prim(DecreasableHeap<Item> pq, Comparator<W> osg) {
        super(osg);
        q = pq;
    }

    public Prim(Comparator<W> osg) {
        this(new PairingHeap<>(Comparator.comparing(i -> i.distance, osg)), osg);
    }

    public Graph<V, W> minimumSpanningTree(Graph<V, W> g, V s) {
        Graph<V, W> mst = new UndirectedHashMapGraph<>();
        prev.clear();
        dist.clear();
        dist.put(s, null);
        q.clear();
        node.clear();
        node.put(s, q.pushRef(new Item(s, null)));
        while (!q.isEmpty()) {
            Item item = q.popMin();
            V u = item.vertex;
            mst.putVertex(u);
            for (V v : g.getNeighbors(u)) {
                W uv = g.getEdge(u, v);
                W dv = dist.get(v);
                if (mst.getVertices().contains(v)) { continue; }
                if (dv == null || comp.compare(uv, dv) < 0) {
                    dist.put(v, uv);
                    prev.put(v, u);
                    Item iv = new Item(v, uv);
                    if (dv == null) {
                        node.put(v, q.pushRef(iv));
                    } else {
                        node.get(v).decreaseKey(iv);
                    }
                }
            }
        }
        for (V v : prev.keySet()) {
            V u = prev.get(v);
            mst.putEdge(u, v, g.getEdge(u, v));
        }
        return mst;
    }

    public class Item implements Comparable<Item> {

        private final V vertex;
        private final W distance;

        private Item(V v, W d) {
            vertex = v;
            distance = d;
        }

        @Override
        public int compareTo(Prim<V, W>.Item p) {
            return comp.compare(distance, p.distance);
        }

    }

}
