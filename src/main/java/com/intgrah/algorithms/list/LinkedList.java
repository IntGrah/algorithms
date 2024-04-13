package com.intgrah.algorithms.list;

import java.util.Iterator;

public class LinkedList<T> extends AbstractList<T> implements Stack<T>, Queue<T> {

    private Node front;
    private Node back;

    public T getFront() {
        assert !isEmpty();
        return front.value;
    }

    public Node pushFront(T value) {
        Node n = new Node(value);
        n.next = front;
        front = n;
        if (size++ == 0)
            back = n;
        return n;
    }

    public T popFront() {
        assert !isEmpty();
        Node f = front;
        front = f.next;
        if (--size == 0)
            back = null;
        return f.value;
    }

    public void clear() {
        front = back = null;
        size = 0;
    }

    public T getBack() {
        assert !isEmpty();
        return back.value;
    }

    public AbstractList<T>.Node pushBack(T value) {
        Node n = new Node(value);
        if (size++ == 0)
            front = n;
        else
            back.next = n;
        back = n;
        return n;
    }

    public void append(LinkedList<T> l) {
        if (size == 0)
            front = l.front;
        else
            back.next = l.front;
        back = l.back;
        size += l.size;
        l.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node n = front;

            @Override
            public boolean hasNext() { return n != null; }

            @Override
            public T next() {
                T value = n.value;
                n = n.next;
                return value;
            }
        };
    }

    public class Node extends AbstractList<T>.Node {

        private Node next;

        Node(T v) { value = v; }

    }

}
