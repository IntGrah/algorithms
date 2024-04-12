package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.LinkedList;
import com.intgrah.algorithms.list.Queue;
import com.intgrah.algorithms.util.EmptyException;

public class BasicPairingHeap<K extends Comparable<K>> implements BasicHeap<K> {

    private Node root = null;

    @Override
    public Void push(K k) {
        root = new Node(k).merge(root);
        return null;
    }

    @Override
    public K popMin() throws EmptyException {
        if (root == null) { throw new EmptyException(); }
        K min = root.key;
        Queue<Node> trees = root.children;
        root = null;
        if (trees.size() == 0) { return min; }
        int pairs = trees.size() / 2;
        for (int i = 0; i < pairs; i++)
            trees.pushBack(trees.popFront().merge(trees.popFront()));
        while (trees.size() > 0)
            root = trees.popFront().merge(root);
        return min;
    }

    @Override
    public K getMin() throws EmptyException {
        if (root == null) { throw new EmptyException(); }
        return root.key;
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void clear() { root = null; }

    private class Node {

        private final K key;
        private final Queue<Node> children = new LinkedList<>();

        private Node(K k) { key = k; }

        private Node merge(Node n) {
            if (n == null) { return this; }
            if (key.compareTo(n.key) < 0) {
                children.pushBack(n);
                return this;
            } else {
                n.children.pushBack(this);
                return n;
            }
        }

    }

}
