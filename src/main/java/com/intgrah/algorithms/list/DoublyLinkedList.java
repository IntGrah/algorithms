package com.intgrah.algorithms.list;

import com.intgrah.algorithms.util.EmptyException;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Deque<T> {

    private Node front;
    private Node back;
    private transient int size = 0;

    @Override
    public T getFront() {
        if (size == 0) { throw new EmptyException(); }
        return front.value;
    }

    @Override
    public T popFront() {
        if (size == 0) { throw new EmptyException(); }
        Node f = front;
        front = f.next;
        if (size == 1) {
            back = null;
        } else {
            front.prev = null;
        }
        size--;
        return f.value;
    }

    public List.Deletable<T> pushBack(T value) {
        Node n = new Node(value);
        if (size == 0) {
            front = n;
        } else {
            n.prev = back;
            back.next = n;
        }
        back = n;
        size++;
        return n;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        front = back = null;
        size = 0;
    }

    public T getBack() {
        if (size == 0) { throw new EmptyException(); }
        return back.value;
    }

    public T popBack() {
        if (size == 0) { throw new EmptyException(); }
        Node b = back;
        back = b.prev;
        if (size == 1) {
            front = null;
        } else {
            back.prev = null;
        }
        size--;
        return b.value;
    }

    @Override
    public List.Deletable<T> pushFront(T value) {
        Node n = new Node(value);
        n.next = front;
        front = n;
        if (size == 0) {
            back = n;
        } else {
            n.next.prev = n;
        }
        size++;
        return n;
    }

    public void append(DoublyLinkedList<T> l) {
        if (l.size == 0) { return; }
        if (size == 0) {
            front = l.front;
        } else {
            back.next = l.front;
            l.front.prev = back;
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

    private class Node implements List.Deletable<T> {

        private T value;
        private Node prev;
        private Node next;

        private Node(T v) {
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

        @Override
        public void delete() {
            if (prev == null) {
                front = next;
            } else {
                prev.next = next;
            }
            if (next == null) {
                back = prev;
            } else {
                next.prev = prev;
            }
            size--;
        }

    }

}
