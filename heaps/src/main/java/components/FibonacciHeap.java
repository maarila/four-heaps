package components;

/**
 * Provides the basic implementation of a maximum Fibonacci heap. It
 * contains the typical methods expected from a maximum heap - insertion,
 * returning the maximum value, deleting maximum value and increasing key.
 *
 * @author Mika Äärilä
 */
public class FibonacciHeap implements Heap {

    /**
     * The basic element of the Fibonacci heap - the max node, which provides
     * links to its siblings, parent and child, as well contains the weight of
     * the node among other information. The property 'numberOfNodes' tracks the
     * number of nodes belonging to the heap.
     */
    FibonacciNode max;
    int numberOfNodes;

    /**
     * Constructor for the Fibonacci heap. At the start, the max node is set to
     * null and the number of nodes is set to zero.
     */
    public FibonacciHeap() {
        this.max = null;
        this.numberOfNodes = 0;
    }

    /**
     * Creates and returns a new Fibonacci heap with maximum node set to null.
     *
     * @return a new Fibonacci heap.
     */
    public FibonacciHeap makeFibonacciHeap() {
        return new FibonacciHeap();
    }

    /**
     * Inserts a new node into the heap. If the nodes's value is smaller than
     * the current maximum value, the new node becomes simply a sibling to the
     * current maximum node. Otherwise, it becomes a sibling and the new maximum
     * node.
     *
     * @param newValue the new value to be added.
     */
    @Override
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
    @Override
    public int returnMax() {
        return this.max == null ? Integer.MIN_VALUE : this.max.getKey();
    }

    /**
     * Deletes the node with the current maximum value and returns said value.
     * After deletion, the heap is re-built if the maximum node was not the only
     * node.
     *
     * @return the current maximum value as type integer.
     */
    @Override
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

    /**
     * A helper function for deleteMax(). Deleting a value from a heap with more
     * than one value results in the heap being 'consolidated'. This means that
     * the children of the max value being removed, i.e. the child of the max
     * value and all its siblings, are first joined separately into the root
     * list. Then the root list is iterated through and all nodes with the same
     * degree are linked until all nodes have unique degrees. Finally, the new
     * root list is built from the unique nodes, and the node with the highest
     * key value becomes the new max node.
     */
    public void consolidate() {
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

    /**
     * Links the two given nodes. First, the first given node is removed from
     * the root list and then made into a child of the second node.
     *
     * @param firstNode the first node to be linked.
     * @param secondNode the second node to be linked.
     */
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

    /**
     * Increase the key value of the given node.
     *
     * @param nodeToIncrease the node of which value is to be increased.
     * @param newKey the new key value of the node.
     */
    public void increaseKey(FibonacciNode nodeToIncrease, int newKey) {
        if (newKey > nodeToIncrease.getKey()) {
            nodeToIncrease.setKey(newKey);
            FibonacciNode parentToIncreased = nodeToIncrease.getParent();

            if (parentToIncreased != null && newKey > parentToIncreased.getKey()) {
                cut(nodeToIncrease, parentToIncreased);
                cascadingCut(parentToIncreased);
            }
            if (nodeToIncrease.getKey() > this.max.getKey()) {
                this.max = nodeToIncrease;
            }
        }
    }

    /**
     * Helper method for increaseKey operation. Cut removes the node whose key
     * was increased from the child list of its parent and places it in the root
     * list of the heap.
     *
     * @param increasedNode the node of which key was increased.
     * @param parentNode the parent of the node of which key was increased.
     */
    public void cut(FibonacciNode increasedNode, FibonacciNode parentNode) {
        // remove increased node from the child list of the parent node
        if (increasedNode.getLeftSibling() == increasedNode) {
            parentNode.setChild(null);
        } else {
            increasedNode.getLeftSibling().setRightSibling(increasedNode.getRightSibling());
            increasedNode.getRightSibling().setLeftSibling(increasedNode.getLeftSibling());
            increasedNode.getLeftSibling().setParent(parentNode);
            parentNode.setChild(increasedNode.getLeftSibling());
            parentNode.setDegree(parentNode.getDegree() - 1);
        }

        // add increased node to the root list of the heap
        increasedNode.setRightSibling(this.max);
        increasedNode.setLeftSibling(this.max.getLeftSibling());
        increasedNode.getLeftSibling().setRightSibling(increasedNode);
        this.max.setLeftSibling(increasedNode);

        increasedNode.setParent(null);
        increasedNode.setMark(false);
    }

    /**
     * Second helper method for increaseKey operation. Cascade cut tracks if a
     * node has had two children cut; in that case it proceeds to go recursively
     * through the nodes parent nodes until it finds either a root or an
     * unmarked node.
     *
     * @param parentNode the parent node of the node to be cut.
     */
    public void cascadingCut(FibonacciNode parentNode) {
        FibonacciNode parentOfParent = parentNode.getParent();
        if (parentOfParent != null) {
            if (parentNode.getMark() == false) {
                parentNode.setMark(true);
            } else {
                cut(parentNode, parentOfParent);
                cascadingCut(parentOfParent);
            }
        }
    }

    /**
     * Unites two Fibonacci heaps into one. The second one is first concatenated
     * into the root list of this heap, and then the max node is set according
     * to which of the heaps has the larger maximum key value.
     *
     * @param otherHeap the heap to be united with this heap.
     * @return the united heap.
     */
    public FibonacciHeap union(FibonacciHeap otherHeap) {
        if (this.max == null) {
            return otherHeap;
        }

        if (otherHeap.getMaxNode() == null) {
            return this;
        }

        FibonacciHeap unitedHeap = this.makeFibonacciHeap();
        unitedHeap.setMaxNode(this.max);

        FibonacciNode unitedMaxNode = unitedHeap.getMaxNode();
        FibonacciNode currentLeftSiblingForMax = unitedHeap.getMaxNode().getLeftSibling();

        unitedMaxNode.setLeftSibling(otherHeap.getMaxNode());
        unitedMaxNode.getLeftSibling().setRightSibling(unitedHeap.getMaxNode());
        unitedMaxNode.getLeftSibling().setLeftSibling(currentLeftSiblingForMax);

        if (otherHeap.returnMax() > this.returnMax()) {
            unitedHeap.setMaxNode(otherHeap.getMaxNode());
        }

        unitedHeap.setNumberOfNodes(this.getNumberOfNodes() + otherHeap.getNumberOfNodes());

        return unitedHeap;
    }

    /**
     * Deletes the given node. This is accomplished by using the two existing
     * Fibonacci heap methods.
     *
     * @param nodeToDelete the node to delete.
     */
    public void delete(FibonacciNode nodeToDelete) {
        this.increaseKey(nodeToDelete, Integer.MAX_VALUE);
        this.deleteMax();
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
     * Sets the current maximum node to the given node.
     *
     * @param max the new maximum node.
     */
    public void setMaxNode(FibonacciNode max) {
        this.max = max;
    }

    /**
     * Returns the number of nodes in the current heap.
     *
     * @return the number of nodes in the heap as type integer.
     */
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    /**
     * Sets the number of nodes for the current heap to the given number.
     *
     * @param numberOfNodes the new number of nodes.
     */
    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }
}
