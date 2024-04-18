package com.intgrah.algorithms.set;

import com.intgrah.algorithms.list.LinkedList;

public class DisjointSetForest<T> implements DisjointSet<T, DisjointSetForest<T>.Node> {

    LinkedList<Node> nodes = new LinkedList<>();

    @Override
    public Node push(T v) {
        Node n = new Node(v);
        nodes.pushFront(n);
        return n;
    }

    @Override
    public void clear() {
        nodes.clear();
    }

    public class Node implements Reference<T, Node> {

        private final T value;
        private Node parent = this;
        private int size = 1;

        private Node(T v) { value = v; }

        @Override
        public T getValue() { return value; }

        @Override
        public Node find() {
            if (parent == this) {
                return this;
            } else {
                return parent = parent.find();
            }
        }

        public Node unite(Node n) {
            Node x = find();
            Node y = n.find();
            if (x == y)
                return x;
            if (x.size < y.size) {
                x.parent = y;
                y.size += x.size;
                return y;
            } else {
                y.parent = x;
                x.size += y.size;
                return x;
            }
        }

    }

}
