package com.intgrah.algorithms.tree;

public class RedBlackTree<K extends Comparable<K>> implements SearchTree<K> {

    private Node root = null;
    private boolean balanced = true;

    @Override
    public void insert(K k) {
        root = insert(root, k);
        root.color = Color.BLACK;
    }

    @Override
    public K search(Comparable<K> k) {
        Node n = root;
        while (n != null) {
            int comp = k.compareTo(n.key);
            if (comp < 0)
                n = n.left;
            else if (comp == 0)
                return n.key;
            else
                n = n.right;
        }
        return null;
    }

    @Override
    public void delete(Comparable<K> k) {
        root = delete(root, k);
        if (root != null)
            root.color = Color.BLACK;
        balanced = true;
    }

    private Node delete(Node n, Comparable<K> k) {
        if (n == null)
            return null;
        int comp = k.compareTo(n.key);
        if (comp == 0) {
            if (n.left == null && n.right == null) {
                if (n.color == Color.BLACK)
                    balanced = false;
                return null;
            }
            if (n.left != null && n.right != null) {
                Node s = n.right;
                while (s.left != null)
                    s = s.left;
                n.key = s.key;
                n.right = delete(n.right, s.key);
                if (!balanced)
                    n = balRight(n);
                return n;
            }
            if (n.left == null) {
                n.right.color = Color.BLACK;
                return n.right;
            } else {
                n.left.color = Color.BLACK;
                return n.left;
            }
        }
        if (comp < 0) {
            n.left = delete(n.left, k);
            if (!balanced)
                n = balLeft(n);
        } else {
            n.right = delete(n.right, k);
            if (!balanced)
                n = balRight(n);
        }
        return n;
    }

    private Node balLeft(Node p) {
        Node s = p.right;
        assert s != null;
        if (s.color == Color.BLACK) {
            if (isRed(s.right)) {
                p = p.rotate(Dir.LEFT);
                p.color = p.left.color;
                p.left.color = Color.BLACK;
                p.right.color = Color.BLACK;
                balanced = true;
            } else if (isRed(s.left)) {
                s = s.rotate(Dir.RIGHT);
                s.color = Color.BLACK;
                p = p.rotate(Dir.LEFT);
                p.color = p.left.color;
                p.left.color = Color.BLACK;
                p.right.color = Color.BLACK;
                balanced = true;
            } else {
                if (p.color == Color.RED) {
                    p.color = Color.BLACK;
                    balanced = true;
                }
                s.color = Color.RED;
            }
        } else { // s.color == RED
            p = p.rotate(Dir.LEFT);
            p.color = Color.BLACK;
            p.left.right.color = Color.RED;
            balanced = true;
        }
        return p;
    }

    private Node balRight(Node p) {
        Node s = p.left;
        if (s.color == Color.BLACK) {
            if (isRed(s.left)) {
                p = p.rotate(Dir.RIGHT);
                p.color = p.right.color;
                p.left.color = Color.BLACK;
                p.right.color = Color.BLACK;
                balanced = true;
            } else if (isRed(s.right)) {
                s = s.rotate(Dir.LEFT);
                s.color = Color.BLACK;
                p = p.rotate(Dir.RIGHT);
                p.color = p.right.color;
                p.left.color = Color.BLACK;
                p.right.color = Color.BLACK;
                balanced = true;
            } else {
                if (p.color == Color.RED) {
                    p.color = Color.BLACK;
                    balanced = true;
                }
                s.color = Color.RED;
            }
        } else { // s.color == RED
            p = p.rotate(Dir.RIGHT);
            p.color = Color.BLACK;
            p.right.left.color = Color.RED;
            balanced = true;
        }
        return p;
    }

    private boolean isRed(Node n) {
        return n != null && n.color == Color.RED;
    }

    private Node fixRedRed(Node n) {
        if (n == null || n.color == Color.RED)
            return n;
        if (isRed(n.left)) {
            if (isRed(n.left.right))
                n.left = n.left.rotate(Dir.LEFT);
            if (isRed(n.left.left)) {
                n = n.rotate(Dir.RIGHT);
                n.color = Color.RED;
                n.left.color = Color.BLACK;
                return n;
            }
        }
        if (isRed(n.right)) {
            if (isRed(n.right.left))
                n.right = n.right.rotate(Dir.RIGHT);
            if (isRed(n.right.right)) {
                n = n.rotate(Dir.LEFT);
                n.color = Color.RED;
                n.right.color = Color.BLACK;
                return n;
            }
        }
        return n;
    }

    private Node insert(Node n, K k) {
        if (n == null)
            return new Node(Color.RED, k);
        int comp = k.compareTo(n.key);
        if (comp == 0) {
            n.key = k;
            return n;
        }
        if (comp < 0)
            n.left = insert(n.left, k);
        else
            n.right = insert(n.right, k);
        return fixRedRed(n);
    }


    private enum Color { RED, BLACK }


    private enum Dir { LEFT, RIGHT }


    private class Node {

        private Color color;
        private K key;
        private Node left = null, right = null;

        private Node(Color c, K k) {
            color = c;
            key = k;
        }

        private Node rotate(Dir dir) {
            Node n;
            if (dir == Dir.LEFT) {
                n = right;
                right = n.left;
                n.left = this;
            } else {
                n = left;
                left = n.right;
                n.right = this;
            }
            return n;
        }

    }

}
