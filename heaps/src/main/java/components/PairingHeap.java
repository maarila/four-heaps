package components;

public class PairingHeap {

    private PairingNode root;

    public PairingHeap() {
        this.root = null;
    }

    public PairingHeap makePairingHeap() {
        return new PairingHeap();
    }

    public PairingNode getRoot() {
        return this.root;
    }

    public int returnMax() {
        return this.root == null ? Integer.MIN_VALUE : this.root.getKey();
    }

    public int deleteMax() {
        if (this.root == null) {
            return Integer.MIN_VALUE;
        }

        int maxValue = this.root.getKey();

        if (this.root.getLeftPointerToChild() == null) {
            this.root = null;
        } else {
            this.root.getLeftPointerToChild().setParent(null);
            this.root = linkSiblings(this.root.getLeftPointerToChild());
        }

        return maxValue;
    }

    public void insert(int newValue) {
        PairingNode newNode = new PairingNode(newValue);
        PairingNode meldedNode = this.meld(this.root, newNode);
        this.root = meldedNode;
    }

    public PairingNode meld(PairingNode firstRoot, PairingNode secondRoot) {
        if (firstRoot == null) {
            return secondRoot;
        }

        if (secondRoot == null) {
            return firstRoot;
        }

        PairingNode meldedRoot;

        if (firstRoot.getKey() >= secondRoot.getKey()) {
            secondRoot.setParent(firstRoot);
            secondRoot.setRightPointerToSibling(firstRoot.getLeftPointerToChild());
            firstRoot.setLeftPointerToChild(secondRoot);
            meldedRoot = firstRoot;
        } else {
            firstRoot.setParent(secondRoot);
            firstRoot.setRightPointerToSibling(secondRoot.getLeftPointerToChild());
            secondRoot.setLeftPointerToChild(firstRoot);
            meldedRoot = secondRoot;
        }

        return meldedRoot;
    }

    public PairingNode linkSiblings(PairingNode startingNode) {
        if (startingNode.getRightPointerToSibling() == null) {
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

            iteratingNode = currentNode.getRightPointerToSibling();
            currentNode.setRightPointerToSibling(null);
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

    public PairingNode[] doubleArraySize(PairingNode[] siblingArray) {
        PairingNode[] doubledArray = new PairingNode[siblingArray.length * 2];

        for (int i = 0; i < siblingArray.length; i++) {
            doubledArray[i] = siblingArray[i];
        }

        return doubledArray;
    }
}
