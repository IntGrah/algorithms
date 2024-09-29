from util import swap


def selection_sort(a: list) -> None:
    for i in range(len(a)):
        min = i
        for j in range(i + 1, len(a)):
            if a[j] < a[min]: # Find minimum
                min = j
        swap(a, i, min) # Put in place
