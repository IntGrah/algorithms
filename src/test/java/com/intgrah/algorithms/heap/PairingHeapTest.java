package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class PairingHeapTest extends DecreasableHeapTest {

    @Override
    protected PairingHeap<Integer> getInstance() {
        return new PairingHeap<Integer>(Comparator.naturalOrder());
    }

}
