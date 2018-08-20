package components;

/**
 * This class provides the basic implementation of a maximum pairing heap. It
 * contains the typical methods expected from a maximum heap - insertion,
 * returning the maximum value, deleting maximum and increasing key.
 *
 * @author Mika Äärilä
 */
public class PairingHeap {

    /**
     * The basic element of a pairing heap - the root node that provides links
     * to its parent, child and sibling. In addition, root maintains its key
     * value.
     */
    private PairingNode root;

    /**
     * Constructor for the pairing heap. At the start, the root is set to null.
     */
    public PairingHeap() {
        this.root = null;
    }

    /**
     * Creates and returns a new pairing heap.
     *
     * @return a new pairing heap.
     */
    public PairingHeap makePairingHeap() {
        return new PairingHeap();
    }

    /**
     * Returns the root node of the heap.
     *
     * @return the root node.
     */
    public PairingNode getRoot() {
        return this.root;
    }

    /**
     * Returns the maximum value of the heap.
     *
     * @return the maximum value of the heap.
     */
    public int returnMax() {
        return this.root == null ? Integer.MIN_VALUE : this.root.getKey();
    }

    /**
     * Deletes the maximum value of the heap and returns it. After deletion, all
     * the remaining nodes need to built into a new heap via the linkSiblings
     * operation.
     *
     * @return the maximum value of the heap.
     */
    public int deleteMax() {
        if (this.root == null) {
            return Integer.MIN_VALUE;
        }

        int maxValue = this.root.getKey();

        if (this.root.getLeftmostChild() == null) {
            this.root = null;
        } else {
            this.root = linkSiblings(this.root.getLeftmostChild());
        }

        return maxValue;
    }

    /**
     * Inserts a new value into the heap. Insertion requires the new node to be
     * melded into the heap i.e. if the new node is not the new root node, it
     * will become one of the subroots of the root node.
     *
     * @param newValue the value to be inserted into the heap.
     */
    public void insert(int newValue) {
        PairingNode newNode = new PairingNode(newValue);
        PairingNode meldedNode = this.meld(this.root, newNode);
        this.root = meldedNode;
    }

    /**
     * Melds two roots into one. The root that has the larger key will become
     * the new root. The other will become a subroot to said root.
     *
     * @param firstRoot first root to be melded.
     * @param secondRoot second root to melded.
     * @return the melded root.
     */
    public PairingNode meld(PairingNode firstRoot, PairingNode secondRoot) {
        if (firstRoot == null) {
            return secondRoot;
        }

        if (secondRoot == null) {
            return firstRoot;
        }

        PairingNode meldedRoot;

        if (firstRoot.getKey() >= secondRoot.getKey()) {
            firstRoot.setRightSibling(secondRoot.getRightSibling());

            if (firstRoot.getRightSibling() != null) {
                firstRoot.getRightSibling().setLeftSibling(firstRoot);

            }

            secondRoot.setLeftSibling(firstRoot);
            secondRoot.setRightSibling(firstRoot.getLeftmostChild());

            if (secondRoot.getRightSibling() != null) {
                secondRoot.getRightSibling().setLeftSibling(secondRoot);
            }

            firstRoot.setLeftmostChild(secondRoot);
            meldedRoot = firstRoot;
        } else {
            secondRoot.setRightSibling(firstRoot.getRightSibling());

            if (secondRoot.getRightSibling() != null) {
                secondRoot.getRightSibling().setLeftSibling(secondRoot);
            }

            firstRoot.setLeftSibling(secondRoot);
            firstRoot.setRightSibling(secondRoot.getLeftmostChild());

            if (firstRoot.getRightSibling() != null) {
                firstRoot.getRightSibling().setLeftSibling(firstRoot);
            }

            secondRoot.setLeftmostChild(firstRoot);
            meldedRoot = secondRoot;
        }

        return meldedRoot;
    }

    /**
     * Links separate roots together to form a new pairing heap. The two-pass
     * procedure starts by melding two consecutive separate roots into pairs
     * after which during the second pass the pairs are melded into the last
     * pair (or individual root) formed on the first pass.
     *
     * @param startingNode the node from which the linking starts.
     * @return the new node satisfies the heap condition.
     */
    public PairingNode linkSiblings(PairingNode startingNode) {
        if (startingNode.getRightSibling() == null) {
            return startingNode;
        }

        int defaultNumberOfSiblings = 16;
        int actualNumberOfSiblings = 0;

        PairingNode[] allSiblings = new PairingNode[defaultNumberOfSiblings];
        PairingNode iteratingNode = startingNode;

        while (iteratingNode != null) {
            PairingNode currentNode = iteratingNode;
            allSiblings[actualNumberOfSiblings] = currentNode;
            actualNumberOfSiblings++;

            if (actualNumberOfSiblings >= allSiblings.length) {
                allSiblings = doubleArraySize(allSiblings);
            }

            iteratingNode = currentNode.getRightSibling();
            currentNode.setRightSibling(null);
        }

        int indexOfLastSibling = 0;

        for (int i = 0; i + 1 < actualNumberOfSiblings; i += 2) {
            allSiblings[i] = meld(allSiblings[i], allSiblings[i + 1]);
            indexOfLastSibling = i;
        }

        if (indexOfLastSibling == actualNumberOfSiblings - 3) {
            allSiblings[indexOfLastSibling] = meld(allSiblings[indexOfLastSibling], allSiblings[indexOfLastSibling + 2]);
        }

        for (int i = indexOfLastSibling; i >= 2; i -= 2) {
            allSiblings[i - 2] = meld(allSiblings[i - 2], allSiblings[i]);
        }

        return allSiblings[0];
    }

    /**
     * Increase the key value of the given node.
     *
     * @param nodeToIncrease the node of which key is to be increased.
     * @param newKey the new value of the key.
     */
    public void increaseKey(PairingNode nodeToIncrease, int newKey) {
        if (newKey > nodeToIncrease.getKey()) {
            if (nodeToIncrease.getKey() == this.returnMax()) {
                nodeToIncrease.setKey(newKey);
                return;
            }

            nodeToIncrease.setKey(newKey);
            nodeToIncrease.getLeftSibling().setRightSibling(nodeToIncrease.getRightSibling());

            if (nodeToIncrease.getRightSibling() != null) {
                nodeToIncrease.getRightSibling().setLeftSibling(nodeToIncrease.getLeftSibling());
            }

            nodeToIncrease.setLeftSibling(null);
            nodeToIncrease.setRightSibling(null);

            this.root = meld(this.root, nodeToIncrease);
        }
    }

    /**
     * Doubles the size of the given array.
     *
     * @param siblingArray the array of which size needs to be doubled.
     * @return the double-sized array.
     */
    public PairingNode[] doubleArraySize(PairingNode[] siblingArray) {
        PairingNode[] doubledArray = new PairingNode[siblingArray.length * 2];

        for (int i = 0; i < siblingArray.length; i++) {
            doubledArray[i] = siblingArray[i];
        }

        return doubledArray;
    }
}
