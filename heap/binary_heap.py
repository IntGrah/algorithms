from util import swap


class BinaryNode[K]:
    def __init__(self, key: K) -> None:
        self.key = key
        self.index: int


class BinaryHeap[K]:
    def __init__(self) -> None:
        self.a: list[BinaryNode[K]] = []
        
    def push(self, key: K) -> BinaryNode[K]:
        n = BinaryNode(key)
        n.index = len(self.a)
        self.a.append(n)
        while n.index > 0 and n.key < self.a[p := (n.index - 1) // 2].key:
            self.a[p].index = n.index
            swap(self.a, p, n.index)
            n.index = p
        return n

    def pop_min(self) -> K:
        if len(self.a) == 0:
            raise IndexError
        min = self.a[0]
        n = self.a.pop()
        if len(self.a) == 0:
            return min.key
        self.a[0] = n
        n.index = 0
        while True:
            l = 2 * n.index + 1
            r = 2 * n.index + 2
            if r < len(self.a) and self.a[l].key >= self.a[r].key < n.key:
                self.a[r].index = n.index
                swap(self.a, n.index, r)
                n.index = r
            elif l < len(self.a) and self.a[l].key < n.key:
                self.a[l].index = n.index
                swap(self.a, n.index, l)
                n.index = l
            else:
                break
        return min.key

    def decrease_key(self, n: BinaryNode[K], key: K) -> None:
        n.key = key
        while n.index > 0 and n.key < self.a[p := (n.index - 1) // 2].key:
            self.a[p].index = n.index
            swap(self.a, p, n.index)
            n.index = p

    def __bool__(self) -> bool:
        return len(self.a) != 0
