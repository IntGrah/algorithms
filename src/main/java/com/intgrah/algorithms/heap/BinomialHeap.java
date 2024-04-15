package com.intgrah.algorithms.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinomialHeap<K> extends DecreasableHeap<K> {

    private final List<Node> roots = new ArrayList<>();
    private Node min = null;

    public BinomialHeap(Comparator<K> ord) { super(ord); }

    @Override
    public Decreasable pushRef(K k) {
        Node n = new Node(k);
        addTree(n);
        if (min == null || ord.compare(n.key, min.key) < 0)
            min = n;
        return n;
    }

    private void addTree(Node n) {
        int deg = n.degree();
        while (deg < roots.size() && roots.get(deg) != null)
            n = link(n, roots.set(deg++, null));
        if (deg == roots.size())
            roots.add(n);
        else
            roots.set(deg, n);
    }

    private Node link(Node m, Node n) {
        if (ord.compare(m.key, n.key) < 0) {
            m.children.add(n);
            n.parent = m;
            return m;
        } else {
            n.children.add(m);
            m.parent = n;
            return n;
        }
    }

    @Override
    public K getMin() {
        assert !isEmpty();
        return min.key;
    }

    @Override
    public void deleteMin() {
        assert !isEmpty();
        roots.set(min.degree(), null);
        for (Node n : min.children) {
            n.parent = null;
            addTree(n);
        }
        min = null;
        for (Node n : roots)
            if (n != null && (min == null || ord.compare(n.key, min.key) < 0))
                min = n;
    }

    @Override
    public boolean isEmpty() { return min == null; }

    @Override
    public void clear() {
        min = null;
        roots.clear();
    }

    private class Node extends Decreasable {

        private List<Node> children = new ArrayList<>();
        private Node parent = null;

        private Node(K k) { super(k); }

        @Override
        public void decreaseKey(K k) {
            assert ord.compare(k, key) <= 0;
            key = k;
            if (ord.compare(k, min.key) < 0)
                min = this;
            while (parent != null && ord.compare(k, parent.key) < 0) {
                Node p = parent;
                Node g = p.parent;
                int nDeg = degree();
                int pDeg = p.degree();
                p.children.set(nDeg, p);
                if (g == null)
                    roots.set(pDeg, this);
                else
                    g.children.set(pDeg, this);
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
