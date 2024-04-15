package com.intgrah.algorithms.graph;

public class GraphTest {

    public static Graph<Integer, Integer> simple() {
        Graph<Integer, Integer> g = new HashMapGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putVertex(4);
        g.putEdge(0, 1, 10);
        g.putEdge(0, 3, 5);
        g.putEdge(1, 2, 1);
        g.putEdge(1, 3, 2);
        g.putEdge(2, 4, 4);
        g.putEdge(3, 1, 3);
        g.putEdge(3, 2, 9);
        g.putEdge(3, 4, 2);
        g.putEdge(4, 0, 7);
        g.putEdge(4, 2, 6);
        return g;
    }

    public static Graph<Integer, Integer> zeroWeightCycle() {
        Graph<Integer, Integer> g = new HashMapGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putEdge(0, 1, 4);
        g.putEdge(1, 2, -3);
        g.putEdge(2, 3, 6);
        g.putEdge(3, 1, -3);
        return g;
    }

    public static Graph<Integer, Integer> anotherGraph() {
        Graph<Integer, Integer> g = new HashMapGraph<>();
        g.putVertex(0);
        g.putVertex(1);
        g.putVertex(2);
        g.putVertex(3);
        g.putVertex(4);
        g.putVertex(5);
        g.putVertex(6);
        g.putVertex(7);
        g.putVertex(8);
        g.putEdge(0, 1, 4);
        g.putEdge(0, 7, 8);
        g.putEdge(1, 2, 8);
        g.putEdge(1, 7, 11);
        g.putEdge(2, 3, 7);
        g.putEdge(2, 8, 2);
        g.putEdge(2, 5, 4);
        g.putEdge(3, 4, 9);
        g.putEdge(3, 5, 14);
        g.putEdge(4, 5, 10);
        g.putEdge(5, 6, 2);
        g.putEdge(6, 7, 1);
        g.putEdge(6, 8, 6);
        g.putEdge(7, 8, 7);
        return g;
    }

}
