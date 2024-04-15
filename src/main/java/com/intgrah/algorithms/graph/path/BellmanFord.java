package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord<V, W> extends ShortestPath<V, W> {

    private final Map<V, W> dist = new HashMap<>();

    public BellmanFord(Graph<V, W> g, OrderedSemigroup<W> osg) {
        super(g, osg);
    }

    public Map<V, W> path(V s) {
        dist.clear();
        dist.put(s, osg.zero());
        for (int i = graph.getVertices().size(); i > 0; i--)
            for (V u : graph.getVertices())
                for (V v : graph.getNeighbors(u)) {
                    W du = dist.get(u);
                    if (du != null) {
                        W dv = dist.get(v);
                        W dvAlt = osg.add(du, graph.getEdge(u, v));
                        if (dv == null || osg.compare(dvAlt, dv) < 0) {
                            if (i == 1)
                                throw new NegativeCycleException();
                            else
                                dist.put(v, dvAlt);
                        }
                    }
                }
        return dist;
    }

    public static class NegativeCycleException extends RuntimeException { }

}
