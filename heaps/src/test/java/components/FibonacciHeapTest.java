package components;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FibonacciHeapTest {

    FibonacciHeap heap;

    public FibonacciHeapTest() {
    }

    @Before
    public void setUp() {
        this.heap = new FibonacciHeap();
    }

    @Test
    public void creatingNewFibonacciHeapReturnsAnEmptyHeap() {
        FibonacciHeap newHeap = this.heap.makeFibonacciHeap();
        assertTrue(newHeap.getMaxNode() == null);
    }

    @Test
    public void emptyHeapReturnsIntegerMinValueAsMaxValue() {
        assertEquals(Integer.MIN_VALUE, this.heap.returnMax());
    }

    @Test
    public void emptyHeapHasZeroNodes() {
        assertEquals(0, this.heap.getNumberOfNodes());
    }

    @Test
    public void numberOfNodesIncreasesWhenInsertingANode() {
        this.heap.insert(50);
        assertEquals(1, this.heap.getNumberOfNodes());
    }

    @Test
    public void numberOfNodesIsCalculatedCorrectlyWhenInsertingManyNodes() {
        for (int i = 1; i <= 50; i++) {
            this.heap.insert(i);
        }
        assertEquals(50, this.heap.getNumberOfNodes());
    }

    @Test
    public void insertingNodeIntoHeapMakesSaidNodesValueHeapsMaxValue() {
        this.heap.insert(50);
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void heapWithOneValueHasItselfForLeftSibling() {
        this.heap.insert(50);
        assertEquals(this.heap.getMaxNode(), this.heap.getMaxNode().getLeftSibling());
    }

    @Test
    public void heapWithOneValueHasItselfForRightSibling() {
        this.heap.insert(50);
        assertEquals(this.heap.getMaxNode(), this.heap.getMaxNode().getRightSibling());
    }

    @Test
    public void heapWithOneValueHasNoParent() {
        this.heap.insert(50);
        assertEquals(null, this.heap.getMaxNode().getParent());
    }

    @Test
    public void heapWithOneValueHasNoChild() {
        this.heap.insert(50);
        assertEquals(null, this.heap.getMaxNode().getChild());
    }

    @Test
    public void insertingLessThanMaxValueNodeDoesNotChangeMaxValue() {
        this.heap.insert(50);
        assertEquals(50, this.heap.returnMax());
        this.heap.insert(30);
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void insertingGreaterThanMaxValueNodeChangesMaxValue() {
        this.heap.insert(50);
        assertEquals(50, this.heap.returnMax());
        this.heap.insert(100);
        assertEquals(100, this.heap.returnMax());
    }

    @Test
    public void lessThanMaxValueInsertedBecomesLeftSiblingToFirstValue() {
        this.heap.insert(50);
        this.heap.insert(10);
        assertEquals(10, this.heap.getMaxNode().getLeftSibling().getKey());
    }

    @Test
    public void lessThanMaxValueInsertedMakesMaxValueNewNodesRightSibling() {
        this.heap.insert(50);
        this.heap.insert(10);
        assertEquals(50, this.heap.getMaxNode().getLeftSibling().getRightSibling().getKey());
    }

    @Test
    public void insertingThirdNodePlacesItBetweenMaxNodeAndSecondNode() {
        this.heap.insert(50);
        this.heap.insert(10);
        this.heap.insert(30);
        assertEquals(50, this.heap.getMaxNode().getLeftSibling().getRightSibling().getKey());
        assertEquals(10, this.heap.getMaxNode().getLeftSibling().getLeftSibling().getKey());
    }

    @Test
    public void deletingMaximumValueFromEmptyHeapReturnsIntegerMinValue() {
        int returnValue = this.heap.deleteMax();
        assertEquals(Integer.MIN_VALUE, returnValue);
    }

    @Test
    public void deletingOnlyValueReturnsSaidValue() {
        this.heap.insert(50);
        int returnValue = this.heap.deleteMax();
        assertEquals(50, returnValue);
    }

    @Test
    public void deletingOnlyValueLeavesMaximumValueNull() {
        this.heap.insert(50);
        this.heap.deleteMax();
        FibonacciNode maxNode = this.heap.getMaxNode();
        assertTrue(maxNode == null);
    }

    @Test
    public void deletingMaxValueMakesOtherValueNewMaxValue() {
        this.heap.insert(50);
        this.heap.insert(100);
        assertEquals(100, this.heap.returnMax());
        this.heap.deleteMax();
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void deletingMaxAfterInsertingMultipleValuesReplacesMaxValueCorrectly() {
        this.heap.insert(30);
        this.heap.insert(50);
        this.heap.insert(20);
        this.heap.insert(25);
        this.heap.insert(40);
        this.heap.insert(35);
        assertEquals(50, this.heap.returnMax());
        this.heap.deleteMax();
        assertEquals(40, this.heap.returnMax());
        this.heap.deleteMax();
        assertEquals(35, this.heap.returnMax());
    }

    @Test
    public void unitingTwoHeapsWithSingleNodeSelectsMaxValueCorrectly() {
        this.heap.insert(50);
        FibonacciHeap otherHeap = new FibonacciHeap();
        otherHeap.insert(30);
        FibonacciHeap unitedHeap = this.heap.union(otherHeap);
        assertEquals(50, unitedHeap.returnMax());
    }

    @Test
    public void unitingTwoHeapsWithSingleNodeCreatesCorrectRelationship() {
        this.heap.insert(50);
        FibonacciHeap otherHeap = new FibonacciHeap();
        otherHeap.insert(30);
        FibonacciHeap unitedHeap = this.heap.union(otherHeap);
        assertEquals(30, unitedHeap.getMaxNode().getLeftSibling().getKey());
    }

    @Test
    public void unitingTwoHeapsWithMultipleValuesSelectsMaxValueCorrectly() {
        this.heap.insert(40);
        this.heap.insert(50);
        this.heap.insert(60);
        this.heap.insert(20);
        FibonacciHeap otherHeap = new FibonacciHeap();
        otherHeap.insert(5);
        otherHeap.insert(10);
        otherHeap.insert(200);
        otherHeap.insert(1);
        FibonacciHeap unitedHeap = this.heap.union(otherHeap);
        assertEquals(200, unitedHeap.returnMax());
    }

    @Test
    public void unitingEmptyHeapWithNonEmptyHeapReturnsNonEmptyHeap() {
        FibonacciHeap otherHeap = new FibonacciHeap();
        otherHeap.insert(40);
        otherHeap.insert(50);
        FibonacciHeap unitedHeap = this.heap.union(otherHeap);
        assertTrue(unitedHeap == otherHeap);
    }

    @Test
    public void unitingNonEmptyHeapWithEmptyHeapReturnsNonEmptyHeap() {
        this.heap.insert(40);
        this.heap.insert(20);
        FibonacciHeap otherHeap = new FibonacciHeap();
        FibonacciHeap unitedHeap = this.heap.union(otherHeap);
        assertTrue(this.heap == unitedHeap);
    }

    @Test
    public void increasingKeyWithSmallerThanExistingValueDoesNothing() {
        this.heap.insert(50);
        this.heap.increaseKey(this.heap.getMaxNode(), 30);
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void increasingMaxValueReturnsIncreasedMaxValue() {
        this.heap.insert(50);
        this.heap.increaseKey(this.heap.getMaxNode(), 100);
        assertEquals(100, this.heap.returnMax());
    }

    @Test
    public void increasingNonMaxValueToMaxValueMakesSaidValueNewMaxValue() {
        this.heap.insert(50);
        this.heap.insert(30);
        assertEquals(30, this.heap.getMaxNode().getLeftSibling().getKey());
        this.heap.increaseKey(this.heap.getMaxNode().getLeftSibling(), 100);
        assertEquals(100, this.heap.returnMax());
    }

    @Test
    public void increasingChildValueMaintainsRelationshipIfValueLessThanParentValue() {
        this.heap.insert(50);
        FibonacciNode childNode = new FibonacciNode(30);
        this.heap.getMaxNode().setChild(childNode);
        childNode.setParent(this.heap.getMaxNode());
        this.heap.increaseKey(this.heap.getMaxNode().getChild(), 40);
        assertEquals(40, this.heap.getMaxNode().getChild().getKey());
    }

    @Test
    public void increasingChildValueAboveParentValueMakesRemoveSaidChildFromChildList() {
        this.heap.insert(50);

        FibonacciNode childNode = new FibonacciNode(40);
        childNode.setLeftSibling(childNode);
        childNode.setRightSibling(childNode);
        childNode.setParent(this.heap.getMaxNode());

        FibonacciNode childToChild = new FibonacciNode(35);
        childToChild.setLeftSibling(childToChild);
        childToChild.setRightSibling(childToChild);
        childToChild.setParent(childNode);

        this.heap.getMaxNode().setChild(childNode);
        childNode.setChild(childToChild);

        this.heap.increaseKey(childToChild, 45);
        assertEquals(null, this.heap.getMaxNode().getChild().getChild());
    }

    @Test
    public void increasingChildValueAboveParentValuePlacesSaidChildToRootList() {
        this.heap.insert(50);

        FibonacciNode childNode = new FibonacciNode(40);
        childNode.setLeftSibling(childNode);
        childNode.setRightSibling(childNode);
        childNode.setParent(this.heap.getMaxNode());

        FibonacciNode childToChild = new FibonacciNode(35);
        childToChild.setLeftSibling(childToChild);
        childToChild.setRightSibling(childToChild);
        childToChild.setParent(childNode);

        this.heap.getMaxNode().setChild(childNode);
        childNode.setChild(childToChild);

        this.heap.increaseKey(childToChild, 45);
        assertEquals(childToChild, this.heap.getMaxNode().getLeftSibling());
    }

    @Test
    public void increasingChildValueAboveParentValueLeavesOtherChildToChildList() {
        this.heap.insert(50);

        FibonacciNode childNode = new FibonacciNode(40);
        childNode.setLeftSibling(childNode);
        childNode.setRightSibling(childNode);
        childNode.setParent(this.heap.getMaxNode());

        FibonacciNode childToChild = new FibonacciNode(35);
        childToChild.setParent(childNode);

        FibonacciNode siblingToChildToChild = new FibonacciNode(5);
        childToChild.setLeftSibling(siblingToChildToChild);
        childToChild.setRightSibling(siblingToChildToChild);

        this.heap.getMaxNode().setChild(childNode);
        childNode.setChild(childToChild);

        this.heap.increaseKey(childToChild, 45);
        assertEquals(siblingToChildToChild, childNode.getChild());
    }

    public void increasingSecondChildValueAboveParentValueLeavesChildListNull() {
        this.heap.insert(50);

        FibonacciNode childNode = new FibonacciNode(40);
        childNode.setLeftSibling(childNode);
        childNode.setRightSibling(childNode);
        childNode.setParent(this.heap.getMaxNode());

        FibonacciNode childToChild = new FibonacciNode(35);
        childToChild.setParent(childNode);

        FibonacciNode siblingToChildToChild = new FibonacciNode(5);
        childToChild.setLeftSibling(siblingToChildToChild);
        childToChild.setRightSibling(siblingToChildToChild);

        this.heap.getMaxNode().setChild(childNode);
        childNode.setChild(childToChild);

        this.heap.increaseKey(childToChild, 45);
        this.heap.increaseKey(siblingToChildToChild, 55);
        assertEquals(null, childNode.getChild());
    }

    @Test
    public void increasingChildWithChildValueAboveParentValueMaintainsChildParentRelationship() {
        this.heap.insert(50);

        FibonacciNode maxAtStart = this.heap.getMaxNode();

        FibonacciNode childNode = new FibonacciNode(40);
        childNode.setLeftSibling(childNode);
        childNode.setRightSibling(childNode);
        childNode.setParent(this.heap.getMaxNode());

        FibonacciNode childToChild = new FibonacciNode(35);
        childToChild.setParent(childNode);

        FibonacciNode siblingToChildToChild = new FibonacciNode(5);
        childToChild.setLeftSibling(siblingToChildToChild);
        childToChild.setRightSibling(siblingToChildToChild);

        this.heap.getMaxNode().setChild(childNode);
        childNode.setChild(childToChild);

        this.heap.increaseKey(childNode, 55);
        assertEquals(childToChild, this.heap.getMaxNode().getChild());
    }

    @Test
    public void deletingTheOnlyNodeMakesMaxNodeNull() {
        this.heap.insert(50);
        this.heap.delete(this.heap.getMaxNode());
        assertEquals(null, this.heap.getMaxNode());
    }

    @Test
    public void deletingTheMaxNodeMakesSecondLargestValueNewMaxNode() {
        this.heap.insert(40);
        this.heap.insert(50);
        this.heap.insert(30);
        this.heap.delete(this.heap.getMaxNode());
        assertEquals(40, this.heap.returnMax());
    }

    @Test
    public void maximumValueIsSetCorrectlyAfterBothDeleteMaxAndDelete() {
        for (int i = 0; i <= 100; i++) {
            this.heap.insert(i);
        }

        this.heap.deleteMax();
        assertEquals(99, this.heap.returnMax());
        this.heap.delete(this.heap.getMaxNode());
        assertEquals(98, this.heap.returnMax());
    }
}
