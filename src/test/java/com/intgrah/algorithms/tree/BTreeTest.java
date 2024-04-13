package com.intgrah.algorithms.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BTreeTest extends SearchTreeTest {

    @Test
    public void largeTest() {
        BTree<Integer> tree = getInstance();
        int k = 712;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 1000; j++) {
                tree.insert(i * 1000 + k);
                k = (401 * k + 417) % 1000;
            }
        }
        for (int i = 0; i < 100000; i++)
            assertEquals(i, tree.search(i));

        k = 135781;
        for (int i = 0; i < 100000; i++) {
            tree.delete(k);
            assertNull(tree.search(k));
            k = (k + 538721) % 100000;
        }
    }

    @Override
    protected BTree<Integer> getInstance() {
        return new BTree<>(100);
    }

}
