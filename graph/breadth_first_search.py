from collections import deque
from collections.abc import Iterator
from graph.graph import Graph


def breadth_first_search[K](g: Graph[K], r: K) -> Iterator[K]:
    seen: set[K] = set()
    q = deque[K]()
    q.append(r)
    while q:
        u = q.pop()
        if u not in seen:
            yield u
            seen.add(u)
            q.extend(g[u])
