package components;

public class BinomialHeap {

    private Node head;

    public BinomialHeap() {
        this.head = null;
    }

    public BinomialHeap makeBinomialHeap() {
        return new BinomialHeap();
    }
    
    public Node getHead() {
        return head;
    }

    public int returnMax() {
        Node iteratingHead = this.head;
        int maxValue = Integer.MIN_VALUE;

        while (iteratingHead != null) {
            if (iteratingHead.getKey() > maxValue) {
                maxValue = iteratingHead.getKey();
            }
            
            iteratingHead = iteratingHead.getSiblingToTheRight();
        }

        return maxValue;
    }

    public int deleteMax() {
        if (this.head == null) {
            return Integer.MIN_VALUE;
        }

        Node maxHead = this.head;
        Node iteratingHead = maxHead.getSiblingToTheRight();
        Node previousToMax = null;
        Node previousToIterator = maxHead;

        while (iteratingHead != null) {
            if (iteratingHead.getKey() > maxHead.getKey()) {
                maxHead = iteratingHead;
                previousToMax = previousToIterator;
            }
            previousToIterator = iteratingHead;
            iteratingHead = iteratingHead.getSiblingToTheRight();
        }

        int maxValue = maxHead.getKey();

        if (maxHead == this.head) {
            this.head = maxHead.getSiblingToTheRight();
        } else {
            previousToMax.setSiblingToTheRight(maxHead.getSiblingToTheRight());
        }

        BinomialHeap newHeap = makeBinomialHeap();
        maxHead = maxHead.getLeftmostChild();

        while (maxHead != null) {
            Node sibling = maxHead.getSiblingToTheRight();
            maxHead.setSiblingToTheRight(newHeap.head);
            newHeap.head = maxHead;
            maxHead = sibling;
        }

        BinomialHeap unitedHeap = this.union(newHeap);
        this.head = unitedHeap.head;

        return maxValue;
    }

    public void increaseKey(Node nodeToIncrease, int newKey) {
        if (newKey > nodeToIncrease.getKey()) {
            nodeToIncrease.setKey(newKey);
            Node iteratingNode = nodeToIncrease;
            
            while (iteratingNode.getParent() != null
                    && iteratingNode.getParent().getKey() > iteratingNode.getKey()) {
                int helperValue = iteratingNode.getKey();
                iteratingNode.setKey(iteratingNode.getParent().getKey());
                iteratingNode.getParent().setKey(helperValue);
                iteratingNode = iteratingNode.getParent();
            }
        }
    }

    public void insert(int newValue) {
        Node newNode = new Node(newValue);
        BinomialHeap newHeap = makeBinomialHeap();
        newHeap.head = newNode;
        BinomialHeap unitedHeap = this.union(newHeap);
        this.head = unitedHeap.head;
    }

    public void link(Node firstNode, Node secondNode) {
        firstNode.setParent(secondNode);
        firstNode.setSiblingToTheRight(secondNode.getLeftmostChild());
        secondNode.setLeftmostChild(firstNode);
        secondNode.setDegree(secondNode.getDegree() + 1);
    }

    public BinomialHeap union(BinomialHeap secondHeap) {
        BinomialHeap unitedHeap = makeBinomialHeap();
        unitedHeap.head = merge(this, secondHeap);

        this.head = null;
        secondHeap.head = null;

        if (unitedHeap.head == null) {
            return unitedHeap;
        }

        Node previousNode = null;
        Node currentNode = unitedHeap.head;
        Node nextNode = unitedHeap.head.getSiblingToTheRight();

        while (nextNode != null) {
            if (currentNode.getDegree() != nextNode.getDegree() || (nextNode.getSiblingToTheRight() != null
                    && nextNode.getSiblingToTheRight().getDegree() == currentNode.getDegree())) {
                previousNode = currentNode;
                currentNode = nextNode;
            } else {
                if (currentNode.getKey() > nextNode.getKey()) {
                    currentNode.setSiblingToTheRight(nextNode.getSiblingToTheRight());
                    link(nextNode, currentNode);
                } else {
                    if (previousNode == null) {
                        unitedHeap.head = nextNode;
                    } else {
                        previousNode.setSiblingToTheRight(nextNode);
                    }
                    link(currentNode, nextNode);
                    currentNode = nextNode;
                }
            }

            nextNode = currentNode.getSiblingToTheRight();
        }

        return unitedHeap;
    }

    public Node merge(BinomialHeap firstHeap, BinomialHeap secondHeap) {
        if (firstHeap.head == null) {
            return secondHeap.head;
        }

        if (secondHeap.head == null) {
            return firstHeap.head;
        }

        Node headOfFirst = firstHeap.head;
        Node headOfSecond = secondHeap.head;
        Node helperHead;

        if (headOfFirst.getDegree() > headOfSecond.getDegree()) {
            firstHeap.head = headOfSecond;
            headOfSecond = headOfFirst;
            headOfFirst = firstHeap.head;
        }

        while (headOfSecond != null) {
            if (headOfFirst.getSiblingToTheRight() == null) {
                headOfFirst.setSiblingToTheRight(headOfSecond);
                headOfSecond = headOfSecond.getSiblingToTheRight();
            } else if (headOfFirst.getSiblingToTheRight().getDegree() < headOfSecond.getDegree()) {
                headOfFirst = headOfFirst.getSiblingToTheRight();
            } else {
                helperHead = headOfSecond.getSiblingToTheRight();
                if (headOfSecond == headOfFirst.getSiblingToTheRight()) {
                    headOfSecond.setSiblingToTheRight(null);
                } else {
                    headOfSecond.setSiblingToTheRight(headOfFirst.getSiblingToTheRight());
                }
                headOfFirst.setSiblingToTheRight(headOfSecond);
                headOfSecond = helperHead;
            }
        }

        return firstHeap.head;
    }
}
