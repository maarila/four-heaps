package components;

/**
 * This class provides the basic implementation of a maximum fibonacci heap. It
 * contains the typical methods expected from a maximum heap - insertion,
 * returning the maximum value, deleting maximum value and increasing key.
 *
 * @author Mika Äärilä
 */
public class FibonacciHeap {

    /**
     * The basic element of the fibonacci heap - the max node, which provides
     * links to its siblings, parent and child, as well contains the weight of
     * the node among other information. The property 'numberOfNodes' tracks the
     * number of nodes belonging to the heap.
     */
    FibonacciNode max;
    int numberOfNodes;

    /**
     * Constructor for the fibonacci heap. At the start, the max node is set to
     * null and the number of nodes to zero.
     */
    public FibonacciHeap() {
        this.max = null;
        this.numberOfNodes = 0;
    }

    /**
     * Creates and returns a new fibonacci heap with maximum node set to null.
     *
     * @return a new fibonacci heap.
     */
    public FibonacciHeap makeFibonacciHeap() {
        return new FibonacciHeap();
    }

    /**
     * Inserts a new value into the heap. If the value is smaller than the
     * current maximum value, the new node becomes simply a sibling to the
     * current maximum node. Otherwise, it becomes a sibling and the new maximum
     * node.
     *
     * @param newValue the new value to be added.
     */
    public void insert(int newValue) {
        FibonacciNode newNode = new FibonacciNode(newValue);
        if (this.max == null) {
            this.max = newNode;
            this.max.setLeftSibling(this.max);
            this.max.setRightSibling(this.max);
        } else {
            newNode.setRightSibling(this.max);
            newNode.setLeftSibling(this.max.getLeftSibling());
            newNode.getLeftSibling().setRightSibling(newNode);
            this.max.setLeftSibling(newNode);

            if (newNode.getKey() > this.max.getKey()) {
                this.max = newNode;
            }
        }

        this.numberOfNodes++;
    }

    /**
     * Returns the maximum value of the heap. If the heap is empty, returns the
     * minimum integer value.
     *
     * @return the maximum key value of the heap.
     */
    public int returnMax() {
        return this.max == null ? Integer.MIN_VALUE : this.max.getKey();
    }

    /**
     * Deletes the node with the current maximum value and returns said value.
     *
     * Please note: implementation is not yet finished.
     *
     * @return the current maximum value as type integer.
     */
    public int deleteMax() {
        if (this.max == null) {
            return Integer.MIN_VALUE;
        }

        FibonacciNode maximumNode = this.max;
        int numberOfChildren = this.max.getDegree();

        FibonacciNode childOfMaxNode = this.max.getChild();
        FibonacciNode rightSibling;

        while (numberOfChildren > 0) {
            rightSibling = childOfMaxNode.getRightSibling();

            // remove the child from the list of children
            childOfMaxNode.getLeftSibling().setRightSibling(childOfMaxNode.getRightSibling());
            childOfMaxNode.getRightSibling().setLeftSibling(childOfMaxNode.getLeftSibling());

            // add the child to root list
            childOfMaxNode.setRightSibling(this.max);
            childOfMaxNode.setLeftSibling(this.max.getLeftSibling());
            childOfMaxNode.getLeftSibling().setRightSibling(childOfMaxNode);

            this.max.setLeftSibling(childOfMaxNode);

            childOfMaxNode.setParent(null);
            childOfMaxNode = rightSibling;
            numberOfChildren--;
        }

        maximumNode.getLeftSibling().setRightSibling(maximumNode.getRightSibling());
        maximumNode.getRightSibling().setLeftSibling(maximumNode.getLeftSibling());

        if (maximumNode == maximumNode.getRightSibling()) {
            this.max = null;
        } else {
            this.max = maximumNode.getRightSibling();
            consolidate();
        }

        this.numberOfNodes--;

        return maximumNode.getKey();
    }

