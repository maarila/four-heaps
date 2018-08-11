package components;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryHeapTest {

    BinaryHeap heap;

    public BinaryHeapTest() {
    }

    @Before
    public void setUp() {
        heap = new BinaryHeap(10);
    }

    @Test
    public void heapIsEmptyAtStart() {
        assertTrue(heap.isEmpty());
    }

    @Test
    public void getHeapSizeReturnsZeroAtStart() {
        assertEquals(this.heap.getHeapSize(), 0);
    }

    @Test
    public void getHeapSizeReturnsTheCorrectValue() {
        this.heap.insert(1);
        this.heap.insert(2);
        this.heap.insert(3);
        assertEquals(this.heap.getHeapSize(), 3);
    }

    @Test
    public void sizeOfHeapEqualsSizeGivenToConstructor() {
        assertEquals(this.heap.getHeap().length, 10);
    }

    @Test
    public void heapIsNotEmptyAfterOneInsertion() {
        this.heap.insert(5);
        assertFalse(heap.isEmpty());
    }

    @Test
    public void heapMaxValueEqualsTheOnlyValue() {
        this.heap.insert(5);
        assertEquals(this.heap.returnMax(), 5);
    }

    @Test
    public void heapifyProcessesValuesSuccessfully() {
        this.heap.insert(5);
        assertEquals(this.heap.returnMax(), 5);
        this.heap.insert(10);
        assertEquals(this.heap.returnMax(), 10);
        this.heap.insert(8);
        assertEquals(this.heap.returnMax(), 10);
    }

    @Test
    public void heapifyDoesNothingIfGivenIndexIsTooHigh() {
        int[] heapAsArray = this.heap.getHeap();
        heapAsArray[0] = 5;
        heapAsArray[1] = 15;
        heapAsArray[2] = 25;
        this.heap.setHeap(heapAsArray);
        this.heap.heapify(this.heap.getHeap(), 10);
        assertEquals(this.heap.returnMax(), 5);
    }

    @Test
    public void increaseKeyWorksAsExpectedWhenGivenValueIsHigherThanOriginalValue() {
        this.heap.insert(5);
        this.heap.increaseKey(0, 10);
        assertEquals(this.heap.returnMax(), 10);
    }

    @Test
    public void increaseKeyDoesNothingWhenGivenValueIsLowerThanOriginalValue() {
        this.heap.insert(5);
        this.heap.increaseKey(0, 3);
        assertEquals(this.heap.returnMax(), 5);
    }

    @Test
    public void increaseKeyPlacesTheChangedKeyCorrectly() {
        this.heap.insert(50);
        this.heap.insert(45);
        this.heap.insert(40);
        this.heap.insert(55);
        this.heap.insert(60);
        this.heap.insert(65);
        this.heap.insert(10);
        assertEquals(this.heap.returnMax(), 65);
        this.heap.increaseKey(6, 100);
        assertEquals(this.heap.returnMax(), 100);
    }

    @Test
    public void increaseKeyMovesNewValueToCorrectPosition() {
        int[] heapAsArray = this.heap.getHeap();
        heapAsArray[0] = 50;
        heapAsArray[1] = 15;
        heapAsArray[2] = 25;
        heapAsArray[3] = 10;
        this.heap.setHeap(heapAsArray);
        this.heap.increaseKey(3, 20);
        heapAsArray = this.heap.getHeap();
        assertEquals(heapAsArray[1], 20);
    }

    @Test
    public void decreaseKeyWorksAsExpectedWhenGivenValueIsLowerThanOriginalValue() {
        this.heap.insert(10);
        this.heap.decreaseKey(0, 5);
        assertEquals(this.heap.returnMax(), 5);
    }

    @Test
    public void decreaseKeyDoesNothingWhenGivenValueIsHigherThanOriginalValue() {
        this.heap.insert(10);
        this.heap.decreaseKey(0, 15);
        assertEquals(this.heap.returnMax(), 10);
    }

    @Test
    public void heapSizeIsDoubledWhenNeeded() {
        BinaryHeap smallHeap = new BinaryHeap(3);
        assertEquals(smallHeap.getHeap().length, 3);
        smallHeap.insert(10);
        smallHeap.insert(20);
        smallHeap.insert(30);
        smallHeap.insert(40);
        assertEquals(smallHeap.getHeap().length, 6);
    }

    @Test
    public void heapSizeIsHalvedWhenNeeded() {
        BinaryHeap smallHeap = new BinaryHeap(3);
        smallHeap.insert(10);
        smallHeap.insert(20);
        smallHeap.insert(30);
        smallHeap.insert(40);
        assertEquals(6, smallHeap.getHeap().length);
        smallHeap.deleteMax();
        assertEquals(3, smallHeap.getHeap().length);
    }

    @Test
    public void heapSizeIsNotHalvedWhenNotNeeded() {
        BinaryHeap smallHeap = new BinaryHeap(3);
        smallHeap.insert(10);
        smallHeap.insert(20);
        smallHeap.insert(30);
        smallHeap.insert(40);
        smallHeap.insert(50);
        assertEquals(6, smallHeap.getHeap().length);
        smallHeap.deleteMax();
        assertEquals(6, smallHeap.getHeap().length);
    }

    @Test
    public void heapSizeIsNotHalvedWhenSizeIsLessThanStartingSize() {
        BinaryHeap smallHeap = new BinaryHeap(3);
        assertEquals(3, smallHeap.getHeap().length);
        smallHeap.insert(10);
        smallHeap.deleteMax();
        assertEquals(3, smallHeap.getHeap().length);
    }

    @Test
    public void deletingMaxValueReturnsTheCorrectValue() {
        this.heap.insert(10);
        this.heap.insert(20);
        this.heap.insert(15);
        int deletedValue = this.heap.deleteMax();
        assertEquals(deletedValue, 20);
    }

    @Test
    public void deletingMaxValueCausesTheSecondGreatestValueToBecomeMaxValue() {
        this.heap.insert(10);
        this.heap.insert(20);
        this.heap.insert(15);
        this.heap.deleteMax();
        assertEquals(this.heap.returnMax(), 15);
    }
}
