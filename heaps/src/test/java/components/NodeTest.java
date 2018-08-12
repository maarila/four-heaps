package components;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    private Node node;

    public NodeTest() {
    }

    @Before
    public void setUp() {
        this.node = new Node(10);
    }

    @Test
    public void newNodeHasTheCorrectKeyValue() {
        assertEquals(10, this.node.getKey());
    }

    @Test
    public void newNodesParentIsSetToNull() {
        assertEquals(null, this.node.getParent());
    }

    @Test
    public void newNodesChildIsSetToNull() {
        assertEquals(null, this.node.getLeftmostChild());
    }

    @Test
    public void newNodesSiblingIsSetToNull() {
        assertEquals(null, this.node.getSiblingToTheRight());
    }

    @Test
    public void newNodesDegreeIsZero() {
        assertEquals(0, this.node.getDegree());
    }

    @Test
    public void setKeySetsCorrectNewValue() {
        this.node.setKey(25);
        assertEquals(25, this.node.getKey());
    }

    @Test
    public void setParentSetsParentCorrectlyWhenParentPreviouslyNull() {
        Node parentNode = new Node(100);
        this.node.setParent(parentNode);
        assertEquals(100, this.node.getParent().getKey());
    }

    @Test
    public void setParentSetsParentCorrectlyWhenParentAlreadyExists() {
        Node firstParentNode = new Node(100);
        this.node.setParent(firstParentNode);
        assertEquals(100, this.node.getParent().getKey());
        Node secondParentNode = new Node(250);
        this.node.setParent(secondParentNode);
        assertEquals(250, this.node.getParent().getKey());
    }

    @Test
    public void setLeftMostChildSetsChildCorrectlyWhenChildPreviouslyNull() {
        Node childNode = new Node(100);
        this.node.setLeftmostChild(childNode);
        assertEquals(100, this.node.getLeftmostChild().getKey());
    }

    @Test
    public void setLeftmostChildSetsChildCorrectlyWhenChildAlreadyExists() {
        Node firstChildNode = new Node(100);
        this.node.setLeftmostChild(firstChildNode);
        assertEquals(100, this.node.getLeftmostChild().getKey());
        Node secondChildNode = new Node(250);
        this.node.setLeftmostChild(secondChildNode);
        assertEquals(250, this.node.getLeftmostChild().getKey());
    }

    @Test
    public void setSiblingToTheRightSetsSiblingCorrectlyWhenSiblingPreviouslyNull() {
        Node siblingNode = new Node(100);
        this.node.setSiblingToTheRight(siblingNode);
        assertEquals(100, this.node.getSiblingToTheRight().getKey());
    }

    @Test
    public void setSiblingToTheRightSetsSiblingCorrectlyWhenSiblingAlreadyExists() {
        Node firstSiblingNode = new Node(100);
        this.node.setSiblingToTheRight(firstSiblingNode);
        assertEquals(100, this.node.getSiblingToTheRight().getKey());
        Node secondSiblingNode = new Node(250);
        this.node.setSiblingToTheRight(secondSiblingNode);
        assertEquals(250, this.node.getSiblingToTheRight().getKey());
    }

    @Test
    public void setDegreeSetsDegreeCorrectly() {
        assertEquals(0, this.node.getDegree());
        this.node.setDegree(25);
        assertEquals(25, this.node.getDegree());
    }
}
