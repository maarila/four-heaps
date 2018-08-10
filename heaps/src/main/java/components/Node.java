package components;

/**
 * This class provides the node needed for the proper functioning of the
 * binomial heap.
 *
 * @author Mika Äärilä
 */
public class Node {

    /**
     * The properties of node include links to its parent node, child node and
     * sibling node. In addition, it holds the information regarding its key
     * value and its degree. The maximum degree of any node in an n-node tree is
     * log n.
     */
    private int key;
    private Node parent;
    private Node leftmostChild;
    private Node siblingToTheRight;
    private int degree;

    /**
     * Constructor for the node.
     *
     * @param key the value of node's key property.
     */
    public Node(int key) {
        this.key = key;
        this.parent = null;
        this.leftmostChild = null;
        this.siblingToTheRight = null;
        this.degree = 0;
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
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent node of the current node.
     *
     * @param parent the new parent node.
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Returns the child node for the current node.
     *
     * @return the child node.
     */
    public Node getLeftmostChild() {
        return leftmostChild;
    }

    /**
     * Sets the child node for the current node.
     *
     * @param leftmostChild the new child node.
     */
    public void setLeftmostChild(Node leftmostChild) {
        this.leftmostChild = leftmostChild;
    }

    /**
     * Returns the sibling node of the current node.
     *
     * @return the sibling node.
     */
    public Node getSiblingToTheRight() {
        return siblingToTheRight;
    }

    /**
     * Sets the sibling node of the current node.
     *
     * @param siblingToTheRight the new sibling node.
     */
    public void setSiblingToTheRight(Node siblingToTheRight) {
        this.siblingToTheRight = siblingToTheRight;
    }

    /**
     * Returns the degree of the current node.
     *
     * @return the current degree of type integer.
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Sets the degree of the current node.
     *
     * @param degree the new degree of type integer.
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }
}
