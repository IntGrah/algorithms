package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.AbstractList;
import com.intgrah.algorithms.list.DoublyLinkedList;

import java.util.Comparator;
import java.util.HashMap;

public class FibonacciHeap<K> extends DecreasableHeap<K> {

    private final DoublyLinkedList<Node> roots = new DoublyLinkedList<>();
    private Node min = null;

    public FibonacciHeap(Comparator<K> ord) { super(ord); }

    @Override
    public Decreasable pushRef(K k) {
        Node n = new Node(k);
        addRoot(n);
        return n;
    }

    private void addRoot(Node n) {
        n.loser = false;
        n.parent = null;
        n.node = roots.pushBack(n);
        if (min == null || ord.compare(n.key, min.key) < 0)
            min = n;
    }

    @Override
    public K getMin() {
        assert !isEmpty();
        return min.key;
    }

    @Override
    public void deleteMin() {
        assert !isEmpty();
        min.node.delete();
        roots.append(min.children);
        HashMap<Integer, Node> d = new HashMap<>();
        for (Node n : roots) {
            int deg = n.children.size();
            while (d.containsKey(deg))
                n = n.link(d.remove(deg));
            d.put(deg, n);
        }
        roots.clear();
        min = null;
        for (Node n : d.values())
            addRoot(n);
    }

    @Override
    public boolean isEmpty() { return min == null; }

    @Override
    public void clear() {
        min = null;
        roots.clear();
    }

    private class Node extends Decreasable {

        private final DoublyLinkedList<Node> children = new DoublyLinkedList<>();
        private boolean loser = false;
        private Node parent = null;
        private AbstractList<Node>.Deletable node;

        private Node(K k) { super(k); }

        private Node link(Node n) {
            if (ord.compare(key, n.key) < 0) {
                n.parent = this;
                n.node = children.pushBack(n);
                return this;
            } else {
                parent = n;
                node = n.children.pushBack(this);
                return n;
            }
        }

        @Override
        public void decreaseKey(K k) {
            assert ord.compare(k, key) <= 0;
            key = k;
            if (parent == null) {
                if (ord.compare(k, min.key) < 0)
                    min = this;
                return;
            }
            Node n = this;
            while (n.parent != null) {
                Node p = n.parent;
                n.node.delete();
                addRoot(n);
                if (!p.loser) {
                    if (p.parent != null)
                        p.loser = true;
                    return;
                }
                n = p;
            }
        }

    }

}
