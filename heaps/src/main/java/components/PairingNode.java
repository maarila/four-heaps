package components;

/**
 * This class provides the node needed for the proper functioning of the pairing
 * heap. This node implementation is identical to that of the binomial heap with
 * the exception of having no degree property and somewhat different naming
 * convention.
 *
 * @author Mika Äärilä
 */
public class PairingNode {

    /**
     * The properties of pairing node include links to its parent node, child
     * node and sibling node. In addition, it holds the information regarding
     * the node's key value.
     */
    private int key;
    private PairingNode parent;
    private PairingNode leftPointerToChild;
    private PairingNode rightPointerToSibling;

    /**
     * Constructor for the node.
     *
     * @param key the value of node's key property.
     */
    public PairingNode(int key) {
        this.key = key;
        this.parent = null;
        this.leftPointerToChild = null;
        this.rightPointerToSibling = null;
    }

    /**
     * Returns the key value of the node.
     *
     * @return the value of the node's key property.
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the key value of the node.
     *
     * @param key the new key of type integer.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Returns the parent node of the current node.
     *
     * @return the parent node.
     */
    public PairingNode getParent() {
        return parent;
    }

    /**
     * Sets the parent node of the current node.
     *
     * @param parent the new parent node.
     */
    public void setParent(PairingNode parent) {
        this.parent = parent;
    }

    /**
     * Returns the child node for the current node.
     *
     * @return the child node.
     */
    public PairingNode getLeftPointerToChild() {
        return leftPointerToChild;
    }

    /**
     * Sets the child node for the current node.
     *
     * @param leftPointerToChild the new child node.
     */
    public void setLeftPointerToChild(PairingNode leftPointerToChild) {
        this.leftPointerToChild = leftPointerToChild;
    }

    /**
     * Returns the sibling node of the current node.
     *
     * @return the sibling node.
     */
    public PairingNode getRightPointerToSibling() {
        return this.rightPointerToSibling;
    }

    /**
     * Sets the sibling node of the current node.
     *
     * @param rightPointerToSibling the new sibling node.
     */
    public void setRightPointerToSibling(PairingNode rightPointerToSibling) {
        this.rightPointerToSibling = rightPointerToSibling;
    }
}
