package com.intgrah.algorithms.graph.mst;

import com.intgrah.algorithms.set.DisjointSet;
import com.intgrah.algorithms.set.DisjointSetForest;
import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.UndirectedAdjacencyListGraph;

import java.util.*;

public class Kruskal<V, W> extends MinimumSpanningTree<V, W> {

    private final DisjointSet<V, DisjointSetForest<V>.Node> partition = new DisjointSetForest<>();
    private final Map<V, DisjointSetForest<V>.Node> node = new HashMap<>();

    public Kruskal(Comparator<W> c) {
        super(c);
    }

    public Graph<V, W> minimumSpanningTree(Graph<V, W> g, V s) {
        Graph<V, W> mst = new UndirectedAdjacencyListGraph<>();
        partition.clear();
        List<Edge> edges = new ArrayList<>();
        for (V u : g.getVertices()) {
            mst.putVertex(u);
            node.put(u, partition.push(u));
            for (V v : g.getNeighbors(u))
                edges.add(new Edge(u, v, g.getEdge(u, v)));
        }
        edges.sort((e, f) -> comp.compare(e.weight, f.weight));
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
