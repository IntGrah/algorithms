package com.intgrah.algorithms.util;

public class OrderedSemigroupTest {

    public static OrderedSemigroup<Integer> integer() {
        return new OrderedGroup<>() {
            @Override
            public Integer neg(Integer a) { return -a; }

            @Override
            public Integer zero() { return 0; }

            @Override
            public Integer add(Integer a, Integer b) { return a + b; }

            @Override
            public int compare(Integer a, Integer b) { return a - b; }
        };
    }

}
