package com.intgrah.algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class SorterTest {

    @Test
    public void testRandom() {
        Comparator<Integer> ord = Comparator.naturalOrder();
        Sorter<Integer> sorter = getInstance(ord);
        Random random = new Random();

        Integer[] a = new Integer[getSize()];
        for (int i = 0; i < getSize(); i++)
            a[i] = random.nextInt();

        sorter.sort(a);
        assertSorted(a);
    }

    protected abstract <T> Sorter<T> getInstance(Comparator<T> ord);

    protected int getSize() { return 1000; }

    public static void assertSorted(Integer[] a) {
        for (int i = 0; i < a.length - 1; i++)
            assertTrue(a[i] <= a[i + 1]);
    }

    @Test
    public void testDuplicates() {
        int n = getSize();
        Comparator<Integer> ord = Comparator.naturalOrder();
        Sorter<Integer> sorter = getInstance(ord);

        Integer[] a = new Integer[n];
        Arrays.fill(a, 0);
        a[getSize() / 2] = 1;

        sorter.sort(a);
        assertSorted(a);
    }

    @Test
    public void testSorted() {
        Comparator<Integer> ord = Comparator.naturalOrder();
        Sorter<Integer> sorter = getInstance(ord);

        Integer[] a = new Integer[getSize()];
        for (int i = 0; i < getSize(); i++)
            a[i] = i;

        sorter.sort(a);
        for (int i = 0; i < getSize(); i++)
            assertEquals(i, a[i]);
    }

    @Test
    public void testReversed() {
        Comparator<Integer> ord = Comparator.naturalOrder();
        Sorter<Integer> sorter = getInstance(ord);

        Integer[] a = new Integer[getSize()];
        for (int i = 0; i < getSize(); i++)
            a[i] = getSize() - 1 - i;

        sorter.sort(a);
        for (int i = 0; i < getSize(); i++)
            assertEquals(i, a[i]);
    }

    @Test
    public void testXShape() {
        assertEquals(0, getSize() % 2);
        Comparator<Integer> ord = Comparator.naturalOrder();
        Sorter<Integer> sorter = getInstance(ord);

        Integer[] a = new Integer[getSize()];
        for (int i = 0; i < getSize(); i++)
            a[i % 2 == 0 ? i : getSize() - i] = i;

        sorter.sort(a);
        for (int i = 0; i < getSize(); i++)
            assertEquals(i, a[i]);
    }

}
