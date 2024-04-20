package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.Deletable;
import com.intgrah.algorithms.list.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Comparator;

public class StrictFibonacciHeap<K> extends DecreasableHeap<K> {

    private final ArrayList<Rank> rankList = new ArrayList<>();
    private final DoublyLinkedList<Node> queue = new DoublyLinkedList<>();
    private final DoublyLinkedList<Rank> activeRootsReady = new DoublyLinkedList<>();
    private final DoublyLinkedList<Node> oneNodeLossReady = new DoublyLinkedList<>();
    private final DoublyLinkedList<Rank> twoNodeLossReady = new DoublyLinkedList<>();
    private Node root;

    { rankList.add(new Rank()); }

    public StrictFibonacciHeap(Comparator<K> ord) { super(ord); }

    @Override
    public Decreasable pushRef(K k) {
        Node x = new Node();
        Box b = new Box(k);
        b.bind(x);
        if (isEmpty()) {
            root = x;
        } else {
            if (ord.compare(k, root.box.key) <= 0) {
                Node r = root;
                link(r, root = x);
                queue.pushFront(r);
//                root = x;
            } else {
                link(x, root);
                queue.pushFront(x);
            }
            activeRootReduction();
            rootDegreeReduction();
        }
        return b;
    }

    private void link(Node n, Node p) {
        if (n.parentRef != null)
            n.parentRef.delete();
        n.parent = p;
        if (n.isActive)
            n.parentRef = p.activeChildren.pushFront(n);
        else if (!n.isLinkable())
            n.parentRef = p.passiveChildren.pushFront(n);
        else
            n.parentRef = p.linkableChildren.pushFront(n);
    }

    private boolean activeRootReduction() {
        if (activeRootsReady.isEmpty())
            return false;
        Rank r = activeRootsReady.getFront();
        Node x = r.popActiveRoot();
        Node y = r.popActiveRoot();
        x.rankRef = null;
        y.rankRef = null;
        if (ord.compare(x.box.key, y.box.key) > 0) {
            Node z = x;
            x = y;
            y = z;
        }
        Node w = y.parent;
        link(y, x);
        if (w.isLinkable())
            link(w, w.parent);
        x.setRank(x.rank + 1);
        if (!x.linkableChildren.isEmpty()) {
            Node z = x.linkableChildren.getBack();
            link(z, root);
        } else if (!x.passiveChildren.isEmpty()) {
            Node z = x.passiveChildren.getBack();
            link(z, root);
        }
        return true;
    }

    private boolean rootDegreeReduction() {
        if (root.linkableChildren.size() < 3)
            return false;
        ArrayList<Node> xyz = new ArrayList<>();
        xyz.add(root.linkableChildren.popBack());
        xyz.add(root.linkableChildren.popBack());
        xyz.add(root.linkableChildren.popBack());
        xyz.sort(Comparator.comparing(n -> n.box.key, ord));
        Node x = xyz.get(0);
        Node y = xyz.get(1);
        Node z = xyz.get(2);
        x.parentRef = null;
        y.parentRef = null;
        z.parentRef = null;
        x.isActive = y.isActive = true;
        link(z, y);
        link(y, x);
        link(x, root);
        x.setRank(1);
        return true;
    }

    @Override
    public K getMin() {
        assert !isEmpty();
        return root.box.key;
    }

    @Override
    public void deleteMin() {
        assert !isEmpty();
        Node r = root;
        root = null;
        Node x = null;
        for (Node c : r.activeChildren)
            if (x == null || ord.compare(c.box.key, x.box.key) < 0)
                x = c;
        for (Node c : r.passiveChildren)
            if (x == null || ord.compare(c.box.key, x.box.key) < 0)
                x = c;
        for (Node c : r.linkableChildren)
            if (x == null || ord.compare(c.box.key, x.box.key) < 0)
                x = c;
        if (x == null)
            return;
        x.isActive = false;
        if (x.rankRef != null)
            rankList.get(x.rank).removeNode(x);
        x.parentRef.delete();
        x.parentRef = null;
        x.parent = null;
        for (Node c : r.activeChildren)
            link(c, x);
        for (Node c : r.passiveChildren)
            link(c, x);
        for (Node c : r.linkableChildren)
            link(c, x);
        for (Node c : x.activeChildren) {
            rankList.get(c.rank).removeNode(c);
            rankList.get(c.rank).addActiveRoot(c);
            c.setLoss(0);
        }
        root = x;
        for (int i = 0; i < Math.min(2, queue.size()); i++) {
            Node y = queue.getFront();
            queue.pushBack(y);
            if (y.passiveChildren.size() + y.linkableChildren.size() >= 2) {
                for (int j = 0; j < 2; j++) {
                    Node z;
                    if (!y.linkableChildren.isEmpty())
                        z = y.linkableChildren.popFront();
                    else
                        z = y.passiveChildren.popFront();
                    z.parentRef = null;
                    link(z, root);
                }
                if (y.isActive && !y.isActiveRoot())
                    y.setLoss(y.loss + 2);
            }
        }
        if (!twoNodeLossReduction())
            oneNodeLossReduction();
        while (activeRootReduction() || rootDegreeReduction())
            assert true;
    }

