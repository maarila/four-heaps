package components;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinomialHeapTest {

    BinomialHeap heap;

    public BinomialHeapTest() {
    }

    @Before
    public void setUp() {
        this.heap = new BinomialHeap();
    }

    @Test
    public void headOfNewHeapIsNull() {
        assertEquals(null, this.heap.getHead());
    }

    @Test
    public void makeBinomialHeapReturnsNewHeapWithHeadSetToNull() {
        BinomialHeap newHeap = this.heap.makeBinomialHeap();
        assertEquals(null, newHeap.getHead());
    }

    @Test
    public void callingReturnMaxValueOnEmptyHeapReturnsIntegerMinValue() {
        assertEquals(Integer.MIN_VALUE, this.heap.returnMax());
    }

    @Test
    public void insertingNewValueIntoEmptyHeapMakesGivenValueMaxValue() {
        this.heap.insert(10);
        assertEquals(10, this.heap.returnMax());
    }

    @Test
    public void returnMaxValueReturnsHeapsMaximumValue() {
        this.heap.insert(10);
        this.heap.insert(20);
        assertEquals(20, this.heap.returnMax());
    }

    @Test
    public void insertingSmallerThanMaxValueIntoNonEmptyHeapDoesNotChangeMaxValue() {
        this.heap.insert(100);
        assertEquals(100, this.heap.returnMax());
        this.heap.insert(50);
        assertEquals(100, this.heap.returnMax());
    }

    @Test
    public void insertingGreaterThanMaxValueIntoNonEmptyHeapChangesMaxValue() {
        this.heap.insert(50);
        assertEquals(50, this.heap.returnMax());
        this.heap.insert(100);
        assertEquals(100, this.heap.returnMax());
    }

    @Test
    public void insertingEqualMaxValuesReturnsOneOfMaxValues() {
        this.heap.insert(50);
        this.heap.insert(30);
        this.heap.insert(20);
        this.heap.insert(50);
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void insertingIntegerMinValueReturnsIntegerMinValueAsMaxValue() {
        this.heap.insert(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, this.heap.returnMax());
    }

    @Test
    public void insertingSmallerValueAsSecondNodeMakesSaidNodeChildNode() {
        this.heap.insert(100);
        Node currentHead = this.heap.getHead();
        assertEquals(null, currentHead.getLeftmostChild());
        this.heap.insert(50);
        assertEquals(50, currentHead.getLeftmostChild().getKey());
    }

    @Test
    public void insertingAThirdValueMakesSaidNodeNewHeadNode() {
        this.heap.insert(100);
        this.heap.insert(50);
        Node currentHead = this.heap.getHead();
        assertEquals(null, currentHead.getSiblingToTheRight());
        this.heap.insert(75);
        currentHead = this.heap.getHead();
        assertEquals(75, currentHead.getKey());
    }

    @Test
    public void insertingAThirdValueMakesSaidNodeHeadNodeThatHasPreviousHeadAsSibling() {
        this.heap.insert(100);
        this.heap.insert(50);
        Node currentHead = this.heap.getHead();
        assertEquals(100, currentHead.getKey());
        this.heap.insert(75);
        currentHead = this.heap.getHead();
        assertEquals(100, currentHead.getSiblingToTheRight().getKey());
    }

    @Test
    public void deletingMaxValueFromEmptyHeapReturnsIntegerMinValue() {
        int returnValue = this.heap.deleteMax();
        assertEquals(Integer.MIN_VALUE, returnValue);
    }

    @Test
    public void deletingMaxValueFromHeapWithOneValueReturnsSaidValue() {
        this.heap.insert(100);
        int returnValue = this.heap.deleteMax();
        assertEquals(100, returnValue);
    }

    @Test
    public void deletingMaxValueFromHeapWithManyValuesReturnsSaidValue() {
        this.heap.insert(30);
        this.heap.insert(15);
        this.heap.insert(45);
        this.heap.insert(60);
        this.heap.insert(20);
        int returnValue = this.heap.deleteMax();
        assertEquals(60, returnValue);
    }

    @Test
    public void deletingMaxValueFromHeapWithManyEqualMaxValuesDeletesOneOfSaidValues() {
        this.heap.insert(100);
        this.heap.insert(100);
        this.heap.insert(100);
        int returnValue = this.heap.deleteMax();
        assertEquals(100, returnValue);
    }

    @Test
    public void deletingMaxValueMakesOtherValueNewMaxValue() {
        this.heap.insert(50);
        this.heap.insert(100);
        this.heap.deleteMax();
        assertEquals(50, this.heap.returnMax());
    }

    @Test
    public void deletingIntegerMinValueAsMaxValueFromHeapReturnsIntegerMinValue() {
        this.heap.insert(Integer.MIN_VALUE);
        int returnValue = this.heap.deleteMax();
        assertEquals(Integer.MIN_VALUE, returnValue);
    }

    @Test
    public void mergeReturnsFirstHeapsHeadIfSecondHeapHeadIsNull() {
        this.heap.insert(10);
        Node firstHeapHead = this.heap.getHead();
        BinomialHeap emptyHeap = new BinomialHeap();
        Node mergedHead = this.heap.merge(this.heap, emptyHeap);
        assertEquals(firstHeapHead.getKey(), mergedHead.getKey());
    }

    @Test
    public void unionOfTwoEmptyHeapsReturnsAnEmptyHeap() {
        BinomialHeap firstEmpty = new BinomialHeap();
        BinomialHeap secondEmpty = new BinomialHeap();
        BinomialHeap unitedHeap = firstEmpty.union(secondEmpty);
        assertEquals(null, unitedHeap.getHead());
    }

    @Test
    public void unionOfNonEmptyAndEmptyHeapReturnsTheNonEmptyHeap() {
        this.heap.insert(100);
        this.heap.insert(40);
        BinomialHeap emptyHeap = new BinomialHeap();
        BinomialHeap unitedHeap = this.heap.union(emptyHeap);
        assertEquals(100, unitedHeap.returnMax());
    }

    @Test
    public void unionOfEmptyAndNonEmptyHeapReturnsTheNonEmptyHeap() {
        this.heap.insert(100);
        this.heap.insert(40);
        BinomialHeap emptyHeap = new BinomialHeap();
        BinomialHeap unitedHeap = emptyHeap.union(this.heap);
        assertEquals(100, unitedHeap.returnMax());
    }

    @Test
    public void increaseKeyDoesNothingIfNewKeyIsSmallerThanExistingKey() {
        this.heap.insert(100);
        Node currentHead = this.heap.getHead();
        this.heap.increaseKey(currentHead, 50);
        assertEquals(100, currentHead.getKey());
    }
    
    @Test
    public void increaseKeyDoesNothingIfNewKeyIsEqualToExistingKey() {
        this.heap.insert(100);
        Node currentHead = this.heap.getHead();
        this.heap.increaseKey(currentHead, 100);
        assertEquals(100, currentHead.getKey());
    }

    @Test
    public void increaseKeyWorksCorrectlyGivenCorrectValue() {
        this.heap.insert(100);
        Node currentHead = this.heap.getHead();
        this.heap.increaseKey(currentHead, 150);
        assertEquals(150, currentHead.getKey());
    }
    
    @Test
    public void increaseKeyMakesIncreasedKeyNewMaximumGivenCorrectValue() {
        this.heap.insert(100);
        this.heap.insert(50);
        Node currentChild = this.heap.getHead().getLeftmostChild();
        this.heap.increaseKey(currentChild, 150);
        assertEquals(150, this.heap.returnMax());
    }
    
    @Test
    public void increaseKeySetsRelationShipsCorrectlyFollowingIncreaseProcedure() {
        this.heap.insert(100);
        this.heap.insert(80);
        this.heap.insert(50);
        this.heap.insert(40);
        assertEquals(50, this.heap.getHead().getLeftmostChild().getKey());
        Node leftMostChild = this.heap.getHead().getLeftmostChild().getLeftmostChild();
        this.heap.increaseKey(leftMostChild, 60);
        assertEquals(60, this.heap.getHead().getLeftmostChild().getKey());        
    }
}
