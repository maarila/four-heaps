package components;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PairingHeapTest {

    private PairingHeap heap;

    public PairingHeapTest() {
    }

    @Before
    public void setUp() {
        this.heap = new PairingHeap();
    }

    @Test
    public void rootOfNewPairingHeapIsNull() {
        assertTrue(this.heap.getRoot() == null);
    }

    @Test
    public void emptyHeapReturnsIntegerMinValueAsMaxValue() {
        assertTrue(this.heap.returnMax() == Integer.MIN_VALUE);
    }

    @Test
    public void heapWithOneValueHasNullAsLeftChild() {
        this.heap.insert(10);
        assertTrue(this.heap.getRoot().getLeftPointerToChild() == null);
    }

    @Test
    public void heapWithOneValueHasNullAsSibling() {
        this.heap.insert(10);
        assertTrue(this.heap.getRoot().getRightPointerToSibling() == null);
    }

    @Test
    public void rootOfNonEmptyHeapHasNullAsParentValue() {
        this.heap.insert(10);
        assertTrue(this.heap.getRoot().getParent() == null);
    }

    @Test
    public void insertingNewValueToEmptyHeapReturnsSaidValueAsMaxValue() {
        this.heap.insert(10);
        assertEquals(10, this.heap.returnMax());
    }

    @Test
    public void insertingSmallerValueThanMaxValueDoesNotChangeMaxValue() {
        this.heap.insert(50);
        assertEquals(50, this.heap.returnMax());
        this.heap.insert(10);
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void insertingGreaterValueThanMaxValueChangesMaxValue() {
        this.heap.insert(10);
        assertEquals(10, this.heap.returnMax());
        this.heap.insert(50);
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void insertingSmallerValueThanMaxValueLinksSaidValueAsRootsLeftChild() {
        this.heap.insert(50);
        this.heap.insert(10);
        assertEquals(10, this.heap.getRoot().getLeftPointerToChild().getKey());
    }

    @Test
    public void insertingThirdNonMaxValueMakesSecondValueTheSiblingOfThirdValue() {
        this.heap.insert(50);
        this.heap.insert(10);
        this.heap.insert(20);
        assertEquals(10, this.heap.getRoot().getLeftPointerToChild().getRightPointerToSibling().getKey());
    }

    @Test
    public void deletingMaxValueMakesSecondLargestNewMaxValue() {
        this.heap.insert(50);
        this.heap.insert(10);
        assertEquals(50, this.heap.returnMax());
        this.heap.deleteMax();
        assertEquals(10, this.heap.returnMax());
    }
}