    private boolean twoNodeLossReduction() {
        if (twoNodeLossReady.isEmpty())
            return false;
        Rank r = twoNodeLossReady.getFront();
        Node x = r.popTwoNodeLossNode();
        Node y = r.popTwoNodeLossNode();
        x.rankRef = null;
        y.rankRef = null;
        if (ord.compare(x.box.key, y.box.key) > 0) {
            Node z = x;
            x = y;
            y = z;
        }
        Node z = y.parent;
        link(y, x);
        y.setLoss(0);
        if (x != z) {
            x.setRank(x.rank + 1);
            x.setLoss(0);
            z.setRank(z.rank - 1);
            if (!z.isActiveRoot())
                z.setLoss(z.loss + 1);
        }
        return true;
    }

    private boolean oneNodeLossReduction() {
        if (oneNodeLossReady.isEmpty())
            return false;
        Node x = oneNodeLossReady.getFront();
        Node y = x.parent;
        x.setLoss(0);
        link(x, root);
        Rank record = rankList.get(x.rank);
        record.removeNode(x);
        record.addActiveRoot(x);
        y.setRank(y.rank - 1);
        if (!y.isActiveRoot())
            y.setLoss(y.loss + 1);
        return true;
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void clear() { root = null; }

    private class Rank {

        final DoublyLinkedList<Node> activeRoots = new DoublyLinkedList<>();
        final DoublyLinkedList<Node> twoNodeLossPairs = new DoublyLinkedList<>();
        Deletable activeRootsReadyNode;
        Deletable twoNodeLossReadyNode;

        void removeNode(Node x) {
            if (x.rankRef == null)
                return;
            x.rankRef.delete();
            x.rankRef = null;
            refreshActiveRootsReady();
            refreshTwoNodeLossReady();
        }

        void refreshActiveRootsReady() {
            if (activeRoots.size() < 2) {
                if (activeRootsReadyNode != null) {
                    activeRootsReadyNode.delete();
                    activeRootsReadyNode = null;
                }
            } else {
                if (activeRootsReadyNode == null)
                    activeRootsReadyNode = activeRootsReady.pushFront(this);
            }
        }

        void refreshTwoNodeLossReady() {
            if (twoNodeLossPairs.size() < 2) {
                if (twoNodeLossReadyNode != null) {
                    twoNodeLossReadyNode.delete();
                    twoNodeLossReadyNode = null;
                }
            } else {

                if (twoNodeLossReadyNode == null)
                    twoNodeLossReadyNode = twoNodeLossReady.pushFront(this);
            }
        }

        void addActiveRoot(Node x) {
            x.rankRef = activeRoots.pushFront(x);
            refreshActiveRootsReady();
        }

        Node popActiveRoot() {
            Node x = activeRoots.popFront();
            x.rankRef = null;
            refreshActiveRootsReady();
            return x;
        }

        void addTwoNodeLossNode(Node x) {
            x.rankRef = twoNodeLossPairs.pushFront(x);
            refreshTwoNodeLossReady();
        }

        Node popTwoNodeLossNode() {
            Node x = twoNodeLossPairs.popFront();
            x.rankRef = null;
            refreshTwoNodeLossReady();
            return x;
        }

    }


    private class Box extends Decreasable {

        Node ref;

        Box(K k) { super(k); }

        @Override
        public void decreaseKey(K k) {
            assert ord.compare(k, key) <= 0;
            key = k;
            Node x = ref;
            if (x == root)
                return;
            if (ord.compare(key, root.box.key) < 0) {
                root.box.bind(ref);
                bind(root);
            }
            Node y = x.parent;
            x.setLoss(0);
            link(x, root);
            if (x.isActiveRoot()) {
                rankList.get(x.rank).removeNode(x);
                rankList.get(x.rank).addActiveRoot(x);
            }
            if (y.isActive) {
                if (x.isActive)
                    y.setRank(y.rank - 1);
                if (!y.isActiveRoot())
                    y.setLoss(y.loss + 1);
            } else if (y.isLinkable()) {
                link(y, y.parent);
            }
            if (!twoNodeLossReduction())
                oneNodeLossReduction();
            for (int i = 0; i < 6; i++) {
                activeRootReduction();
                rootDegreeReduction();
            }
        }

        void bind(Node x) {
            x.box = this;
            ref = x;
        }

    }


    private class Node {

        final DoublyLinkedList<Node> activeChildren = new DoublyLinkedList<>();
        final DoublyLinkedList<Node> passiveChildren = new DoublyLinkedList<>();
        final DoublyLinkedList<Node> linkableChildren = new DoublyLinkedList<>();
        Box box;
        Node parent = null;
        Deletable parentRef;
        boolean isActive = false;
        int rank = 0;
        Deletable rankRef;
        int loss = 0;
        Deletable lossRef;

        void setLoss(int l) {
            rankList.get(rank).removeNode(this);
            loss = l;
            if (loss == 0) {
                if (lossRef != null) {
                    lossRef.delete();
                    lossRef = null;
                }
            }
            if (loss == 1)
                rankList.get(rank).addTwoNodeLossNode(this);
            else if (loss >= 2) {
                if (lossRef != null)
                    lossRef = oneNodeLossReady.pushFront(this);
            }
        }

        void setRank(int r) {
            rankList.get(rank).removeNode(this);
            rank = r;
            if (rank == rankList.size())
                rankList.add(new Rank());
            if (isActiveRoot())
                rankList.get(rank).addActiveRoot(this);
            else if (loss == 1)
                rankList.get(rank).addTwoNodeLossNode(this);
        }

        boolean isActiveRoot() { return isActive && !parent.isActive; }

        boolean isLinkable() {
            return !isActive && activeChildren.isEmpty() && passiveChildren.isEmpty();
        }

    }

}
