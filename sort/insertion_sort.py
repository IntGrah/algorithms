def insertion_sort(a: list) -> None:
    for j in range(1, len(a)):
        x = a[j]
        i = j
        while i > 0 and a[i - 1] > a[i]:
            a[i] = a[i - 1]
            i -= 1
        a[i] = x
