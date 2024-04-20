package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class SkewHeap<K> extends Heap<K> {

    private Node root;

    public SkewHeap(Comparator<K> ord) { super(ord); }

    @Override
    public void push(K k) {
        root = link(root, new Node(k));
    }

    @Override
    public K getMin() {
        assert !isEmpty();
        return root.key;
    }

    @Override
    public void deleteMin() {
        assert !isEmpty();
        root = link(root.left, root.right);
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void clear() { root = null; }

    private Node link(Node m, Node n) {
        if (m == null)
            return n;
        if (n == null)
            return m;
        if (ord.compare(m.key, n.key) > 0)
            return link(n, m);
        Node r = link(m.right, n);
        m.right = m.left;
        m.left = r;
        return m;
    }

    private class Node {

        private final K key;
        private Node left, right;

        private Node(K k) { key = k; }

    }

}
