package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class LeftistTree<K> extends Heap<K> {

    private Node root;

    public LeftistTree(Comparator<K> ord) {
        super(ord);
    }

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
        if (m.left == null) {
            m.left = r;
        } else {
            if (m.left.s < r.s) {
                m.right = m.left;
                m.left = r;
            } else {
                m.right = r;
            }
            m.s = m.right.s + 1;
        }
        return m;
    }

    private class Node {

        private final K key;
        int s;
        private Node left, right;

        private Node(K k) { key = k; }

    }

}
