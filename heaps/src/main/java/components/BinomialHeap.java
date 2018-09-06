package components;

/**
 * Provides the basic implementation of a binomial maximum heap. It
 * contains the typical methods expected from a maximum heap - insertion,
 * returning the maximum value, deleting maximum and increasing key.
 *
 * @author Mika Äärilä
 */
public class BinomialHeap implements Heap {

    /**
     * The basic element of a binomial heap - a head node that provides links to
     * its parent, child and sibling. In addition, head maintains its key value
     * and its degree.
     */
    private BinomialNode head;

    /**
     * Constructor for the binomial heap. At the start, the head is set to null.
     */
    public BinomialHeap() {
        this.head = null;
    }

    /**
     * Creates and returns a new binomial heap with head set to null.
     *
     * @return a new binomial heap.
     */
    public BinomialHeap makeBinomialHeap() {
        return new BinomialHeap();
    }

    /**
     * Returns the head node of the current binomial heap.
     *
     * @return the head node.
     */
    public BinomialNode getHead() {
        return head;
    }

    /**
     * Returns the maximum value of the heap.
     *
     * @return the maximum value found after iterating through the current head
     * and its siblings i.e. the root list of the heap.
     */
    @Override
    public int returnMax() {
        BinomialNode iteratingHead = this.head;
        int maxValue = Integer.MIN_VALUE;

        while (iteratingHead != null) {
            if (iteratingHead.getKey() > maxValue) {
                maxValue = iteratingHead.getKey();
            }

            iteratingHead = iteratingHead.getSiblingToTheRight();
        }

        return maxValue;
    }

    /**
     * Finds the maximum value of the heap, deletes it and then returns it.
     * Before returning the maximum value, the method also reorganises the heap
     * should it prove necessary.
     *
     * @return the maximum value that was deleted.
     */
    @Override
    public int deleteMax() {
        if (this.head == null) {
            return Integer.MIN_VALUE;
        }

        BinomialNode maxHead = this.head;
        BinomialNode iteratingHead = maxHead.getSiblingToTheRight();
        BinomialNode previousToMax = null;
        BinomialNode previousToIterator = maxHead;

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
            BinomialNode sibling = maxHead.getSiblingToTheRight();
            maxHead.setSiblingToTheRight(newHeap.head);
            newHeap.head = maxHead;
            maxHead = sibling;
        }

        BinomialHeap unitedHeap = this.union(newHeap);
        this.head = unitedHeap.head;

        return maxValue;
    }

    /**
     * Inserts a new value into the heap. Insertion requires the heap to be
     * rebuilt with the union method.
     *
     * @param newValue the value to be inserted into the heap.
     */
    @Override
    public void insert(int newValue) {
        BinomialNode newNode = new BinomialNode(newValue);
        BinomialHeap newHeap = makeBinomialHeap();
        newHeap.head = newNode;
        BinomialHeap unitedHeap = this.union(newHeap);
        this.head = unitedHeap.head;
    }

    /**
     * Increases the value of the key in the given node.
     *
     * @param nodeToIncrease the node containing the key to be increased.
     * @param newKey the new value of the key.
     */
    public void increaseKey(BinomialNode nodeToIncrease, int newKey) {
        if (newKey > nodeToIncrease.getKey()) {
            nodeToIncrease.setKey(newKey);
            BinomialNode iteratingNode = nodeToIncrease;

            while (iteratingNode.getParent() != null
                    && iteratingNode.getParent().getKey() < iteratingNode.getKey()) {
                int helperValue = iteratingNode.getKey();
                iteratingNode.setKey(iteratingNode.getParent().getKey());
                iteratingNode.getParent().setKey(helperValue);
                iteratingNode = iteratingNode.getParent();
            }
        }
    }

    /**
     * Unites two binomial heaps into one while making sure that the resulting
     * heap abides by the binomial heap properties.
     *
     * @param secondHeap the heap to be united with the current heap.
     * @return the heap that resulted from uniting the current heap with the
     * given heap.
     */
    public BinomialHeap union(BinomialHeap secondHeap) {
        BinomialHeap unitedHeap = makeBinomialHeap();
        unitedHeap.head = merge(this, secondHeap);

        this.head = null;
        secondHeap.head = null;

        if (unitedHeap.head == null) {
            return unitedHeap;
        }

        BinomialNode previousNode = null;
        BinomialNode currentNode = unitedHeap.head;
        BinomialNode nextNode = unitedHeap.head.getSiblingToTheRight();

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

    /**
     * Helper method for the union operation. Its purpose is to make the given
     * first node the new head of the second node's child nodes.
     *
     * @param firstNode the first node to be linked.
     * @param secondNode the second node to be linked.
     */
    public void link(BinomialNode firstNode, BinomialNode secondNode) {
        firstNode.setParent(secondNode);
        firstNode.setSiblingToTheRight(secondNode.getLeftmostChild());
        secondNode.setLeftmostChild(firstNode);
        secondNode.setDegree(secondNode.getDegree() + 1);
    }

    /**
     * Helper method for the union operation. Its purpose is to merge the root
     * lists of the given heaps into a single linked list which is sorted by
     * degree into increasing order.
     *
     * @param firstHeap the first heap to be merged.
     * @param secondHeap the second heap to be merged.
     * @return the merged heap.
     */
    public BinomialNode merge(BinomialHeap firstHeap, BinomialHeap secondHeap) {
        if (firstHeap.head == null) {
            return secondHeap.head;
        }

        if (secondHeap.head == null) {
            return firstHeap.head;
        }

        BinomialNode headOfFirst = firstHeap.head;
        BinomialNode headOfSecond = secondHeap.head;
        BinomialNode helperHead;

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
