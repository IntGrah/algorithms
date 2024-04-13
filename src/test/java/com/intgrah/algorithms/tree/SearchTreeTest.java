package com.intgrah.algorithms.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class SearchTreeTest {

    @Test
    public void simpleTest() {
        SearchTree<Integer> tree = getInstance();

        assertNull(tree.search(3));
        int[] keys = { 5, 7, 3, 6, 4, 2, 8, 1 };

        for (int k : keys)
            tree.insert(k);
        for (int k : keys)
            assertEquals(k, tree.search(k));
        for (int k : keys) {
            tree.delete(k);
            assertNull(tree.search(k));
        }
    }

    protected abstract SearchTree<Integer> getInstance();

    @Test
    public void sortedTest() {
        SearchTree<Integer> tree = getInstance();
        for (int k = 0; k < 10; k++) {
            tree.insert(k);
            assertEquals(k, tree.search(k));
        }
        for (int k = 0; k < 10; k++) {
            tree.delete(k);
            assertNull(tree.search(k));
        }
    }

    @Test
    public void doubleTest() {
        SearchTree<Integer> tree = getInstance();

        int[] keys = new int[] { 3, 4, 8, 1, 0, 9, 2, 5, 2, 3, 2, 5, 7, 1, 8, 3, 5, 8, 1, 3, 9, 7 };
        for (int k : keys) {
            tree.insert(k);
            assertEquals(k, tree.search(k));
        }
        for (int k : keys) {
            tree.delete(k);
            assertNull(tree.search(k));
        }
    }

    @Test
    public void pseudoRandomTest() {
        SearchTree<Integer> tree = getInstance();
        for (int k = 0; k < 1000; k++) {
            tree.insert(k);
            assertEquals(k, tree.search(k));
        }
        for (int k = 0; k < 1000; k++) {
            tree.delete(k);
            assertNull(tree.search(k));
        }
    }

}
