package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.DoublyLinkedList;
import com.intgrah.algorithms.list.List;
import com.intgrah.algorithms.util.EmptyException;

import java.util.HashMap;

public class FibonacciHeap<K extends Comparable<K>> implements Heap<K> {

    private final DoublyLinkedList<Node> roots = new DoublyLinkedList<>();
    private Node min = null;

    @Override
    public Decreasable<K> push(K k) {
        Node n = new Node(k);
        addRoot(n);
        return n;
    }

    private void addRoot(Node n) {
        n.loser = false;
        n.parent = null;
        n.node = roots.pushBack(n);
        if (min == null || n.key.compareTo(min.key) < 0) { min = n; }
    }

    @Override
    public K popMin() throws EmptyException {
        if (min == null) { throw new EmptyException(); }
        min.node.delete();
        roots.append(min.children);
        HashMap<Integer, Node> d = new HashMap<>();
        for (Node n : roots) {
            int deg = n.children.size();
            while (d.containsKey(deg))
                n = n.merge(d.remove(deg));
            d.put(deg, n);
        }
        roots.clear();
        K minKey = min.key;
        min = null;
        for (Node n : d.values())
            addRoot(n);
        return minKey;
    }

    @Override
    public K getMin() throws EmptyException {
        if (min == null) { throw new EmptyException(); }
        return min.key;
    }

    @Override
    public boolean isEmpty() { return min == null; }

    @Override
    public void clear() {
        min = null;
        roots.clear();
    }

    private class Node implements Heap.Decreasable<K> {

        private final DoublyLinkedList<Node> children = new DoublyLinkedList<>();
        private K key;
        private boolean loser = false;
        private Node parent = null;
        private List.Deletable<Node> node;

        private Node(K k) {
            key = k;
        }

        private Node merge(Node n) {
            if (key.compareTo(n.key) < 0) {
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
        public K getKey() { return key; }

        @Override
        public void decreaseKey(K k) throws KeyException {
            if (k.compareTo(key) > 0) { throw new KeyException(); }
            key = k;
            if (parent == null) {
                if (k.compareTo(min.key) < 0) { min = this; }
                return;
            }
            Node n = this;
            while (n.parent != null) {
                Node p = n.parent;
                n.node.delete();
                addRoot(n);
                if (!p.loser) {
                    if (p.parent != null) { p.loser = true; }
                    return;
                }
                n = p;
            }
        }

    }

}
