package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.util.EmptyException;

import java.util.Stack;

import static com.intgrah.algorithms.util.Util.swap;

public class BasicBinaryHeap<K extends Comparable<K>> implements BasicHeap<K> {

    private final Stack<K> nodes = new Stack<>();

    @Override
    public Void push(K k) {
        int i = nodes.size();
        nodes.add(k);
        while (i > 0) {
            int p = (i - 1) / 2;
            if (k.compareTo(nodes.get(p)) >= 0) { break; }
            swap(nodes, p, i);
            i = p;
        }
        return null;
    }

    @Override
    public K popMin() throws EmptyException {
        if (nodes.empty()) { throw new EmptyException(); }
        K min = nodes.get(0);
        K n = nodes.pop();
        if (nodes.empty()) { return min; }
        nodes.set(0, n);
        int i = 0;
        while (true) {
            int li = 2 * i + 1;
            int ri = 2 * i + 2;
            K l = null;
            K r = null;
            if (li < nodes.size()) {
                l = nodes.get(li);
                if (ri < nodes.size()) { r = nodes.get(ri); }
            }
            if (r != null && l.compareTo(r) >= 0 && n.compareTo(r) > 0) {
                swap(nodes, i, i = ri);
            } else if (l != null && n.compareTo(l) > 0) {
                swap(nodes, i, i = li);
            } else {
                break;
            }
        }
        return min;
    }

    @Override
    public K getMin() throws EmptyException {
        if (nodes.empty()) { throw new EmptyException(); }
        return nodes.getFirst();
    }

    @Override
    public boolean isEmpty() { return nodes.empty(); }

    @Override
    public void clear() { nodes.clear(); }

}
