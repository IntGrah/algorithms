package com.intgrah.algorithms.graph.traverse;

import com.intgrah.algorithms.graph.Graph;

public interface Traversal<V> {

    Iterable<V> traverse(Graph<V, ?> g, V s);

}
