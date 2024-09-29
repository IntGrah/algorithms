def merge_sort(a: list) -> None:
    aux = [0] * (len(a) // 2)
    w = 1
    while w < len(a):
        for r in range(len(a), 0, -2 * w):
            m = max(r - w, 0)
            l = max(m - w, 0)
            aux[: m - l] = a[l : m]
            lm = m - l
            i = 0
            while l < r:
                if i < lm and (m == r or aux[i] <= a[m]):
                    a[l] = aux[i]
                    i += 1
                else:
                    a[l] = a[m]
                    m += 1
                l += 1
        w *= 2
