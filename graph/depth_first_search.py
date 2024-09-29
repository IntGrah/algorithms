from collections.abc import Iterator
from graph.graph import Graph


def depth_first_search[K](g: Graph[K], r: K) -> Iterator[K]:
    seen: set[K] = set()
    s: list[K] = [r]
    while s:
        u = s.pop()
        if u not in seen:
            yield u
            seen.add(u)
            s.extend(g[u])
