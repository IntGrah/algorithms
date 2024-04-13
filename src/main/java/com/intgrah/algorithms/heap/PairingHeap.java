package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.AbstractList;
import com.intgrah.algorithms.list.DoublyLinkedList;
import com.intgrah.algorithms.list.Queue;

import java.util.Comparator;

public class PairingHeap<K> extends DecreasableHeap<K> {

    private Node root = null;

    public PairingHeap(Comparator<K> ord) { super(ord); }

    @Override
    public Decreasable pushRef(K k) {
        Node n = new Node(k);
        root = n.link(root);
        return n;
    }

    @Override
    public K getMin() {
        assert !isEmpty();
        return root.key;
    }

    @Override
    public void deleteMin() {
        assert !isEmpty();
        Queue<Node> trees = root.children;
        root = null;
        int pairs = trees.size() / 2;
        for (int i = 0; i < pairs; i++)
            trees.pushBack(trees.popFront().link(trees.popFront()));
        while (!trees.isEmpty())
            root = trees.popFront().link(root);
        if (root != null)
            root.node = null;
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void clear() { root = null; }

    private class Node extends Decreasable {

        private final DoublyLinkedList<Node> children = new DoublyLinkedList<>();
        private AbstractList<Node>.Deletable node;

        private Node(K k) { super(k); }

        @Override
        public void decreaseKey(K k) {
            assert ord.compare(k, key) < 0;
            key = k;
            if (this == root)
                return;
            node.delete();
            root = link(root);
            root.node = null;
        }

        private Node link(Node n) {
            assert n != this;
            if (n == null)
                return this;
            if (ord.compare(key, n.key) < 0) {
                n.node = children.pushFront(n);
                return this;
            } else {
                node = n.children.pushFront(this);
                return n;
            }
        }

    }

}
