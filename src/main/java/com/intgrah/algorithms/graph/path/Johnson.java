package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.graph.GraphDecorator;
import com.intgrah.algorithms.graph.HashMapGraph;
import com.intgrah.algorithms.util.OrderedGroup;

import java.util.Map;

public class Johnson<V, W> extends AllShortestPaths<V, W> {

    private final OrderedGroup<W> og;

    public Johnson(Graph<V, W> g, OrderedGroup<W> og) {
        super(g, og);
        this.og = og;
    }

    @Override
    public Graph<V, W> allPaths() throws BellmanFord.NegativeCycleException {
        Graph<V, W> dist = new HashMapGraph<>();
        graph.putVertex(null);
        for (V u : graph.getVertices()) {
            if (u != null)
                dist.putVertex(u);
            graph.putEdge(null, u, osg.zero());
        }

        BellmanFord<V, W> bmf = new BellmanFord<>(graph, osg);
        Map<V, W> bmfDist = bmf.path(null);
        graph.removeVertex(null);

        Graph<V, W> aux = new GraphDecorator<>(graph) {
            @Override
            public W getEdge(V u, V v) {
                return og.add(graph.getEdge(u, v), og.sub(bmfDist.get(u), bmfDist.get(v)));
            }
        };

        Dijkstra<V, W> dijkstra = new Dijkstra<>(aux, osg);

        for (V u : aux.getVertices()) {
            Map<V, W> dijkstraDist = dijkstra.path(u);
            for (Map.Entry<V, W> e : dijkstraDist.entrySet()) {
                V v = e.getKey();
                dist.putEdge(u, v, og.add(e.getValue(), og.sub(bmfDist.get(v), bmfDist.get(u))));
            }
        }
        return dist;
    }

}
