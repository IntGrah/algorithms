package com.intgrah.algorithms.sort;

import java.util.Comparator;
import java.util.Stack;

import static com.intgrah.algorithms.util.Util.swap;

public class QuickSort<T> extends Sorter<T> {

    public QuickSort(Comparator<T> ord) {
        super(ord);
    }

    @Override
    public void sort(T[] a) {
        Stack<Integer> los = new Stack<>();
        Stack<Integer> his = new Stack<>();
        los.push(0);
        his.push(a.length - 1);
        while (!los.empty()) {
            int lo = los.pop();
            int hi = his.pop();
            if (lo >= hi) {
                continue;
            }
            T p = a[(lo + hi) / 2];
            int lt = lo, eq = lo, gt = hi;
            while (eq <= gt) {
                int comp = ord.compare(a[eq], p);
                if (comp < 0) {
                    swap(a, eq++, lt++);
                } else if (comp > 0) {
                    swap(a, eq, gt--);
                } else {
                    eq++;
                }
            }
            los.push(lo);
            his.push(lt - 1);
            los.push(gt + 1);
            his.push(hi);
        }
    }

}
