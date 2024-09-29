from __future__ import annotations


class BinomialNode[K]:
    def __init__(self, key: K) -> None:
        self.key = key
        self.parent: BinomialNode[K] | None = None
        self.children = list[BinomialNode[K]]()

    def merge(self, n: BinomialNode[K]) -> BinomialNode[K]:
        if self.key < n.key:
            n.parent = self
            self.children.append(n)
            return self
        else:
            self.parent = n
            n.children.append(self)
            return n


class BinomialHeap[K]:
    def __init__(self) -> None:
        self.min: BinomialNode[K] | None = None
        self.roots: list[BinomialNode[K]] = []

    def add(self, n: BinomialNode[K]) -> None:
        while True:
            if (deg := len(n.children)) >= len(self.roots):
                for _ in range(deg - len(self.roots)):
                    self.roots.append(None)
                self.roots.append(n)
                return
            n2 = self.roots[deg]
            if n2 is None:
                self.roots[deg] = n
                return
            n = n.merge(n2)
            self.roots[deg] = None

    def push(self, key: K) -> BinomialNode[K]:
        self.add(n := BinomialNode(key))
        if self.min is None or n.key < self.min.key:
            self.min = n
        return n

    def pop_min(self) -> K:
        if (min := self.min) is None:
            raise IndexError
        self.roots[len(min.children)] = None
        for n in min.children:
            n.parent = None
            self.add(n)
        self.min = None
        for n in self.roots:
            if n is not None and (self.min is None or n.key < self.min.key):
                self.min = n
        return min.key

    def decrease_key(self, n: BinomialNode[K], k: K) -> None:
        assert k <= n.key
        n.key = k
        if k < self.min.key:
            self.min = n
        while (p := n.parent) is not None and k < p.key: 
            # Swap n and p
            t_deg = len(n.children)
            p_deg = len(p.children)
            p.children[t_deg] = p
            if p.parent is None:
                self.roots[p_deg] = n
            else:
                p.parent.children[p_deg] = n
            n.parent, p.parent = p.parent, n
            n.children, p.children = p.children, n.children

    def __bool__(self) -> bool:
        return self.min is not None
