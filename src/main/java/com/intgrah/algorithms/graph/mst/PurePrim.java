package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.UndirectedAdjacencyListGraph;
import com.intgrah.algorithms.heap.BasicHeap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PurePrim<V, W> extends MinimumSpanningTree<V, W> {

    private final BasicHeap<Item> q;
    private final Map<V, W> dist = new HashMap<>();
    private final Map<V, V> prev = new HashMap<>();

    public PurePrim(BasicHeap<Item> pq, Comparator<W> c) {
        super(c);
        q = pq;
    }

    public Graph<V, W> minimumSpanningTree(Graph<V, W> g, V s) {
        Graph<V, W> mst = new UndirectedAdjacencyListGraph<>();
        prev.clear();
        dist.clear();
        dist.put(s, null);
        q.clear();
        q.push(new Item(s, null));
        while (!q.isEmpty()) {
            Item item = q.popMin();
            V u = item.vertex;
            if (mst.getVertices().contains(u)) { continue; }
            mst.putVertex(u);
            for (V v : g.getNeighbors(u)) {
                W uv = g.getEdge(u, v);
                W dv = dist.get(v);
                if (mst.getVertices().contains(v)) { continue; }
                if (dv == null || comp.compare(uv, dv) < 0) {
                    dist.put(v, uv);
                    prev.put(v, u);
                    q.push(new Item(v, uv));
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
        public int compareTo(PurePrim<V, W>.Item p) {
            return comp.compare(distance, p.distance);
        }

    }

}
