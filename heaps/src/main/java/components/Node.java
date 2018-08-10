
package components;

public class Node {
    private int key;
    private Node parent;
    private Node leftmostChild;
    private Node siblingToTheRight;
    private int degree;
    
    public Node (int key) {
        this.key = key;
        this.parent = null;
        this.leftmostChild = null;
        this.siblingToTheRight = null;
        this.degree = 0;        
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftmostChild() {
        return leftmostChild;
    }

    public void setLeftmostChild(Node leftmostChild) {
        this.leftmostChild = leftmostChild;
    }

    public Node getSiblingToTheRight() {
        return siblingToTheRight;
    }

    public void setSiblingToTheRight(Node siblingToTheRight) {
        this.siblingToTheRight = siblingToTheRight;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }   
}
