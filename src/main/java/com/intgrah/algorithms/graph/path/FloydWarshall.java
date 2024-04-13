package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.HashMapGraph;
import com.intgrah.algorithms.util.OrderedSemigroup;

public class FloydWarshall<V, W> extends AllShortestPaths<V, W> {

    public FloydWarshall(OrderedSemigroup<W> osg) {
        super(osg);
    }

    @Override
    public Graph<V, W> allPaths(Graph<V, W> g) {
        Graph<V, W> dist = new HashMapGraph<>();
        for (V u : g.getVertices())
            dist.putVertex(u);
        for (V u : g.getVertices()) {
            for (V v : g.getNeighbors(u))
                dist.putEdge(u, v, g.getEdge(u, v));
            dist.putEdge(u, u, osg.zero());
        }
        for (V k : g.getVertices()) {
            for (V u : g.getVertices()) {
                for (V v : g.getVertices()) {
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
