from collections.abc import Iterator


class DoubleNode[T]:
    def __init__(self, value: T):
        self.value = value
        self.next: DoubleNode[T] | None = None
        self.prev: DoubleNode[T] | None = None

class DoublyLinkedList[T]:
    def __init__(self):
        self.front: DoubleNode[T] | None = None
        self.back: DoubleNode[T] | None = None
        self.len = 0

    def push_front(self, value: T) -> DoubleNode[T]:
        n = DoubleNode(value)
        if self.front is None:
            self.front = self.back = n
        else:
            n.next = self.front
            self.front.prev = n
            self.front = n
        self.len += 1
        return n

    def pop_front(self) -> T:
        if self.front is None:
            raise IndexError
        f = self.front
        self.front = f.next
        self.front.prev = None
        self.len -= 1
        return f.value
    
    def push_back(self, value: T) -> DoubleNode[T]:
        n = DoubleNode(value)
        if self.back is None:
            self.front = self.back = n
        else:
            n.prev = self.back
            self.back.next = n
            self.back = n
        self.len += 1
        return n

    def pop_back(self) -> T:
        b = self.back
        self.back = b.prev
        self.back.next = None
        self.len -= 1
        return b.value
    
    def delete(self, n: DoubleNode[T]) -> None:
        if n.prev is None:
            self.front = n.next
        else:
            n.prev.next = n.next
        if n.next is None:
            self.back = n.prev
        else:
            n.next.prev = n.prev
        self.len -= 1
            
    def clear(self) -> None:
        self.front = self.back = None
        self.len = 0
    
    def __iter__(self) -> Iterator[T]:
        n = self.front
        while n is not None:
            yield n.value
            n = n.next

    def __len__(self) -> int:
        return self.len
