package com.intgrah.algorithms.graph.flow;

public class EdmondsKarpTest extends FordFulkersonTest {

    protected EdmondsKarp<Integer, Integer> getInstance() {
        return new EdmondsKarp<>(getOSG());
    }

}
