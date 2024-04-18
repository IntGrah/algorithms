package com.intgrah.algorithms.heap;

import com.intgrah.algorithms.list.Deletable;
import com.intgrah.algorithms.list.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Comparator;

public class StrictFibonacciHeap<K> extends DecreasableHeap<K> {

    private final ActiveRecord activeRecord = new ActiveRecord(true);
    private final ArrayList<Rank> rankList = new ArrayList<>();
    private final DoublyLinkedList<Node> queue = new DoublyLinkedList<>();
    private final DoublyLinkedList<Rank> activeRootsReady = new DoublyLinkedList<>();
    private final DoublyLinkedList<Rank> oneNodeLossReady = new DoublyLinkedList<>();
    private final DoublyLinkedList<Rank> twoNodeLossReady = new DoublyLinkedList<>();
    private Node root;

    { rankList.add(new Rank()); }

    public StrictFibonacciHeap(Comparator<K> ord) { super(ord); }

    @Override
    public Decreasable pushRef(K k) {
        Node x = new Node(k);
        if (isEmpty()) {
            root = x;
        } else {
            if (ord.compare(k, root.box.key) <= 0) {
                link(root, x);
                root.queueRef = queue.pushFront(root);
                root = x;
            } else {
                link(x, root);
                x.queueRef = queue.pushFront(x);
            }
            if (!activeRootsReady.isEmpty())
                activeRootReduction();
            if (root.passiveLinkableChildren.size() >= 3)
                rootDegreeReduction();
        }
        return x.box;
    }

    private void link(Node x, Node y) {
        assert ord.compare(x.box.key, y.box.key) >= 0;
        // remove x from its original parent
        if (x.parentRef != null)
            x.parentRef.delete();
        // link node x and y
        x.parent = y;
        if (x.isActive())
            x.parentRef = y.activeChildren.pushFront(x);
        else if (!x.isLinkable())
            x.parentRef = y.passiveChildren.pushFront(x);
        else
            x.parentRef = y.passiveLinkableChildren.pushFront(x);
    }

    private void activeRootReduction() {
        assert !activeRootsReady.isEmpty();
        Rank r = activeRootsReady.getFront();
        assert r.activeRoots.size() >= 2;
        Node x = r.popActiveRoot();
        Node y = r.popActiveRoot();
        x.rankRef = null;
        y.rankRef = null;
        // Performed on two active roots x and y
        assert x.isActiveRoot();
        assert y.isActiveRoot();
        assert x.loss == 0;
        assert y.loss == 0;
        // with equal rank r
        assert x.rank == y.rank;

        if (ord.compare(x.box.key, y.box.key) > 0) {
            // Swap x and y
            Node z = x;
            x = y;
            y = z;
        }
        // W.L.O.G. x.key <= y.key
        assert ord.compare(x.box.key, y.box.key) <= 0;
        // Link y to x
        link(y, x);
        // thus x's rank is increased by 1
        x.setRank(x.rank + 1);
        if (!x.passiveChildren.isEmpty()) {
            // If the rightmost child z of x is passive, link z to the root
            Node z = x.passiveChildren.getBack();
            link(z, root);
        }
    }

