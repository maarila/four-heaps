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
    public void makeNewPairingHeapReturnsAnEmptyPairingHeap() {
        PairingHeap emptyHeap = this.heap.makePairingHeap();
        assertTrue(emptyHeap.getRoot() == null);
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
        assertTrue(this.heap.getRoot().getLeftmostChild() == null);
    }

    @Test
    public void heapWithOneValueHasNullAsRightSibling() {
        this.heap.insert(10);
        assertTrue(this.heap.getRoot().getRightSibling() == null);
    }

    @Test
    public void heapWithOneValueHasNullAsLeftSibling() {
        this.heap.insert(10);
        assertTrue(this.heap.getRoot().getLeftSibling() == null);
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
        assertEquals(10, this.heap.getRoot().getLeftmostChild().getKey());
    }

    @Test
    public void insertingThirdNonMaxValueMakesSecondValueTheSiblingOfThirdValue() {
        this.heap.insert(50);
        this.heap.insert(10);
        this.heap.insert(20);
        assertEquals(10, this.heap.getRoot().getLeftmostChild().getRightSibling().getKey());
    }

    @Test
    public void deletingMaxValueFromEmptyHeapReturnsIntegerMinValue() {
        int maxValue = this.heap.deleteMax();
        assertEquals(Integer.MIN_VALUE, maxValue);
    }

    @Test
    public void deletingOnlyValueMakesRootNull() {
        this.heap.insert(50);
        this.heap.deleteMax();
        assertTrue(this.heap.getRoot() == null);
    }

    @Test
    public void deletingMaxValueMakesSecondLargestNewMaxValue() {
        this.heap.insert(50);
        this.heap.insert(10);
        assertEquals(50, this.heap.returnMax());
        this.heap.deleteMax();
        assertEquals(10, this.heap.returnMax());
    }

    @Test
    public void increaseKeyDoesNothingIfNewValueIsLowerThanNodeValue() {
        this.heap.insert(50);
        this.heap.increaseKey(this.heap.getRoot(), 30);
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void increaseKeyChangesCurrentMaxValueAsExpected() {
        this.heap.insert(50);
        assertEquals(50, this.heap.returnMax());
        this.heap.increaseKey(this.heap.getRoot(), 100);
        assertEquals(100, this.heap.returnMax());
    }

    @Test
    public void increaseKeyWorksAsExpectedWhenMaxValueIsChanged() {
        this.heap.insert(50);
        this.heap.insert(10);
        this.heap.insert(20);
        assertEquals(10, this.heap.getRoot().getLeftmostChild().getRightSibling().getKey());
        this.heap.increaseKey(this.heap.getRoot().getLeftmostChild().getRightSibling(), 90);
        assertEquals(90, this.heap.returnMax());
    }

    @Test
    public void increaseKeyWorksAsExpectedWhenMaxValueIsNotChanged() {
        this.heap.insert(50);
        this.heap.insert(10);
        this.heap.insert(20);
        assertEquals(10, this.heap.getRoot().getLeftmostChild().getRightSibling().getKey());
        this.heap.increaseKey(this.heap.getRoot().getLeftmostChild().getRightSibling(), 30);
        assertEquals(30, this.heap.getRoot().getLeftmostChild().getKey());
    }

    @Test
    public void increaseKeyMakesChangedNodeTheLeftmostChildNodeOfRootNodeIfNewValueLessThanMax() {
        this.heap.insert(50);
        this.heap.insert(30);
        this.heap.insert(40);
        this.heap.insert(20);
        assertEquals(40, this.heap.getRoot().getLeftmostChild().getRightSibling().getKey());
        this.heap.increaseKey(this.heap.getRoot().getLeftmostChild().getRightSibling(), 45);
        assertEquals(45, this.heap.getRoot().getLeftmostChild().getKey());
    }

    @Test
    public void arraySizeInLinkingAfterDeleteMaxDoublesBeyondDefaultSizeSixteen() {
        for (int i = 70; i > 0; i--) {
            this.heap.insert(i);
        }
        this.heap.deleteMax();
        assertEquals(69, this.heap.returnMax());
    }

    @Test
    public void meldingANonNullNodeWithNullNodeReturnsNonNullNode() {
        PairingNode nonNull = new PairingNode(10);
        PairingHeap emptyHeap = new PairingHeap();
        PairingNode melded = this.heap.meld(nonNull, emptyHeap.getRoot());
        assertEquals(10, melded.getKey());
    }

    @Test
    public void meldingANullNodeWithANonNullNodeReturnsNonNullNode() {
        PairingNode nonNull = new PairingNode(10);
        PairingHeap emptyHeap = new PairingHeap();
        PairingNode melded = this.heap.meld(emptyHeap.getRoot(), nonNull);
        assertEquals(10, melded.getKey());
    }

    @Test
    public void meldingLargerFirstNodeWithSiblingsHandlesRelationshipsCorrectly() {
        this.heap.insert(50);
        this.heap.insert(40);
        this.heap.insert(30);
        assertEquals(40, this.heap.getRoot().getLeftmostChild().getRightSibling().getKey());
        PairingHeap otherHeap = new PairingHeap();
        otherHeap.insert(100);
        PairingNode melded = otherHeap.meld(otherHeap.getRoot(), this.heap.getRoot().getLeftmostChild());
        assertEquals(40, melded.getRightSibling().getKey());
    }

    @Test
    public void meldingLargerSecondNodeWithSiblingsHandlesRelationshipsCorrectly() {
        this.heap.insert(50);
        this.heap.insert(40);
        this.heap.insert(30);
        assertEquals(40, this.heap.getRoot().getLeftmostChild().getRightSibling().getKey());
        PairingHeap otherHeap = new PairingHeap();
        otherHeap.insert(100);
        PairingNode melded = otherHeap.meld(this.heap.getRoot().getLeftmostChild(), otherHeap.getRoot());
        assertEquals(40, melded.getRightSibling().getKey());
    }

}
