package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.AbstractList;
import com.intgrah.algorithms.list.DoublyLinkedList;
import com.intgrah.algorithms.list.LinkedList;

import java.util.Comparator;

public class SkewBinomialHeap<K> extends Heap<K> {

    private final DoublyLinkedList<Node> roots = new DoublyLinkedList<>();
    private Node min = null;

    public SkewBinomialHeap(Comparator<K> ord) { super(ord); }

    @Override
    public void push(K k) {
        Node l = new Node(k, 0);
        if (roots.size() >= 2) {
            Node m = roots.popFront();
            Node n = roots.popFront();
            if (m.rank == n.rank) {
                Node s = skewLink(l, m, n);
                s.node = roots.pushFront(s);
            } else {
                n.node = roots.pushFront(n);
                m.node = roots.pushFront(m);
                l.node = roots.pushFront(l);
            }
        } else {
            l.node = roots.pushFront(l);
        }
        if (min == null || ord.compare(l.key, min.key) < 0)
            min = l;
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
        DoublyLinkedList<K> zero = new DoublyLinkedList<>();
        DoublyLinkedList<Node> nonZero = new DoublyLinkedList<>();
        while (!min.children.isEmpty()) {
            Node n = min.children.popFront();
            if (n.rank == 0)
                zero.pushBack(n.key);
            else
                nonZero.pushBack(n);
        }
        merge(nonZero);
        for (K k : zero)
            push(k);
        min = null;
        for (Node root : roots) {
            if (min == null || ord.compare(root.key, min.key) < 0)
                min = root;
        }
    }

    @Override
    public boolean isEmpty() { return min == null; }

    @Override
    public void clear() {
        min = null;
        roots.clear();
    }

    private Node skewLink(Node l, Node m, Node n) {
        if (ord.compare(l.key, m.key) <= 0 && ord.compare(l.key, n.key) <= 0) {
            l.children.pushBack(m);
            l.children.pushBack(n);
            l.rank = m.rank + 1;
            return l;
        } else {
            if (ord.compare(m.key, n.key) <= 0) {
                m.children.pushFront(l);
                m.children.pushBack(n);
                m.rank++;
                return m;
            } else {
                n.children.pushFront(l);
                n.children.pushBack(m);
                n.rank++;
                return n;
            }
        }
    }

    private void merge(DoublyLinkedList<Node> h) {
        if (h.isEmpty())
            return;
        unique(roots);
        unique(h);
        if (roots.isEmpty()) {
            for (Node n : h)
                n.node = roots.pushBack(n);
            return;
        }
        Node t1 = roots.getFront();
        Node t2 = h.getFront();
        if (t1.rank < t2.rank) {
            t1 = roots.popFront();
            merge(h);
            t1.node = roots.pushFront(t1);
        } else if (t1.rank > t2.rank) {
            t2 = h.popFront();
            merge(h);
            t2.node = roots.pushFront(t2);
        } else {
            t1 = roots.popFront();
            t2 = h.popFront();
            Node s = simpleLink(t1, t2);
            merge(h);
            s.node = roots.pushFront(s);
            unique(roots);
        }
    }

    private void unique(DoublyLinkedList<Node> l) {
        while (l.size() >= 2) {
            Node m = l.popFront();
            Node n = l.popFront();
            if (m.rank == n.rank) {
                Node s = simpleLink(m, n);
                s.node = l.pushFront(s);
            } else {
                n.node = l.pushFront(n);
                m.node = l.pushFront(m);
                return;
            }
        }
    }

    private Node simpleLink(Node m, Node n) {
        if (ord.compare(m.key, n.key) < 0) {
            m.children.pushFront(n);
            m.rank++;
            return m;
        } else {
            n.children.pushFront(m);
            n.rank++;
            return n;
        }
    }


    private class Node {

        private final K key;
        private final LinkedList<Node> children = new LinkedList<>();
        private AbstractList<Node>.Deletable node;
        private int rank;

        private Node(K k, int r) {
            key = k;
            rank = r;
        }

    }

}
