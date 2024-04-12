package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.HashMap;
import java.util.Map;

public class FloydWarshall<V, W> extends AllShortestPaths<V, W> {

    public FloydWarshall(OrderedSemigroup<W> osg) {
        super(osg);
    }

    @Override
    public Map<V, Map<V, W>> allPaths(Graph<V, W> g) {
        Map<V, Map<V, W>> dist = new HashMap<>();
        for (V u : g.getVertices()) {
            Map<V, W> vs = new HashMap<>();
            vs.put(u, osg.zero());
            dist.put(u, vs);
        }
        for (V u : g.getVertices())
            for (V v : g.getNeighbors(u))
                dist.get(u).put(v, g.getEdge(u, v));
        for (V k : g.getVertices())
            for (V u : g.getVertices())
                for (V v : g.getVertices()) {
                    W uk = dist.get(u).get(k);
                    if (uk == null) { continue; }
                    W kv = dist.get(k).get(v);
                    if (kv == null) { continue; }
                    W uvAlt = osg.add(uk, kv);
                    W uv = dist.get(u).get(v);
                    if (uv == null || osg.compare(uvAlt, uv) < 0) {
                        dist.get(u).put(v, uvAlt);
                    }
                }
        return dist;
    }

}
