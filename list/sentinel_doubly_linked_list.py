from collections.abc import Iterator


class Sentinel:
    def __init__(self):
        self.next = self.prev = self


class CircularDoubleNode[T](Sentinel):
    def __init__(self, value: T):
        super().__init__()
        self.value = value


class SentinelDoublyLinkedList[T]:
    def __init__(self):
        self.sentinel = Sentinel[T]()
        self.len = 0

    def push_front(self, value: T) -> CircularDoubleNode[T]:
        n = CircularDoubleNode(value)
        front = self.sentinel.next
        self.sentinel.next = n
        n.prev = self.sentinel
        n.next = front
        front.prev = n
        self.len += 1
        return n

    def pop_front(self) -> T:
        front = self.sentinel.next
        if not isinstance(front, CircularDoubleNode):
            raise IndexError
        self.delete(front)
        return front.value

    def push_back(self, value: T) -> CircularDoubleNode[T]:
        n = CircularDoubleNode(value)
        back = self.sentinel.prev
        back.next = n
        n.prev = back
        n.next = self.sentinel
        self.sentinel.prev = n
        self.len += 1
        return n

    def pop_back(self) -> T:
        back = self.sentinel.prev
        if not isinstance(back, CircularDoubleNode):
            raise IndexError
        self.delete(back)
        return back.value

    def delete(self, n: CircularDoubleNode[T]) -> None:
        n.prev.next = n.next
        n.next.prev = n.prev
        self.len -= 1

    def clear(self) -> None:
        self.sentinel.next = self.sentinel.prev = self.sentinel
        self.len = 0

    def __iter__(self) -> Iterator[T]:
        n = self.sentinel.next
        while isinstance(n, CircularDoubleNode):
            yield n.value
            n = n.next
            
    def __len__(self) -> int:
        return self.len
