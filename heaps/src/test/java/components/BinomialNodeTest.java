package components;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinomialNodeTest {

    private BinomialNode node;

    public BinomialNodeTest() {
    }

    @Before
    public void setUp() {
        this.node = new BinomialNode(10);
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
        BinomialNode parentNode = new BinomialNode(100);
        this.node.setParent(parentNode);
        assertEquals(100, this.node.getParent().getKey());
    }

    @Test
    public void setParentSetsParentCorrectlyWhenParentAlreadyExists() {
        BinomialNode firstParentNode = new BinomialNode(100);
        this.node.setParent(firstParentNode);
        assertEquals(100, this.node.getParent().getKey());
        BinomialNode secondParentNode = new BinomialNode(250);
        this.node.setParent(secondParentNode);
        assertEquals(250, this.node.getParent().getKey());
    }

    @Test
    public void setLeftMostChildSetsChildCorrectlyWhenChildPreviouslyNull() {
        BinomialNode childNode = new BinomialNode(100);
        this.node.setLeftmostChild(childNode);
        assertEquals(100, this.node.getLeftmostChild().getKey());
    }

    @Test
    public void setLeftmostChildSetsChildCorrectlyWhenChildAlreadyExists() {
        BinomialNode firstChildNode = new BinomialNode(100);
        this.node.setLeftmostChild(firstChildNode);
        assertEquals(100, this.node.getLeftmostChild().getKey());
        BinomialNode secondChildNode = new BinomialNode(250);
        this.node.setLeftmostChild(secondChildNode);
        assertEquals(250, this.node.getLeftmostChild().getKey());
    }

    @Test
    public void setSiblingToTheRightSetsSiblingCorrectlyWhenSiblingPreviouslyNull() {
        BinomialNode siblingNode = new BinomialNode(100);
        this.node.setSiblingToTheRight(siblingNode);
        assertEquals(100, this.node.getSiblingToTheRight().getKey());
    }

    @Test
    public void setSiblingToTheRightSetsSiblingCorrectlyWhenSiblingAlreadyExists() {
        BinomialNode firstSiblingNode = new BinomialNode(100);
        this.node.setSiblingToTheRight(firstSiblingNode);
        assertEquals(100, this.node.getSiblingToTheRight().getKey());
        BinomialNode secondSiblingNode = new BinomialNode(250);
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