    private void rootDegreeReduction() {
        // Performed on the three rightmost passive linkable children x, y, z of the root
        assert root.passiveLinkableChildren.size() >= 3;
        ArrayList<Node> xyz = new ArrayList<>();
        xyz.add(root.passiveLinkableChildren.popBack());
        xyz.add(root.passiveLinkableChildren.popBack());
        xyz.add(root.passiveLinkableChildren.popBack());
        // Assume x.key <= y.key <= z.key
        xyz.sort(Comparator.comparing(n -> n.box.key, ord));
        Node x = xyz.get(0);
        Node y = xyz.get(1);
        Node z = xyz.get(2);
        x.parentRef = null;
        y.parentRef = null;
        z.parentRef = null;
        assert ord.compare(x.box.key, y.box.key) <= 0;
        assert ord.compare(y.box.key, z.box.key) <= 0;
        // Make x and y active
        x.flag = y.flag = activeRecord;
        assert x.isActiveRoot();
        rankList.get(x.rank).addActiveRoot(x);
        // Link z to y
        link(z, y);
        // Link y to x
        link(y, x);
        // Since x is active, it now has loss 0 and rank 1
        x.setRank(1);
        assert x.loss == 0;
        // (i.e. y)
        assert x.activeChildren.getFront() == y;
        // make x the leftmost child of the root
        link(x, root);
        // y now has loss 0 and rank 0
        assert y.rank == 0;
        assert y.loss == 0;
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
        // Scan through the children of the root and find node x with the minimum value
        for (Node c : r.activeChildren)
            if (x == null || ord.compare(c.box.key, x.box.key) < 0)
                x = c;
        for (Node c : r.passiveChildren)
            if (x == null || ord.compare(c.box.key, x.box.key) < 0)
                x = c;
        for (Node c : r.passiveLinkableChildren)
            if (x == null || ord.compare(c.box.key, x.box.key) < 0)
                x = c;
        if (x == null)
            return;
        // Make x passive
        x.flag = ActiveRecord.passive;
        if (x.rankRef != null)
            rankList.get(x.rank).removeNode(x);
        x.parentRef.delete();
        x.parentRef = null;
        x.parent = null;
        // Link all the other children of the root to x
        for (Node c : r.activeChildren)
            link(c, x);
        for (Node c : r.passiveChildren)
            link(c, x);
        for (Node c : r.passiveLinkableChildren)
            link(c, x);
        // Make x the new root
        root = x;
        // Do this twice:
        for (int i = 0; i < Math.min(2, queue.size()); i++) {
            // Move the first node, y, in Q to the back.
            Node y = queue.getFront();
            y.queueRef = queue.pushBack(y);
            if (y.passiveChildren.size() + y.passiveLinkableChildren.size() >= 2) {
                // If y has two passive children, link both to the root.
                for (int j = 0; j < 2; j++) {
                    Node z;
                    if (!y.passiveLinkableChildren.isEmpty())
                        z = y.passiveLinkableChildren.popFront();
                    else
                        z = y.passiveChildren.popFront();
                    z.parentRef = null;
                    link(z, root);
                }
            }
        }
        // Perform 0 or 1 loss reduction
        if (!twoNodeLossReady.isEmpty())
            twoNodeLossReduction();
        else if (!oneNodeLossReady.isEmpty())
            oneNodeLossReduction();
        // Perform O(log n) active root reduction and root degree reduction
        while (!activeRootsReady.isEmpty() || root.passiveLinkableChildren.size() >= 3) {
            if (!activeRootsReady.isEmpty())
                activeRootReduction();
            if (root.passiveLinkableChildren.size() >= 3)
                rootDegreeReduction();
        }
    }

    private void twoNodeLossReduction() {
        assert !twoNodeLossReady.isEmpty();
        Rank r = twoNodeLossReady.getFront();
        Node x = r.popLoss1();
        Node y = r.popLoss1();
        x.rankRef = null;
        y.rankRef = null;
        assert x.isActive();
        assert y.isActive();
        assert !x.isActiveRoot();
        assert !y.isActiveRoot();
        assert x.rank == y.rank;
        assert x.loss == 1;
        assert y.loss == 1;
        // Swap if necessary
        if (ord.compare(x.box.key, y.box.key) > 0) {
            Node z = x;
            x = y;
            y = z;
        }
        // w.l.o.g. x.key <= y.key
        assert ord.compare(x.box.key, y.box.key) <= 0;
        Node z = y.parent;
        // Link y to x
        link(y, x);
        x.setRank(x.rank + 1);
        // Make both x and y have loss 0
        x.setLoss(0);
        y.setLoss(0);
        // For y's original parent z, its rank is decreased by 1
        assert z.rank > 0;
        z.setRank(z.rank - 1);
        if (!z.isActiveRoot())
            // its loss is increased by 1 (if it is not an active root)
            z.setLoss(z.loss + 1);
    }

    private void oneNodeLossReduction() {
        assert !oneNodeLossReady.isEmpty();
        Rank r = oneNodeLossReady.getFront();
        Node x = r.popLoss2();
        // Performed on an active node x
        assert x.isActive();
        // with loss 2
        assert x.loss == 2;
        // x's original parent y must be active
        Node y = x.parent;
        assert y.isActive();
        // Given x had loss 2; x was not an active root
        assert !x.isActiveRoot();
        // Link x to the root
        link(x, root);
        // x is now an active root
        assert x.isActiveRoot();
        // thus it has loss 0 now
        x.setLoss(0);
        assert x.loss == 0;
        // we decrease y's rank by 1
        assert y.rank > 0;
        y.setRank(y.rank - 1);
        if (!y.isActiveRoot())
            // increase y's loss by 1 if y is not an active root
            y.setLoss(y.loss + 1);
    }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void clear() {
        root = null;
    }

    private static class ActiveRecord {

        public static final ActiveRecord passive = new ActiveRecord(false);
        boolean isActive;

        ActiveRecord(boolean a) { isActive = a; }

    }


    private class Rank {

        final DoublyLinkedList<Node> activeRoots = new DoublyLinkedList<>();
        final DoublyLinkedList<Node> loss1 = new DoublyLinkedList<>();
        final DoublyLinkedList<Node> loss2 = new DoublyLinkedList<>();
        Deletable activeRootsReadyNode;
        Deletable twoNodeLossReadyNode;
        Deletable oneNodeLossReadyNode;

