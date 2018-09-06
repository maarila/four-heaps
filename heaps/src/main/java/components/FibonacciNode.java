package components;

/**
 * This class provides the node needed for the proper functioning of the
 * Fibonacci heap.
 *
 * @author Mika Äärilä
 */
public class FibonacciNode {

    /**
     * The node includes a circular doubly-linked list to both its siblings as
     * well links to its parent and child. In addition, the node tracks the
     * number of its children in the degree property, its weight i.e. its key
     * value in the key property and finally it uses the boolean value mark to
     * track its history when dealing with the increase/decrease key operation.
     */
    int key;
    int degree;
    Boolean mark;
    FibonacciNode parent;
    FibonacciNode child;
    FibonacciNode leftSibling;
    FibonacciNode rightSibling;

    /**
     * Constructor for the node.
     *
     * @param key the value of the node's key property.
     */
    public FibonacciNode(int key) {
        this.key = key;
        this.degree = 0;
        this.mark = false;
        this.parent = null;
        this.child = null;
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
     * @param key the new key as type integer.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Returns the degree value of the node.
     *
     * @return the value of the node's degree property.
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Sets the degree of the node.
     *
     * @param degree the new degree as type integer.
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     * Returns the mark value of the node.
     *
     * @return the mark value of the node.
     */
    public Boolean getMark() {
        return mark;
    }

    /**
     * Sets the mark value of the node.
     *
     * @param mark the new mark value of the node as type boolean.
     */
    public void setMark(Boolean mark) {
        this.mark = mark;
    }

    /**
     * Returns the parent node of the current node.
     *
     * @return the parent node.
     */
    public FibonacciNode getParent() {
        return parent;
    }

    /**
     * Sets the parent node of the current node.
     *
     * @param parent the new parent node.
     */
    public void setParent(FibonacciNode parent) {
        this.parent = parent;
    }

    /**
     * Returns the child node for the current node.
     *
     * @return the child node.
     */
    public FibonacciNode getChild() {
        return child;
    }

    /**
     * Sets the child node for the current node.
     *
     * @param child the new child node.
     */
    public void setChild(FibonacciNode child) {
        this.child = child;
    }

    /**
     * Returns the left sibling node of the current node.
     *
     * @return the left sibling node.
     */
    public FibonacciNode getLeftSibling() {
        return leftSibling;
    }

    /**
     * Sets the left sibling node of the current node.
     *
     * @param leftSibling the new sibling node.
     */
    public void setLeftSibling(FibonacciNode leftSibling) {
        this.leftSibling = leftSibling;
    }

    /**
     * Returns the right sibling node of the current node.
     *
     * @return the right sibling node.
     */
    public FibonacciNode getRightSibling() {
        return rightSibling;
    }

    /**
     * Sets the right sibling node of the current node.
     *
     * @param rightSibling the new sibling node.
     */
    public void setRightSibling(FibonacciNode rightSibling) {
        this.rightSibling = rightSibling;
    }
}
