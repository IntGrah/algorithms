from collections.abc import Iterator
from graph import Graph


def topological_sort[V](g: Graph[V]) -> Iterator[V]:
    indegree = {v: 0 for v in g}
    for u, vs in g.items():
        for v in vs:
            indegree[v] += 1
    s = [u for u, d in indegree.items() if d == 0]
    while s:
        u = s.pop()
        yield u
        for v in g[u]:
            indegree[v] -= 1
            if indegree[v] == 0:
                s.append(v)

g = {
    0: [],
    1: [],
    2: [3],
    3: [1],
    4: [0, 1],
    5: [0, 2],
}
