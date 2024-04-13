package com.intgrah.algorithms.graph.path;

import com.intgrah.algorithms.util.OrderedGroup;

public class JohnsonTest extends AllShortestPathsTest {

    @Override
    protected Johnson<Integer, Integer> getInstance() {
        return new Johnson<>(new OrderedGroup<>() {
            @Override
            public Integer neg(Integer a) { return -a; }

            @Override
            public Integer zero() { return 0; }

            @Override
            public Integer add(Integer a, Integer b) { return a + b; }

            @Override
            public int compare(Integer a, Integer b) { return a - b; }
        });
    }

}
