def depth_first_search(g, r):
    seen = {r}
    s = [r]
    while s:
        u = s.pop()
        yield u
        for v in g[u]:
            if v not in seen:
                seen.add(v)
                s.append(v)
# 0 -> 1, 0 -> 2, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 3 
g = {
    0: [1, 2],
    1: [2],
    2: [0, 3],
    3: [3]
}

print(list(depth_first_search(g, 2)))
# 2 0 1 3