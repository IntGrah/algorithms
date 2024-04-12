package com.intgrah.algorithms.graph.path;

public class DijkstraTest extends ShortestPathTest {

    @Override
    public Dijkstra<Integer, Integer> getInstance() {
        return new Dijkstra<>(getOSG());
    }

}
