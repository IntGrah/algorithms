from util import swap


def heap_sort(a: list):
    start = len(a) // 2 # First non-leaf node
    end = len(a)
    while end > 1:
        if start == 0:
            end -= 1
            swap(a, 0, end)
        else:
            start -= 1
        i = start # Sift down
        while True:
            l = 2 * i + 1
            r = 2 * i + 2
            if r < end and a[l] <= a[r] > a[i]:
                swap(a, i, r)
                i = r
            elif l < end and a[l] > a[i]:
                swap(a, i, l)
                i = l
            else:
                break
