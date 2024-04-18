package com.intgrah.algorithms.list;

import java.util.Iterator;

public class DoublyLinkedList<T> extends AbstractList<T> implements
        Deque<T, DoublyLinkedList<T>.Node> {

    Node front;
    Node back;

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
        if (--size == 0)
            back = null;
        else
            front.prev = null;
        return f.value;
    }

    @Override
    public Node pushBack(T value) {
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

    @Override
    public T getBack() {
        assert !isEmpty();
        return back.value;
    }

    @Override
    public T popBack() {
        assert !isEmpty();
        Node b = back;
        back = b.prev;
        if (--size == 0)
            front = null;
        else
            back.next = null;
        return b.value;
    }

    @Override
    public Node pushFront(T value) {
        Node n = new Node(value);
        if (size++ == 0) {
            back = n;
        } else {
            n.next = front;
            front.prev = n;
        }
        front = n;
        return n;
    }

    public void append(DoublyLinkedList<T> l) {
        if (l.size == 0)
            return;
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

    public class Node implements Deletable {

        private final T value;
        private Node prev;
        private Node next;

        Node(T v) { value = v; }

        public void delete() {
            assert !isEmpty();
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
