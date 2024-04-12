package com.intgrah.algorithms.list;

public interface List<T> extends Iterable<T> {

    interface Node<T> {

        T getValue();

        void setValue(T v);

    }


    interface Deletable<T> extends Node<T> {

        void delete();

    }

}
