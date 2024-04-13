package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.LinkedList;
import com.intgrah.algorithms.list.Stack;

import java.util.Comparator;

public class SkewHeap<K> extends Heap<K> {

    private Node root;

    public SkewHeap(Comparator<K> ord) { super(ord); }

    @Override
    public void push(K k) {
        root = merge(root, new Node(k));
    }

    @Override
    public K getMin() {
        assert !isEmpty();
        return root.key;
    }

    @Override
    public void deleteMin() {
        assert !isEmpty();
        root = merge(root.left, root.right);
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void clear() { root = null; }

    private Node merge(Node m, Node n) {
        if (m == null)
            return n;
        if (n == null)
            return m;
        Stack<Node> stack = new LinkedList<>();
        while (m != null && n != null) {
            if (ord.compare(m.key, n.key) < 0) {
                stack.pushFront(m);
                m = m.right;
            } else {
                stack.pushFront(n);
                n = n.right;
            }
        }
        Node r = m == null ? n : m;
        while (!stack.isEmpty()) {
            Node x = stack.popFront();
            x.right = x.left;
            x.left = r;
            r = x;
        }
        return r;
    }

    private class Node {

        private final K key;
        private Node left, right;

        private Node(K k) { key = k; }

    }

}
