from heap.binary_heap import BinaryHeap
from heap.binomial_heap import BinomialHeap
from heap.fibonacci_heap import FibonacciHeap


heap = FibonacciHeap[int]()
n5 = heap.push(5)
n6 = heap.push(6)
n8 = heap.push(8)
n7 = heap.push(7)
n1 = heap.push(1)
n3 = heap.push(3)
n2 = heap.push(2)
n4 = heap.push(4)
assert heap.pop_min() == 1
heap.decrease_key(n5, 0)
assert heap.pop_min() == 0
assert heap.pop_min() == 2
assert heap.pop_min() == 3
heap.decrease_key(n7, 5)
assert heap.pop_min() == 4
assert heap.pop_min() == 5
assert heap.pop_min() == 6
assert heap.pop_min() == 8
