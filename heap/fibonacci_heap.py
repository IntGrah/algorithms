from __future__ import annotations
from list.doubly_linked_list import *


class FibonacciNode[K]:
    def __init__(self, key: K) -> None:
        self.key = key
        self.loser = False
        self.parent: FibonacciNode[K] | None = None
        self.children = DoublyLinkedList[FibonacciNode[K]]()
        self.node: DoubleNode[FibonacciNode[K]]
        
    def merge(self, n: FibonacciNode[K]) -> FibonacciNode[K]:
        if self.key < n.key:
            n.parent = self
            n.node = self.children.push_back(n)
            return self
        else:
            self.parent = n
            self.node = n.children.push_back(self)
            return n


class FibonacciHeap[K]:
    def __init__(self) -> None:
        self.min: FibonacciNode[K] | None = None
        self.roots = DoublyLinkedList[FibonacciNode[K]]()
    
    def add_root(self, n: FibonacciNode[K]) -> None:
        n.loser = False
        n.parent = None
        n.node = self.roots.push_back(n)
        if self.min is None or n.key < self.min.key:
            self.min = n

    def push(self, key: K) -> FibonacciNode[K]:
        self.add_root(n := FibonacciNode(key))
        return n

    def pop_min(self) -> K:
        if (min := self.min) is None:
            raise IndexError
        self.roots.delete(min.node)
        d: dict[int, FibonacciNode[K]] = {}
        for n in list(self.roots) + list(min.children):
            while (deg := len(n.children)) in d:
                n = n.merge(d[deg])
                del d[deg]
            d[len(n.children)] = n
        self.roots.clear()
        self.min = None
        for n in d.values():
            self.add_root(n)
        return min.key
    
    def decrease_key(self, n: FibonacciNode[K], key: K) -> None:
        assert key <= n.key
        n.key = key
        if n.parent is None:
            if n.key < self.min.key:
                self.min = n
            return
        while (p := n.parent) is not None:
            p.children.delete(n.node)
            self.add_root(n)
            if not p.loser:
                if p.parent is not None:
                    p.loser = True
                return
            n = p
            
    def __bool__(self) -> bool:
        return self.min is not None

fh = FibonacciHeap[int]()
n5 = fh.push(5)
n6 = fh.push(6)
n8 = fh.push(8)
n7 = fh.push(7)
n1 = fh.push(1)
n3 = fh.push(3)
n2 = fh.push(2)
n4 = fh.push(4)
assert fh.pop_min() == 1
fh.decrease_key(n5, 0)
assert fh.pop_min() == 0
assert fh.pop_min() == 2
assert fh.pop_min() == 3
fh.decrease_key(n7, 5)
assert fh.pop_min() == 4
assert fh.pop_min() == 5
assert fh.pop_min() == 6
assert fh.pop_min() == 8