        void removeNode(Node x) {
            if (x.rankRef == null)
                return;
            x.rankRef.delete();
            x.rankRef = null;
            refreshActiveRootsReady();
            refreshTwoNodeLossReady();
            refreshOneNodeLossReady();
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
            if (loss1.size() < 2) {
                if (twoNodeLossReadyNode != null) {
                    twoNodeLossReadyNode.delete();
                    twoNodeLossReadyNode = null;
                }
            } else {

                if (twoNodeLossReadyNode == null)
                    twoNodeLossReadyNode = twoNodeLossReady.pushFront(this);
            }
        }

        void refreshOneNodeLossReady() {
            if (loss2.isEmpty()) {
                if (oneNodeLossReadyNode != null) {
                    oneNodeLossReadyNode.delete();
                    oneNodeLossReadyNode = null;
                }
            } else {
                if (oneNodeLossReadyNode == null)
                    oneNodeLossReadyNode = oneNodeLossReady.pushFront(this);
            }
        }

        void addActiveRoot(Node x) {
            assert x.isActiveRoot();
            x.rankRef = activeRoots.pushFront(x);
            refreshActiveRootsReady();
        }

        Node popActiveRoot() {
            Node x = activeRoots.popFront();
            x.rankRef = null;
            assert x.isActiveRoot();
            refreshActiveRootsReady();
            return x;
        }

        void addLoss1(Node x) {
            assert x.loss == 1;
            x.rankRef = loss1.pushFront(x);
            refreshTwoNodeLossReady();
        }

        Node popLoss1() {
            Node x = loss1.popFront();
            x.rankRef = null;
            assert x.loss == 1;
            refreshTwoNodeLossReady();
            return x;
        }

        void addLoss2(Node x) {
            assert x.loss == 2;
            x.rankRef = loss2.pushFront(x);
            refreshOneNodeLossReady();
        }

        Node popLoss2() {
            Node x = loss2.popFront();
            x.rankRef = null;
            assert x.loss == 2;
            refreshOneNodeLossReady();
            return x;
        }

    }


    private class Box extends Decreasable {

        private Node ref;

        private Box(K k, Node r) {
            super(k);
            ref = r;
        }

        @Override
        public void decreaseKey(K k) {
            assert ord.compare(k, key) <= 0;
            key = k;
            Node x = ref;
            // If x is the root - DONE
            if (x == root)
                return;
            // If v is smaller than the root value, swap the two values
            if (ord.compare(key, root.box.key) < 0) {
                ref.box = root.box;
                ref.box.ref = ref;
                root.box = this;
                ref = root;
            }
            Node y = x.parent;
            link(x, root);
            if (y.isActive()) {
                if (x.isActive())
                    y.setRank(y.rank - 1);
                if (!y.isActiveRoot())
                    y.setLoss(y.loss + 1);
            }

            // perform <= 1 loss reduction
            if (!twoNodeLossReady.isEmpty())
                twoNodeLossReduction();
            else if (!oneNodeLossReady.isEmpty())
                oneNodeLossReduction();
            // perform <= 6 active root reduction and <= 4 root degree reduction
            for (int i = 0; i < 6; i++) {
                if (!activeRootsReady.isEmpty())
                    activeRootReduction();
                if (root.passiveLinkableChildren.size() >= 3)
                    rootDegreeReduction();
            }
        }

    }


    private class Node {

        private final DoublyLinkedList<Node> activeChildren = new DoublyLinkedList<>();
        private final DoublyLinkedList<Node> passiveChildren = new DoublyLinkedList<>();
        private final DoublyLinkedList<Node> passiveLinkableChildren = new DoublyLinkedList<>();
        private Box box;
        private Node parent = null;
        private Deletable queueRef;
        private Deletable parentRef; // to allow deletion from its parent
        private ActiveRecord flag = ActiveRecord.passive;
        private int rank = 0;
        private Deletable rankRef;
        private int loss = 0;

        private Node(K k) { box = new Box(k, this); }

        private void setLoss(int l) {
            assert l != loss;
            assert isActive();
            assert !isActiveRoot();
            loss = l;
            Rank record = rankList.get(rank);
            if (isActive()) {
                if (loss == 1)
                    record.addLoss1(this);
                else if (loss == 2)
                    record.addLoss2(this);
            }
        }

        private boolean isActive() { return flag.isActive; }

        private boolean isActiveRoot() { return isActive() && parent.isPassive(); }

        private boolean isPassive() { return !flag.isActive; }

        private void setRank(int r) {
            assert r == activeChildren.size();
            assert r != rank;
            rankList.get(rank).removeNode(this);
            rank = r;
            if (rank == rankList.size())
                rankList.add(new Rank());
            Rank record = rankList.get(rank);
            if (isActiveRoot())
                record.addActiveRoot(this);
            else if (loss == 1)
                record.addLoss1(this);
            else if (loss == 2)
                record.addLoss2(this);
        }

        private boolean isLinkable() {
            return isPassive() && activeChildren.isEmpty() && passiveChildren.isEmpty();
        }

    }

}
