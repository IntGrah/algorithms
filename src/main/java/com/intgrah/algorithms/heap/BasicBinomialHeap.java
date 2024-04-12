package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.LinkedList;
import com.intgrah.algorithms.util.EmptyException;

import java.util.ArrayList;
import java.util.List;

public class BasicBinomialHeap<K extends Comparable<K>> implements BasicHeap<K> {

    private final List<Node> roots = new ArrayList<>();
    private Node min = null;

    @Override
    public Void push(K k) {
        Node n = new Node(k);
        addTree(n);
        if (min == null || n.key.compareTo(min.key) < 0) { min = n; }
        return null;
    }

    private void addTree(Node n) {
        int deg = n.children.size();
        while (deg < roots.size() && roots.get(deg) != null)
            n = n.merge(roots.set(deg++, null));
        if (deg == roots.size()) {
            roots.add(n);
        } else {
            roots.set(deg, n);
        }
    }

    @Override
    public K popMin() throws EmptyException {
        if (isEmpty()) { throw new EmptyException(); }
        roots.set(min.children.size(), null);
        for (Node n : min.children)
            addTree(n);
        K minKey = min.key;
        min = null;
        for (Node n : roots)
            if (n != null && (min == null || n.key.compareTo(min.key) < 0)) {
                min = n;
            }
        return minKey;
    }

    @Override
    public K getMin() throws EmptyException {
        if (min == null) { throw new EmptyException(); }
        return min.key;
    }

    @Override
    public boolean isEmpty() { return min == null; }

    @Override
    public void clear() {
        min = null;
        roots.clear();
    }

    private class Node {

        private final K key;
        private final LinkedList<Node> children = new LinkedList<>();

        private Node(K k) { key = k; }

        private Node merge(Node n) {
            if (key.compareTo(n.key) < 0) {
                children.pushFront(n);
                return this;
            } else {
                n.children.pushFront(this);
                return n;
            }
        }

    }

}
