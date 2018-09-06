package components;

/**
 * Provides the node needed for the proper functioning of the pairing
 * heap.
 *
 * @author Mika Äärilä
 */
public class PairingNode {

    /**
     * The properties of pairing node include links to its leftmost child node,
     * and to its left and right sibling nodes. The leftmost child node uses its
     * left pointer to sibling to link to its parent. In addition, pairing node
     * holds the information regarding the node's key value.
     */
    private int key;
    private PairingNode leftmostChild;
    private PairingNode leftSibling;
    private PairingNode rightSibling;

    /**
     * Constructor for the node.
     *
     * @param key the value of node's key property.
     */
    public PairingNode(int key) {
        this.key = key;
        this.leftmostChild = null;
        this.leftSibling = null;
        this.rightSibling = null;
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
     * Returns the leftmost child node of the current node.
     *
     * @return the leftmost child node.
     */
    public PairingNode getLeftmostChild() {
        return leftmostChild;
    }

    /**
     * Sets the leftmost child node of the current node.
     *
     * @param leftmostChild the new leftmost child node.
     */
    public void setLeftmostChild(PairingNode leftmostChild) {
        this.leftmostChild = leftmostChild;
    }

    /**
     * Returns the left sibling node for the current node.
     *
     * @return the left sibling node.
     */
    public PairingNode getLeftSibling() {
        return leftSibling;
    }

    /**
     * Sets the left sibling node for the current node.
     *
     * @param leftSibling the new sibling node.
     */
    public void setLeftSibling(PairingNode leftSibling) {
        this.leftSibling = leftSibling;
    }

    /**
     * Returns the sibling node of the current node.
     *
     * @return the sibling node.
     */
    public PairingNode getRightSibling() {
        return this.rightSibling;
    }

    /**
     * Sets the sibling node of the current node.
     *
     * @param rightSibling the new sibling node.
     */
    public void setRightSibling(PairingNode rightSibling) {
        this.rightSibling = rightSibling;
    }
}
