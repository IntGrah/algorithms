package com.intgrah.algorithms.tree;

import java.util.ArrayList;

public class BTree<K extends Comparable<K>> implements SearchTree<K> {

    private final int min;
    private final int order; // Knuth's definition (max children)
    private Node root;

    public BTree(int o) {
        root = new Node(null);
        min = (o + 1) / 2;
        order = o;
    }

    @Override
    public void insert(K k) {
        insertAt(root, k);
        if (root.keys.size() == order) {
            Node n = root;
            root = new Node();
            root.keys.add(n.median());
            root.children.add(n.left());
            root.children.add(n.right());
        }
    }

    @Override
    public K search(Comparable<K> k) {
        Node n = root;
        while (true) {
            int i = n.binarySearch(k);
            if (i < n.keys.size() && k.compareTo(n.keys.get(i)) == 0) {
                return n.keys.get(i);
            }
            if (n.children == null) { return null; }
            n = n.children.get(i);
        }
    }

    @Override
    public void delete(Comparable<K> k) {
        deleteFrom(root, k);
        if (root.keys.isEmpty() && root.children != null) {
            root = root.children.get(0);
        }
    }

    private void insertAt(Node n, K k) {
        int j = n.binarySearch(k);
        if (j < n.keys.size() && k.compareTo(n.keys.get(j)) == 0) {
            n.keys.set(j, k);
        } else if (n.children == null) {
            n.keys.add(j, k);
        } else {
            insertAt(n.children.get(j), k);
            fixOverflow(n, j);
        }
    }

    private void fixOverflow(Node p, int i) {
        Node n = p.children.get(i);
        if (n.keys.size() < order) return;
        p.keys.add(i, n.median());
        p.children.set(i, n.left());
        p.children.add(i + 1, n.right());
    }

    private void deleteFrom(Node n, Comparable<K> k) {
        int j = n.binarySearch(k);
        if (j < n.keys.size() && k.compareTo(n.keys.get(j)) == 0) {
            if (n.children == null) {
                n.keys.remove(j);
            } else {
                Node c = n.children.get(j + 1);
                while (c.children != null)
                    c = c.children.getFirst();
                n.keys.set(j, c.keys.getFirst());
                deleteMin(n.children.get(j + 1));
                fixUnderflow(n, j + 1);
            }
        } else {
            if (n.children == null) return;
            deleteFrom(n.children.get(j), k);
            fixUnderflow(n, j);
        }
    }

    private void deleteMin(Node n) {
        if (n.children == null) {
            n.keys.removeFirst();
        } else {
            deleteMin(n.children.getFirst());
            fixUnderflow(n, 0);
        }
    }

    private void fixUnderflow(Node p, int i) {
        Node n = p.children.get(i);
        if (n.keys.size() >= min - 1)
            return;
        Node left = i > 0 ? p.children.get(i - 1) : null;
        Node right = i + 1 < p.children.size() ? p.children.get(i + 1) : null;
        if (right != null && right.keys.size() > min) { // Right sibling donates
            n.keys.addLast(p.keys.get(i));
            p.keys.set(i, right.keys.removeFirst());
            if (n.children != null)
                n.children.addLast(right.children.removeFirst());
        } else if (left != null && left.keys.size() > min) { // Left sibling donates
            n.keys.addFirst(p.keys.get(i - 1));
            p.keys.set(i - 1, left.keys.removeLast());
            if (n.children != null)
                n.children.addFirst(left.children.removeLast());
        } else if (right != null) { // Merge with right sibling and borrow from parent
            n.keys.addLast(p.keys.remove(i));
            n.keys.addAll(right.keys);
            if (n.children != null)
                n.children.addAll(right.children);
            p.children.remove(i + 1);
        } else if (left != null) { // Merge with left sibling and borrow from parent
            left.keys.addLast(p.keys.remove(i - 1));
            left.keys.addAll(n.keys);
            if (left.children != null)
                left.children.addAll(n.children);
            p.children.remove(i);
        }
    }

    private class Node {

        private final ArrayList<K> keys;
        private final ArrayList<Node> children;

        private Node() {
            keys = new ArrayList<>(order);
            children = new ArrayList<>(order + 1);
        }

        private Node(ArrayList<Node> cn) {
            keys = new ArrayList<>(order);
            children = cn;
        }

        private Node left() {
            Node l = children == null ? new Node(null) : new Node();
            l.keys.addAll(keys.subList(0, min - 1));
            if (l.children != null)
                l.children.addAll(children.subList(0, min));
            return l;
        }

        private Node right() {
            Node r = new Node(children == null ? null : new ArrayList<>(order + 1));
            r.keys.addAll(keys.subList(min, order));
            if (r.children != null)
                r.children.addAll(children.subList(min, order + 1));
            return r;
        }

        private K median() {
            return keys.get(min - 1);
        }

        private int binarySearch(Comparable<K> k) {
            int lo = 0, hi = keys.size();
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (k.compareTo(keys.get(mid)) > 0)
                    lo = mid + 1;
                else
                    hi = mid;
            }
            return lo;
        }

    }

}
