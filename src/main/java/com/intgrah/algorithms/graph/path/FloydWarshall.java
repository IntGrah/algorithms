package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.HashMapGraph;
import com.intgrah.algorithms.util.OrderedSemigroup;

public class FloydWarshall<V, W> extends AllShortestPaths<V, W> {

    public FloydWarshall(Graph<V, W> g, OrderedSemigroup<W> osg) {
        super(g, osg);
    }

    @Override
    public Graph<V, W> allPaths() {
        Graph<V, W> dist = new HashMapGraph<>();
        for (V u : graph.getVertices())
            dist.putVertex(u);
        for (V u : graph.getVertices()) {
            for (V v : graph.getNeighbors(u))
                dist.putEdge(u, v, graph.getEdge(u, v));
            dist.putEdge(u, u, osg.zero());
        }
        for (V k : graph.getVertices()) {
            for (V u : graph.getVertices()) {
                for (V v : graph.getVertices()) {
                    W uk = dist.getEdge(u, k);
                    if (uk == null)
                        continue;
                    W kv = dist.getEdge(k, v);
                    if (kv == null)
                        continue;
                    W uvAlt = osg.add(uk, kv);
                    W uv = dist.getEdge(u, v);
                    if (uv == null || osg.compare(uvAlt, uv) < 0) {
                        dist.putEdge(u, v, uvAlt);
                    }
                }
            }
        }
        return dist;
    }

}
