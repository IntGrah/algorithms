package com.intgrah.algorithms.graph.path;

public class FloydWarshallTest extends AllShortestPathsTest {

    @Override
    public FloydWarshall<Integer, Integer> getInstance() {
        return new FloydWarshall<>(getOSG());
    }

}
