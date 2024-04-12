package com.intgrah.algorithms.graph.path;

public class BasicDijkstraTest extends ShortestPathTest {

    @Override
    public BasicDijkstra<Integer, Integer> getInstance() {
        return new BasicDijkstra<>(getOSG());
    }

}
