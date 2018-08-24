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
     * the node among other information. The property 'numberOfNodes' tracks
     * the number of nodes belonging to the heap.
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
        } else {
            newNode.setRightSibling(this.max);
            newNode.setLeftSibling(this.max.getLeftSibling());

            if (newNode.getLeftSibling() != null) {
                newNode.getLeftSibling().setRightSibling(newNode);
            }

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

        FibonacciNode childOfMaxNode = this.max.getChild();
        FibonacciNode iteratingChild = this.max.getChild();
        FibonacciNode rightSibling;

        if (iteratingChild != null) {
            do {
                rightSibling = iteratingChild.getRightSibling();
                // remove the child from the list of children
                iteratingChild.getLeftSibling().setRightSibling(iteratingChild.getRightSibling());
                iteratingChild.getRightSibling().setLeftSibling(iteratingChild.getLeftSibling());
                // add the child to root list
                iteratingChild.setRightSibling(this.max);
                iteratingChild.setLeftSibling(this.max.getLeftSibling());
                this.max.setLeftSibling(iteratingChild);

                iteratingChild.setParent(null);
                iteratingChild = rightSibling;
            } while (iteratingChild != childOfMaxNode);
        }

        if (maximumNode.getLeftSibling() != null) {
            maximumNode.getLeftSibling().setRightSibling(maximumNode.getRightSibling());
        }

        if (maximumNode.getRightSibling() != null) {
            maximumNode.getRightSibling().setLeftSibling(maximumNode.getLeftSibling());
        }

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
