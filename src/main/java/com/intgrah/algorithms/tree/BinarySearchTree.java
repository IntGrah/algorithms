package com.intgrah.algorithms.tree;

public class BinarySearchTree<K extends Comparable<K>> implements SearchTree<K> {

    private Node root;

    @Override
    public void insert(K k) {
        root = insertAt(root, k);
    }

    @Override
    public K search(Comparable<K> k) {
        Node n = root;
        while (n != null) {
            int comp = k.compareTo(n.key);
            if (comp < 0) {
                n = n.left;
            } else if (comp == 0) {
                return n.key;
            } else {
                n = n.right;
            }
        }
        return null;
    }

    @Override
    public void delete(Comparable<K> k) {
        root = deleteFrom(root, k);
    }

    private Node deleteFrom(Node n, Comparable<K> k) {
        if (n == null) { return null; }
        int comp = k.compareTo(n.key);
        if (comp < 0) { n.left = deleteFrom(n.left, k); } else if (comp == 0) {
            if (n.left == null) { return n.right; }
            if (n.right == null) { return n.left; }
            Node sp = n;
            Node s = n.right;
            while (s.left != null) {
                sp = s;
                s = s.left;
            }
            n.key = s.key;
            n.right = s.right;
            if (n != sp) { sp.left = null; }
        } else {
            n.right = deleteFrom(n.right, k);
        }
        return n;
    }

    private Node insertAt(Node p, K k) {
        if (p == null) { return new Node(k); }
        int comp = k.compareTo(p.key);
        if (comp < 0) {
            p.left = insertAt(p.left, k);
        } else if (comp == 0) {
            p.key = k;
        } else {
            p.right = insertAt(p.right, k);
        }
        return p;
    }


    public class Node {

        private K key;
        private Node left, right;

        private Node(K k) {
            key = k;
        }

    }

}
