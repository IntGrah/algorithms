package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.UndirectedHashMapGraph;
import com.intgrah.algorithms.heap.DecreasableHeap;
import com.intgrah.algorithms.heap.PairingHeap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Prim<V, W> extends MinimumSpanningTree<V, W> {

    private final DecreasableHeap<Item> heap;
    private final Map<V, W> dist = new HashMap<>();
    private final Map<V, V> prev = new HashMap<>();
    private final Map<V, DecreasableHeap<Item>.Decreasable> node = new HashMap<>();

    public Prim(Graph<V, W> g, Comparator<W> osg) {
        super(g, osg);
        heap = new PairingHeap<>(Comparator.comparing(i -> i.distance, osg));
    }

    public Graph<V, W> minimumSpanningTree(V s) {
        Graph<V, W> mst = new UndirectedHashMapGraph<>();
        prev.clear();
        dist.clear();
        dist.put(s, null);
        heap.clear();
        node.clear();
        node.put(s, heap.pushRef(new Item(s, null)));
        while (!heap.isEmpty()) {
            Item item = heap.popMin();
            V u = item.vertex;
            mst.putVertex(u);
            for (V v : graph.getNeighbors(u)) {
                W uv = graph.getEdge(u, v);
                W dv = dist.get(v);
                if (mst.getVertices().contains(v))
                    continue;
                if (dv == null || ord.compare(uv, dv) < 0) {
                    dist.put(v, uv);
                    prev.put(v, u);
                    Item iv = new Item(v, uv);
                    if (dv == null)
                        node.put(v, heap.pushRef(iv));
                    else
                        node.get(v).decreaseKey(iv);
                }
            }
        }
        for (V v : prev.keySet()) {
            V u = prev.get(v);
            mst.putEdge(u, v, graph.getEdge(u, v));
        }
        return mst;
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
