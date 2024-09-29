from util import swap


def quick_sort(a: list) -> None:
    jobs = [(0, len(a) - 1)] # Stack of inclusive intervals
    while jobs:
        lo, hi = jobs.pop()
        if hi - lo <= 0:
            continue
        pivot = a[(lo + hi) // 2] # Middle element
        lt = eq = lo
        gt = hi
        while eq <= gt:
            if a[eq] < pivot:
                swap(a, eq, lt)
                lt += 1
                eq += 1
            elif a[eq] > pivot:
                swap(a, eq, gt)
                gt -= 1
            else:
                eq += 1
        jobs.append((lo, lt - 1))
        jobs.append((gt + 1, hi))
