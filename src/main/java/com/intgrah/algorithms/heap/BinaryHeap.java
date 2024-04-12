package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.util.EmptyException;

import java.util.Stack;

import static com.intgrah.algorithms.util.Util.swap;

public class BinaryHeap<K extends Comparable<K>> implements Heap<K> {

    private final Stack<Node> nodes = new Stack<>();

    @Override
    public Decreasable<K> push(K k) {
        Node n = new Node(k);
        n.index = nodes.size();
        nodes.add(n);
        while (n.index > 0) {
            int p = (n.index - 1) / 2;
            if (n.key.compareTo(nodes.get(p).key) >= 0) { break; }
            nodes.get(p).index = n.index;
            swap(nodes, p, n.index);
            n.index = p;
        }
        return n;
    }

    @Override
    public K popMin() throws EmptyException {
        if (nodes.empty()) { throw new EmptyException(); }
        Node min = nodes.get(0);
        Node n = nodes.pop();
        if (nodes.empty()) { return min.key; }
        nodes.set(0, n);
        n.index = 0;
        while (true) {
            int li = 2 * n.index + 1;
            int ri = 2 * n.index + 2;
            Node l = null;
            Node r = null;
            if (li < nodes.size()) {
                l = nodes.get(li);
                if (ri < nodes.size()) { r = nodes.get(ri); }
            }
            if (r != null && l.key.compareTo(r.key) >= 0 && n.key.compareTo(r.key) > 0) {
                swap(nodes, r.index = n.index, n.index = ri);
            } else if (l != null && n.key.compareTo(l.key) > 0) {
                swap(nodes, l.index = n.index, n.index = li);
            } else {
                break;
            }
        }
        return min.key;
    }

    @Override
    public K getMin() throws EmptyException {
        if (nodes.empty()) { throw new EmptyException(); }
        return nodes.getFirst().key;
    }

    @Override
    public boolean isEmpty() { return nodes.empty(); }

    @Override
    public void clear() { nodes.clear(); }

    private class Node implements Heap.Decreasable<K> {

        private K key;
        private int index;

        private Node(K k) { key = k; }

        @Override
        public K getKey() { return key; }

        @Override
        public void decreaseKey(K k) throws KeyException {
            if (k.compareTo(key) > 0) { throw new KeyException(); }
            key = k;
            while (index > 0) {
                int p = (index - 1) / 2;
                if (key.compareTo(nodes.get(p).key) >= 0) { break; }
                nodes.get(p).index = index;
                swap(nodes, p, index);
                index = p;
            }
        }

    }

}
