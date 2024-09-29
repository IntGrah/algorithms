from util import swap


def bubble_sort(a: list):
    for j in reversed(range(1, len(a))):
        for i in range(j):
            if a[i] > a[i + 1]:
                swap(a, i, i + 1)
