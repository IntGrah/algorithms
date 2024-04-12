package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.LinkedList;
import com.intgrah.algorithms.list.Stack;
import com.intgrah.algorithms.util.EmptyException;

public class BasicSkewHeap<K extends Comparable<K>> implements BasicHeap<K> {

    private Node root;

    @Override
    public Void push(K k) {
        root = merge(root, new Node(k));
        return null;
    }

    @Override
    public K popMin() throws EmptyException {
        if (root == null)
            throw new EmptyException();
        K min = root.key;
        root = merge(root.left, root.right);
        return min;
    }

    @Override
    public K getMin() throws EmptyException {
        if (root == null)
            throw new EmptyException();
        return root.key;
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
            if (m.key.compareTo(n.key) <= 0) {
                stack.pushFront(m);
                m = m.right;
            } else {
                stack.pushFront(n);
                n = n.right;
            }
        }
        Node r = m == null ? n : m;
        while (stack.size() > 0) {
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