    public void consolidate() {
        if (this.max == null) {
            return;
        }

        // calculate the upper bound on the degree of any node
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        int maxDegree = (int) Math.floor(Math.log(numberOfNodes) / Math.log(goldenRatio)) + 1;
        FibonacciNode[] nodesOfDegree = new FibonacciNode[maxDegree + 1];

        // count the number of nodes in the root list
        int nodesInRootList = 1;
        FibonacciNode iteratingNode = this.max.getRightSibling();

        while (iteratingNode != this.max) {
            iteratingNode = iteratingNode.getRightSibling();
            nodesInRootList++;
        }

        // place the nodes in the root list into a separate array
        FibonacciNode[] rootNodes = new FibonacciNode[nodesInRootList];

        for (int i = 0; i < nodesInRootList; i++) {
            rootNodes[i] = iteratingNode;
            iteratingNode = iteratingNode.getRightSibling();
        }

        // link nodes with same degree
        for (int i = 0; i < nodesInRootList; i++) {
            iteratingNode = rootNodes[i];
            int degree = iteratingNode.getDegree();

            while (nodesOfDegree[degree] != null) {
                FibonacciNode nodeWithSameDegree = nodesOfDegree[degree];

                if (iteratingNode.getKey() < nodeWithSameDegree.getKey()) {
                    FibonacciNode helperNode = iteratingNode;
                    iteratingNode = nodeWithSameDegree;
                    nodeWithSameDegree = helperNode;
                }

                link(nodeWithSameDegree, iteratingNode);
                nodesOfDegree[degree] = null;
                degree++;
            }

            nodesOfDegree[degree] = iteratingNode;
        }

        // empty the root list
        this.max = null;

        // reconstruct the root list
        for (int i = 0; i <= maxDegree; i++) {
            if (nodesOfDegree[i] != null) {
                if (this.max == null) {
                    this.max = nodesOfDegree[i];
                    this.max.setLeftSibling(this.max);
                    this.max.setRightSibling(this.max);
                } else {
                    nodesOfDegree[i].setRightSibling(this.max);
                    nodesOfDegree[i].setLeftSibling(this.max.getLeftSibling());

                    this.max.getLeftSibling().setRightSibling(nodesOfDegree[i]);
                    this.max.setLeftSibling(nodesOfDegree[i]);

                    if (nodesOfDegree[i].getKey() > this.max.getKey()) {
                        this.max = nodesOfDegree[i];
                    }
                }
            }
        }
    }

    public void link(FibonacciNode firstNode, FibonacciNode secondNode) {
        FibonacciNode firstNodesLeft = firstNode.getLeftSibling();
        FibonacciNode firstNodesRight = firstNode.getRightSibling();

        firstNodesLeft.setRightSibling(firstNodesRight);
        firstNodesRight.setLeftSibling(firstNodesLeft);
        firstNode.setParent(secondNode);

        if (secondNode.getChild() == null) {
            secondNode.setChild(firstNode);
            firstNode.setLeftSibling(firstNode);
            firstNode.setRightSibling(firstNode);
        } else {
            firstNode.setLeftSibling(secondNode.getChild());
            firstNode.setRightSibling(secondNode.getChild().getRightSibling());
            secondNode.getChild().setRightSibling(firstNode);
            firstNode.getRightSibling().setLeftSibling(firstNode);

        }

        secondNode.setDegree(secondNode.getDegree() + 1);
        firstNode.setMark(false);
    }

    public void increaseKey() {

    }

    /**
     * Unites two fibonacci heaps into one.
     *
     * Please note: implementation is not yet finished.
     *
     * @param firstHeap the first heap to be united.
     * @param secondHeap the second heap to be united.
     * @return the united heap.
     */
    public FibonacciHeap union(FibonacciHeap firstHeap, FibonacciHeap secondHeap) {
        FibonacciHeap unitedHeap = this.makeFibonacciHeap();
        unitedHeap.max = firstHeap.max;

//        unitedHeap.getMax Node().setLeftSibling(secondHeap.getMaxNode());
        if (firstHeap.getMaxNode() == null || (secondHeap.getMaxNode() != null
                && secondHeap.returnMax() > firstHeap.returnMax())) {
            unitedHeap.max = secondHeap.max;
        }

        unitedHeap.numberOfNodes = firstHeap.numberOfNodes + secondHeap.numberOfNodes;

        return unitedHeap;
    }

    /**
     * Returns the node with the current maximum value.
     *
     * @return the current maximum node.
     */
    public FibonacciNode getMaxNode() {
        return max;
    }

    /**
     * A helper getter for tests.
     *
     * @return the number of nodes in the heap as type integer.
     */
    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}
