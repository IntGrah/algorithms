package com.intgrah.algorithms.heap;

import java.util.Comparator;

public class PairingHeapTest extends DecreasableHeapTest {

    @Override
    protected <T> PairingHeap<T> getInstance(Comparator<T> ord) {
        return new PairingHeap<>(ord);
    }

}
