package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.GraphDecorator;
import com.intgrah.algorithms.util.OrderedGroup;

import java.util.HashMap;
import java.util.Map;

public class Johnson<V, W> extends AllShortestPaths<V, W> {

    private final OrderedGroup<W> og;

    public Johnson(OrderedGroup<W> og) {
        super(og);
        this.og = og;
    }

    @Override
    public Map<V, Map<V, W>> allPaths(
            Graph<V, W> g
    ) throws BellmanFord.NegativeWeightCycleException {
        Map<V, Map<V, W>> dist = new HashMap<>();
        g.putVertex(null);
        for (V u : g.getVertices()) {
            if (u != null) { dist.put(u, new HashMap<>()); }
            g.putEdge(null, u, osg.zero());
        }

        BellmanFord<V, W> bmf = new BellmanFord<>(osg);
        Map<V, W> bmfDist = bmf.path(g, null);
        g.removeVertex(null);

        Graph<V, W> aux = new GraphDecorator<>(g) {
            @Override
            public W getEdge(V u, V v) {
                return og.add(g.getEdge(u, v), og.sub(bmfDist.get(u), bmfDist.get(v)));
            }
        };

        Dijkstra<V, W> dijkstra = new Dijkstra<>(osg);

        for (V u : aux.getVertices()) {
            Map<V, W> dijkstraDist = dijkstra.path(aux, u);
            for (Map.Entry<V, W> e : dijkstraDist.entrySet()) {
                V v = e.getKey();
                dist.get(u).put(v, og.add(e.getValue(), og.sub(bmfDist.get(v), bmfDist.get(u))));
            }
        }
        return dist;
    }

}
