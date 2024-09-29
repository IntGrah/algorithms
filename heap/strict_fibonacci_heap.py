from collections import deque
from __future__ import annotations

class Box[K]:
    def __init__(self, k: K) -> None:
        self.key = k
        self.node: Node = None
        
    def bind(self, n: Node) -> None:
        n.box = self
        self.node = n


class Node[K]:
    def __init__(self) -> None:
        self.active = list[Node]()
        self.passive = list[Node]()
        self.linkable = list[Node]()

        self.box: Box = None
        self.parent: Node[K] | None = None
        self.isActive = False
        self.rank = 0
        self.loss = 0
        
    def detach(self) -> None:
        assert self.parent is not None
        if self.isActive:
            self.parent.active.remove(self)
        elif not self.isLinkable():
            self.parent.passive.remove(self)
        else:
            self.parent.linkable.remove(self)
        self.parent = None
            
    def attach(self, p: Node[K]) -> None:
        assert self.parent is None
        self.parent = p
        if self.isActive:
            p.active.append(self)
        elif not self.isLinkable():
            p.passive.append(self)
        else:
            p.linkable.append(self)
            
    def reattach(self) -> None:
        p = self.parent
        self.detach()
        self.attach(p)

    def isActiveRoot(self):
        return self.isActive and not self.parent.isActive

    def isLinkable(self):
        return not self.isActive and self.activeChildren.isEmpty() and self.passiveChildren.isEmpty()

    

class Rank[K]:
    def __init__(self) -> None:
        self.activeRoots: list[Node[K]] = []
        self.twoNodeLossPairs: list[Node[K]] = []


class StrictFibonacciHeap[K]:
    def __init__(self) -> None:
        self.rank_list: list[Rank] = [Rank()]
        self.queue = deque[Node] = deque()
        # self.activeRootsReady = set[Rank]()
        # self.oneNodeLossReady = set[Node]()
        # self.twoNodeLossReady = set[Rank]()
        # self.nodes = set[Node]()
        self.root: Node = None

    def push(self, k: K):
        x = Node[K]()
        b = Box(k)
        b.bind(x)
        # self.nodes.add(x) ############################
        if self.is_empty():
            self.root = x
        else:
            if k <= self.root.box.key:
                r = self.root
                self.root = x
                r.attach(x)
                self.queue.append(r)
            else:
                x.attach(self.root)
                self.queue.pushFront(x)
            self.activeRootReduction()
            self.rootDegreeReduction()

        return b


    def activeRootReduction(self) -> bool:
        # Check if available
        ready = [r for r in self.rank_list if len(r.activeRoots) >= 2]
        if not ready:
            return False
        r = ready[0]
        x = r.activeRoots.pop()
        y = r.activeRoots.pop()
        if x.box.key > y.box.key:
            x, y = y, x
        w = y.parent
        y.detach()
        y.attach(x)
        if w.isLinkable():
            w.reattach()
        x.setRank(x.rank + 1)
        if len(x.linkable) > 0:
            z = x.linkable[-1]
            z.detach()
            z.attach(self.root)
        elif not x.passiveChildren.isEmpty():
            z = x.passiveChildren[-1]
            z.detach()
            z.attach(self.root)
        return True

    def rootDegreeReduction(self) -> bool:
        if len(self.root.linkable) < 3:
            return False
        xyz = [self.root.linkable.pop() for _ in range(3)]
        xyz.sort(key=lambda n: n.box.key)
        x, y, z = xyz
        x.detach()
        y.detach()
        z.detach()
        x.isActive = True
        y.isActive = True
        z.attach(y)
        y.attach(x)
        x.setRank(1)
        x.attach(self.root)
        return True

    def oneNodeLossReduction(self) -> bool:
        if not self.oneNodeLossReady.isEmpty():
            return False
        x = self.oneNodeLossReady.getFront()
        Node y = x.parent
        x.setLoss(0)
        link(x, root)
        Rank record = rankList.get(x.rank)
        record.removeNode(x)
        record.addActiveRoot(x)
        y.setRank(y.rank - 1)
        if (!y.isActiveRoot())
            y.setLoss(y.loss + 1)
        return true

    def get_min(self) -> K:
        assert not self.is_empty()
        return self.root.box.key

    def delete_min(self) -> None:
        assert not self.is_empty()
        r = self.root
        self.root = None
        x: Node = None
        for c in r.active:
            if x is None or c.box.key < x.box.key:
                x = c
        for c in r.passive:
            if x is None or c.box.key < x.box.key:
                x = c
        for c in r.linkable:
            if x is None or c.box.key < x.box.key:
                x = c
        if x == None:
            return
        x.isActive = False
        if x.rankRef is not None:
            rankList.get(x.rank).removeNode(x)
        x.parentRef.delete()
        x.parentRef = None
        x.parent = None
        for c in r.active:
            c.attach(x)
        for c in r.passive:
            c.attach(x)
        for c in r.linkable:
            c.attach(x)
        for c in x.activeChildren:
            rankList.get(c.rank).removeNode(c)
            rankList.get(c.rank).addActiveRoot(c)
            c.setLoss(0)
        self.root = x
        for i in range(min(2, len(self.queue))):
            y = self.queue.pop()
            self.queue.append(y)
            if (y.passiveChildren.size() + y.linkableChildren.size() >= 2):
                for j in range(2):
                    if not y.linkableChildren.isEmpty():
                        z = y.linkableChildren.popFront()
                    else:
                        z = y.passiveChildren.popFront()
                    z.parentRef = None
                    link(z, root)
                if y.isActive and not y.isActiveRoot():
                    y.setLoss(y.loss + 2)
        if not twoNodeLossReduction():
            oneNodeLossReduction()
        while activeRootReduction() or rootDegreeReduction():
            assert True

    def twoNodeLossReduction(self) -> bool:
        if twoNodeLossReady.isEmpty():
            return False
        Rank r = twoNodeLossReady.getFront()
        Node x = r.popTwoNodeLossNode()
        Node y = r.popTwoNodeLossNode()
        x.rankRef = None
        y.rankRef = None
        if (ord.compare(x.box.key, y.box.key) > 0) {
            Node z = x
            x = y
            y = z
        }
        Node z = y.parent
        link(y, x)
        y.setLoss(0)
        if (x != z) {
            x.setRank(x.rank + 1)
            x.setLoss(0)
            z.setRank(z.rank - 1)
            if (!z.isActiveRoot())
                z.setLoss(z.loss + 1)
        }
        return true
    
    def decreaseKey(self, b: Box, k: K) -> None:
        assert k <= box.key
        b.key = k
        x = self.node
            if x == root:
                return
            if key < root.box.key:
                root.box.bind(ref)
                bind(root)

            Node y = x.parent
            x.setLoss(0)
            link(x, root)
            if (x.isActiveRoot()) {
                rankList.get(x.rank).removeNode(x)
                rankList.get(x.rank).addActiveRoot(x)
            }
            if (y.isActive) {
                if (x.isActive)
                    y.setRank(y.rank - 1)
                if (!y.isActiveRoot())
                    y.setLoss(y.loss + 1)
            } else if (y.isLinkable()) {
                link(y, y.parent)
            }
            if (!twoNodeLossReduction())
                oneNodeLossReduction()
            for i in range(6):
                activeRootReduction()
                rootDegreeReduction()


    def is_empty(self) -> bool:
        return self.root == None

    def clear(self) -> None:
        self.root = None
