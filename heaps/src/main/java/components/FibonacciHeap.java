package components;

public class FibonacciHeap {

    FibonacciNode max;
    int numberOfNodes;

    public FibonacciHeap() {
        this.max = null;
        this.numberOfNodes = 0;
    }

    public FibonacciHeap makeFibonacciHeap() {
        return new FibonacciHeap();
    }

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

    public int returnMax() {
        return this.max == null ? Integer.MIN_VALUE : this.max.getKey();
    }

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

    public FibonacciNode getMaxNode() {
        return max;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}
