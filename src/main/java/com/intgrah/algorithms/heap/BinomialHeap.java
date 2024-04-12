package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.util.EmptyException;

import java.util.ArrayList;
import java.util.List;

public class BinomialHeap<K extends Comparable<K>> implements Heap<K> {

    private final List<Node> roots = new ArrayList<>();
    private Node min = null;

    @Override
    public Decreasable<K> push(K k) {
        Node n = new Node(k);
        addTree(n);
        if (min == null || n.key.compareTo(min.key) < 0) { min = n; }
        return n;
    }

    private void addTree(Node n) {
        int deg = n.degree();
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
        roots.set(min.degree(), null);
        for (Node n : min.children) {
            n.parent = null;
            addTree(n);
        }
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
    public boolean isEmpty() {
        return min == null;
    }

    @Override
    public void clear() {
        min = null;
        roots.clear();
    }

    private class Node implements Heap.Decreasable<K> {

        private K key;
        private List<Node> children = new ArrayList<>();
        private Node parent = null;

        private Node(K k) { key = k; }

        private Node merge(Node n) {
            if (key.compareTo(n.key) < 0) {
                children.add(n);
                n.parent = this;
                return this;
            } else {
                n.children.add(this);
                parent = n;
                return n;
            }
        }

        @Override
        public K getKey() { return key; }

        @Override
        public void decreaseKey(K k) throws KeyException {
            if (k.compareTo(key) > 0) { throw new KeyException(); }
            key = k;
            if (k.compareTo(min.key) < 0) { min = this; }
            while (parent != null && k.compareTo(parent.key) < 0) {
                Node p = parent;
                Node g = p.parent;
                int nDeg = degree();
                int pDeg = p.degree();
                p.children.set(nDeg, p);
                if (g == null) {
                    roots.set(pDeg, this);
                } else {
                    g.children.set(pDeg, this);
                }
                parent = g;
                p.parent = this;
                List<Node> pChildren = p.children;
                for (var child : children)
                    child.parent = p;
                for (Node child : pChildren)
                    child.parent = this;
                p.children = children;
                children = pChildren;
            }
        }

        private int degree() { return children.size(); }

    }

}
