from collections.abc import Mapping
from graph import WeightedGraph
from heap.fibonacci_heap import *


def dijkstra[K, W](g: WeightedGraph[K, W], s: K, inf: W) -> Mapping[K, W]:
    dist: dict[K, W] = {}
    node: dict[K, FibonacciNode[tuple[W, K]]] = {}
    q = FibonacciHeap[tuple[W, K]]()
    for v in g:
        d = 0 if v == s else inf
        dist[v] = d
        node[v] = q.push((d, v))

    while q:
        dist_u, u = q.pop_min()
        for v, weight_uv in g[u].items():
            dist_v = dist_u + weight_uv
            if dist_v < dist[v]:
                dist[v] = dist_v
                q.decrease_key(node[v], (dist_v, v))
    return dist
