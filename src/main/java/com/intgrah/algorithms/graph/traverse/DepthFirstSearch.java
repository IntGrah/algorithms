package com.intgrah.algorithms.graph.traverse;

import com.intgrah.algorithms.graph.Graph;
import com.intgrah.algorithms.list.LinkedList;
import com.intgrah.algorithms.list.Stack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DepthFirstSearch<V> implements Traversal<V> {

    @Override
    public Iterable<V> traverse(Graph<V, ?> g, V s) {
        return () -> new Iterator<>() {
            private final Set<V> seen = new HashSet<>();
            private final Stack<V> stack = new LinkedList<>();

            {
                stack.pushFront(s);
            }

            @Override
            public boolean hasNext() {
                return stack.size() != 0;
            }

            @Override
            public V next() {
                V u = stack.popFront();
                for (V v : g.getNeighbors(u)) {
                    if (!seen.contains(v)) {
                        seen.add(v);
                        stack.pushFront(v);
                    }
                }
                return u;
            }
        };
    }

}
