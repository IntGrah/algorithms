package com.intgrah.algorithms.heap;

import java.util.Comparator;
import java.util.Stack;

import static com.intgrah.algorithms.util.Util.swap;

public class BinaryHeap<K> extends Heap<K> {

    private final Stack<K> nodes = new Stack<>();

    public BinaryHeap(Comparator<K> ord) { super(ord); }

    @Override
    public void push(K k) {
        int i = nodes.size();
        nodes.add(k);
        while (i > 0) {
            int p = (i - 1) / 2;
            if (ord.compare(k, nodes.get(p)) >= 0)
                break;
            swap(nodes, p, i);
            i = p;
        }
    }

    @Override
    public K getMin() {
        assert !isEmpty();
        return nodes.getFirst();
    }

    @Override
    public void deleteMin() {
        assert !isEmpty();
        K n = nodes.pop();
        if (isEmpty())
            return;
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
            if (r != null && ord.compare(l, r) >= 0 && ord.compare(n, r) > 0)
                swap(nodes, i, i = ri);
            else if (l != null && ord.compare(n, l) > 0)
                swap(nodes, i, i = li);
            else
                break;
        }
    }

    @Override
    public boolean isEmpty() { return nodes.empty(); }

    @Override
    public void clear() { nodes.clear(); }

}
