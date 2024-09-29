from collections.abc import Iterator


class SingleNode[T]:
    def __init__(self, value: T) -> None:
        self.value = value
        self.next: SingleNode[T] | None = None


class LinkedList[T]:
    def __init__(self) -> None:
        self.front: SingleNode[T] | None = None
        self.len = 0

    def push_front(self, value: T) -> SingleNode[T]:
        front = SingleNode(value)
        front.next = self.front
        self.front = front
        self.len += 1
        return front

    def pop_front(self) -> T:
        if self.front is None:
            raise IndexError
        value = self.front.value
        self.front = self.front.next
        self.len -= 1
        return value
    
    def clear(self) -> None:
        self.front = None
        self.len = 0
    
    def __iter__(self) -> Iterator[T]:
        n = self.front
        while n is not None:
            yield n.value
            n = n.next
            
    def __len__(self) -> int:
        return self.len
