package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.DoublyLinkedList;
import com.intgrah.algorithms.list.List;
import com.intgrah.algorithms.util.EmptyException;

public class PairingHeap<K extends Comparable<K>> implements Heap<K> {

    private Node root = null;

    @Override
    public Decreasable<K> push(K k) {
        Node n = new Node(k);
        if (root == null) {
            root = n;
        } else {
            root = root.merge(n);
        }
        return n;
    }

    @Override
    public K popMin() throws EmptyException {
        if (root == null) { throw new EmptyException(); }
        K minKey = root.key;
        DoublyLinkedList<Node> children = root.children;
        root = null;
        for (Node n : children)
            root = n.merge(root);
        if (root != null) { root.node = null; }
        return minKey;
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

    private class Node implements Heap.Decreasable<K> {

        private final DoublyLinkedList<Node> children = new DoublyLinkedList<>();
        private K key;
        private List.Deletable<Node> node;

        private Node(K k) { key = k; }

        @Override
        public K getKey() { return key; }

        @Override
        public void decreaseKey(K k) throws KeyException {
            if (k.compareTo(key) > 0) { throw new KeyException(); }
            key = k;
            if (node != null) {
                node.delete();
                root = root.merge(this);
                root.node = null;
            }
        }

        private Node merge(Node n) {
            if (n == null) { return this; }
            if (key.compareTo(n.key) < 0) {
                n.node = children.pushFront(n);
                return this;
            } else {
                node = n.children.pushFront(this);
                return n;
            }
        }

    }

}
