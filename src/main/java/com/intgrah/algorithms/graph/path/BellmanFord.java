package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.util.OrderedSemigroup;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord<V, W> extends ShortestPath<V, W> {

    private final Map<V, W> dist = new HashMap<>();

    public BellmanFord(OrderedSemigroup<W> osg) {
        super(osg);
    }

    public Map<V, W> path(
            Graph<V, W> g, V s
    ) throws NegativeWeightCycleException {
        dist.clear();
        dist.put(s, osg.zero());
        for (int i = g.getVertices().size(); i > 0; i--)
            for (V u : g.getVertices())
                for (V v : g.getNeighbors(u)) {
                    W du = dist.get(u);
                    if (du != null) {
                        W dv = dist.get(v);
                        W dvAlt = osg.add(du, g.getEdge(u, v));
                        if (dv == null || osg.compare(dvAlt, dv) < 0) {
                            if (i == 1) {
                                throw new NegativeWeightCycleException();
                            } else {
                                dist.put(v, dvAlt);
                            }
                        }
                    }
                }
        return dist;
    }

    public static class NegativeWeightCycleException extends RuntimeException {

    }

}
