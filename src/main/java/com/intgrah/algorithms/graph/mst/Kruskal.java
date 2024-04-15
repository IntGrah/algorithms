package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.UndirectedHashMapGraph;
import com.intgrah.algorithms.set.DisjointSet;
import com.intgrah.algorithms.set.DisjointSetForest;

import java.util.*;

public class Kruskal<V, W> extends MinimumSpanningTree<V, W> {

    private final DisjointSet<V, DisjointSetForest<V>.Node> partition = new DisjointSetForest<>();
    private final Map<V, DisjointSetForest<V>.Node> node = new HashMap<>();

    public Kruskal(Graph<V, W> g, Comparator<W> ord) { super(g, ord); }

    public Graph<V, W> minimumSpanningTree(V s) {
        Graph<V, W> mst = new UndirectedHashMapGraph<>();
        partition.clear();
        List<Edge> edges = new ArrayList<>();
        for (V u : graph.getVertices()) {
            mst.putVertex(u);
            node.put(u, partition.push(u));
            for (V v : graph.getNeighbors(u))
                edges.add(new Edge(u, v, graph.getEdge(u, v)));
        }
        edges.sort((e, f) -> ord.compare(e.weight, f.weight));
        for (Edge e : edges) {
            V u = e.from;
            V v = e.to;
            DisjointSetForest<V>.Node p = node.get(u).find();
            DisjointSetForest<V>.Node q = node.get(v).find();
            if (p != q) {
                mst.putEdge(u, v, e.weight);
                p.unite(q);
            }
        }
        return mst;
    }

    private class Edge {

        private final V from;
        private final V to;
        private final W weight;

        private Edge(V u, V v, W uv) {
            from = u;
            to = v;
            weight = uv;
        }

    }

}
