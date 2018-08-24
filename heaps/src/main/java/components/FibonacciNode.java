package components;

public class FibonacciNode {

    int key;
    int degree;
    Boolean mark;
    FibonacciNode parent;
    FibonacciNode child;
    FibonacciNode leftSibling;
    FibonacciNode rightSibling;

    public FibonacciNode(int key) {
        this.key = key;
        this.degree = 0;
        this.mark = false;
        this.parent = null;
        this.child = null;
        this.leftSibling = null;
        this.rightSibling = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }

    public FibonacciNode getParent() {
        return parent;
    }

    public void setParent(FibonacciNode parent) {
        this.parent = parent;
    }

    public FibonacciNode getChild() {
        return child;
    }

    public void setChild(FibonacciNode child) {
        this.child = child;
    }

    public FibonacciNode getLeftSibling() {
        return leftSibling;
    }

    public void setLeftSibling(FibonacciNode leftSibling) {
        this.leftSibling = leftSibling;
    }

    public FibonacciNode getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(FibonacciNode rightSibling) {
        this.rightSibling = rightSibling;
    }
}
