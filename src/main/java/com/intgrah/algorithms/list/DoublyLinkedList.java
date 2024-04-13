package com.intgrah.algorithms.list;

import java.util.Iterator;

public class DoublyLinkedList<T> extends AbstractList<T> implements Deque<T> {

    private Node front;
    private Node back;

    @Override
    public T getFront() {
        assert !isEmpty();
        return front.value;
    }

    @Override
    public T popFront() {
        assert !isEmpty();
        Node f = front;
        front = f.next;
        if (size-- == 1)
            back = null;
        else
            front.prev = null;
        return f.value;
    }

    public AbstractList<T>.Deletable pushBack(T value) {
        Node n = new Node(value);
        if (size++ == 0) {
            front = n;
        } else {
            n.prev = back;
            back.next = n;
        }
        back = n;
        return n;
    }

    public T getBack() {
        assert !isEmpty();
        return back.value;
    }

    public T popBack() {
        assert !isEmpty();
        Node b = back;
        back = b.prev;
        if (size-- == 1)
            front = null;
        else
            back.prev = null;
        return b.value;
    }

    @Override
    public AbstractList<T>.Deletable pushFront(T value) {
        Node n = new Node(value);
        n.next = front;
        front = n;
        if (size++ == 0)
            back = n;
        else
            n.next.prev = n;
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
    public void clear() {
        front = back = null;
        size = 0;
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

    private class Node extends AbstractList<T>.Deletable {

        private Node prev;
        private Node next;
        private boolean deleted = false;

        private Node(T v) { value = v; }

        @Override
        public void delete() {
            if (deleted)
                return;
            deleted = true;
            if (prev == null)
                front = next;
            else
                prev.next = next;
            if (next == null)
                back = prev;
            else
                next.prev = prev;
            size--;
        }

    }

}
