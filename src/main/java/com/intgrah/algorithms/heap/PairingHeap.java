package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.Deletable;
import com.intgrah.algorithms.list.DoublyLinkedList;

import java.util.Comparator;

public class PairingHeap<K> extends DecreasableHeap<K> {

    private Node root = null;

    public PairingHeap(Comparator<K> ord) { super(ord); }

    @Override
    public Decreasable pushRef(K k) {
        Node n = new Node(k);
        root = link(root, n);
        return n;
    }

    private Node link(Node m, Node n) {
        if (m == null)
            return n;
        if (n == null)
            return m;
        if (ord.compare(m.key, n.key) <= 0) {
            n.ref = m.children.pushFront(n);
            return m;
        } else {
            m.ref = n.children.pushFront(m);
            return n;
        }
    }

    @Override
    public K getMin() {
        assert !isEmpty();
        return root.key;
    }

    @Override
    public void deleteMin() {
        assert !isEmpty();
        DoublyLinkedList<Node> trees = root.children;
        root = null;
        int pairs = trees.size() / 2;
        for (int i = 0; i < pairs; i++)
            trees.pushBack(link(trees.popFront(), trees.popFront()));
        while (!trees.isEmpty())
            root = link(root, trees.popFront());
        if (root != null)
            root.ref = null;
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void clear() { root = null; }

    private class Node extends Decreasable {

        private final DoublyLinkedList<Node> children = new DoublyLinkedList<>();
        private Deletable ref;

        private Node(K k) { super(k); }

        @Override
        public void decreaseKey(K k) {
            assert ord.compare(k, key) < 0;
            key = k;
            if (this == root)
                return;
            ref.delete();
            root = link(root, this);
            root.ref = null;
        }

    }

}
