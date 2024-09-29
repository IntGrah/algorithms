from collections.abc import Iterator


class CircularDoubleNode[T]:
    def __init__(self, value: T):
        self.value = value
        self.next = self.prev = self


class CircularDoublyLinkedList[T]:
    def __init__(self):
        self.front: CircularDoubleNode[T] | None = None
        self.len = 0

    def push_front(self, value: T) -> CircularDoubleNode[T]:
        self.front = self.push_back(value)
        return self.front

    def pop_front(self) -> T:
        front = self.front
        self.delete(front)
        return front.value

    def push_back(self, value: T) -> CircularDoubleNode[T]:
        n = CircularDoubleNode(value)
        if self.front is None:
            self.front = n
        else:
            a = self.front
            z = a.prev
            z.next = n
            n.prev = z
            n.next = a
            a.prev = n
        self.len += 1
        return n

    def pop_front(self) -> T:
        if self.front is None:
            raise IndexError
        back = self.front.prev
        self.delete(back)
        return back.value

    def delete(self, a: CircularDoubleNode[T]) -> None:
        b = a.next
        z = a.prev
        b.prev = z
        z.next = b
        if a == self.front:
            self.front = None if a == b else b
        self.len -= 1

    def clear(self) -> None:
        self.front = None
        self.len = 0

    def __iter__(self) -> Iterator[T]:
        n = self.front
        while n is not None:
            yield n.value
            n = n.next
            if n == self.front:
                break

    def __len__(self) -> int:
        return self.len
