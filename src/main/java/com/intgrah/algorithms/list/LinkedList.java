package com.intgrah.algorithms.list;

import com.intgrah.algorithms.util.EmptyException;

import java.util.Iterator;

public class LinkedList<T> implements Stack<T>, Queue<T> {

    private Node front;
    private Node back;
    private transient int size = 0;

    public T getFront() {
        if (size == 0) { throw new EmptyException(); }
        return front.value;
    }

    public Node pushFront(T value) {
        Node n = new Node(value);
        n.next = front;
        front = n;
        if (size++ == 0) { back = n; }
        return n;
    }

    public T popFront() {
        if (size == 0) { throw new EmptyException(); }
        Node f = front;
        front = f.next;
        if (--size == 0) { back = null; }
        return f.value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        front = back = null;
        size = 0;
    }

    public T getBack() {
        if (size == 0) { throw new EmptyException(); }
        return back.value;
    }

    public List.Node<T> pushBack(T value) {
        Node n = new Node(value);
        if (size++ == 0) {
            front = n;
        } else {
            back.next = n;
        }
        back = n;
        return n;
    }

    public void append(LinkedList<T> l) {
        if (l.size == 0) { return; }
        if (size == 0) {
            front = l.front;
        } else {
            back.next = l.front;
        }
        back = l.back;
        size += l.size;
        l.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node n = front;

            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public T next() {
                T value = n.value;
                n = n.next;
                return value;
            }
        };
    }

    public class Node implements List.Node<T> {

        private T value;
        private Node next;

        Node(T v) {
            value = v;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public void setValue(T v) {
            value = v;
        }

    }

}
