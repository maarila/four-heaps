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
    public void heapWithOneValueHasNoLeftSibling() {
        this.heap.insert(50);
        assertEquals(null, this.heap.getMaxNode().getLeftSibling());
    }

    @Test
    public void heapWithOneValueHasNoRightSibling() {
        this.heap.insert(50);
        assertEquals(null, this.heap.getMaxNode().getRightSibling());
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
    
    

}
