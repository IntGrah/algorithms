package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.UndirectedHashMapGraph;
import com.intgrah.algorithms.heap.BinaryHeap;
import com.intgrah.algorithms.heap.Heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PurePrim<V, W> extends MinimumSpanningTree<V, W> {

    private final Heap<Item> heap;
    private final Map<V, W> dist = new HashMap<>();
    private final Map<V, V> prev = new HashMap<>();

    public PurePrim(Graph<V, W> g, Comparator<W> ord) {
        super(g, ord);
        heap = new BinaryHeap<>(Comparator.comparing(i -> i.distance, ord));
    }

    public Graph<V, W> minimumSpanningTree(V s) {
        Graph<V, W> mst = new UndirectedHashMapGraph<>();
        prev.clear();
        dist.clear();
        dist.put(s, null);
        heap.clear();
        heap.push(new Item(s, null));
        while (!heap.isEmpty()) {
            Item item = heap.popMin();
            V u = item.vertex;
            if (mst.getVertices().contains(u))
                continue;
            mst.putVertex(u);
            for (V v : graph.getNeighbors(u)) {
                W uv = graph.getEdge(u, v);
                W dv = dist.get(v);
                if (mst.getVertices().contains(v))
                    continue;
                if (dv == null || ord.compare(uv, dv) < 0) {
                    dist.put(v, uv);
                    prev.put(v, u);
                    heap.push(new Item(v, uv));
                }
            }
        }
        for (V v : prev.keySet()) {
            V u = prev.get(v);
            mst.putEdge(u, v, graph.getEdge(u, v));
        }
        return mst;
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
