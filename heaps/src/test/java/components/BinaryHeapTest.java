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
        assertEquals(0, this.heap.getHeapSize());
    }

    @Test
    public void getHeapSizeReturnsTheCorrectValue() {
        this.heap.insert(1);
        this.heap.insert(2);
        this.heap.insert(3);
        assertEquals(3, this.heap.getHeapSize());
    }

    @Test
    public void sizeOfHeapEqualsSizeGivenToConstructor() {
        assertEquals(10, this.heap.getHeap().length);
    }

    @Test
    public void heapIsNotEmptyAfterOneInsertion() {
        this.heap.insert(5);
        assertFalse(heap.isEmpty());
    }

    @Test
    public void heapMaxValueEqualsTheOnlyValue() {
        this.heap.insert(5);
        assertEquals(5, this.heap.returnMax());
    }

    @Test
    public void heapifyProcessesValuesSuccessfully() {
        this.heap.insert(5);
        assertEquals(5, this.heap.returnMax());
        this.heap.insert(10);
        assertEquals(10, this.heap.returnMax());
        this.heap.insert(8);
        assertEquals(10, this.heap.returnMax());
    }

    @Test
    public void heapifyDoesNothingIfGivenIndexIsTooHigh() {
        int[] heapAsArray = this.heap.getHeap();
        heapAsArray[0] = 5;
        heapAsArray[1] = 15;
        heapAsArray[2] = 25;
        this.heap.setHeap(heapAsArray);
        this.heap.heapify(this.heap.getHeap(), 10);
        assertEquals(5, this.heap.returnMax());
    }

    @Test
    public void increaseKeyWorksAsExpectedWhenGivenValueIsHigherThanOriginalAndOnlyValue() {
        this.heap.insert(5);
        this.heap.increaseKey(5, 10);
        assertEquals(10, this.heap.returnMax());
    }

    @Test
    public void increaseKeyDoesNothingWhenGivenValueIsLowerThanOriginalValue() {
        this.heap.insert(5);
        this.heap.increaseKey(5, 3);
        assertEquals(5, this.heap.returnMax());
    }

    @Test
    public void increaseKeyDoesNothingWhenGivenValueIsNotFound() {
        this.heap.insert(15);
        this.heap.increaseKey(9, 30);
        assertEquals(15, this.heap.returnMax());
    }

    @Test
    public void increaseKeyDoesNothingWhenGivenValueIsEqualToOriginalValue() {
        this.heap.insert(10);
        this.heap.increaseKey(10, 10);
        assertEquals(10, this.heap.returnMax());
    }

    @Test
    public void increaseKeySetsTheChangedKeyCorrectlyAsNewMaximumValue() {
        this.heap.insert(50);
        this.heap.insert(45);
        this.heap.insert(40);
        this.heap.insert(55);
        this.heap.insert(60);
        this.heap.insert(65);
        this.heap.insert(10);
        assertEquals(65, this.heap.returnMax());
        this.heap.increaseKey(40, 100);
        assertEquals(100, this.heap.returnMax());
    }

    @Test
    public void increaseKeyWorksCorrectlyForValueOtherThanMaximumValue() {
        this.heap.insert(10);
        this.heap.insert(20);
        this.heap.increaseKey(10, 15);
        int[] currentHeap = this.heap.getHeap();
        assertEquals(15, currentHeap[1]);
    }

    @Test
    public void decreaseKeyWorksAsExpectedWhenGivenValueIsLowerThanOriginalValue() {
        this.heap.insert(10);
        this.heap.decreaseKey(10, 5);
        assertEquals(5, this.heap.returnMax());
    }

    @Test
    public void decreaseKeyDoesNothingWhenGivenValueIsHigherThanOriginalValue() {
        this.heap.insert(10);
        this.heap.decreaseKey(10, 15);
        assertEquals(10, this.heap.returnMax());
    }

    @Test
    public void decreaseKeyDoesNothingWhenGivenValueIsZero() {
        this.heap.insert(15);
        this.heap.decreaseKey(15, 0);
        assertEquals(15, this.heap.returnMax());
    }

    @Test
    public void decreaseKeyDoesNothingWhenGivenValueIsNegative() {
        this.heap.insert(15);
        this.heap.decreaseKey(15, -10);
        assertEquals(15, this.heap.returnMax());
    }

    @Test
    public void decreaseKeyDoesNothingWhenGivenValueIsNotFound() {
        this.heap.insert(15);
        this.heap.decreaseKey(999, 10);
        assertEquals(15, this.heap.returnMax());
    }

    @Test
    public void decreaseKeyDoesNothingWhenGivenValueIsEqualToOriginalValue() {
        this.heap.insert(10);
        this.heap.decreaseKey(10, 10);
        assertEquals(10, this.heap.returnMax());
    }

    @Test
    public void indexOfReturnsCorrectIndex() {
        this.heap.insert(5);
        this.heap.insert(10);
        int indexOf = this.heap.indexOf(5);
        assertEquals(1, indexOf);

    }

    @Test
    public void indexOfReturnsMinusOneIfGivenValueIsNotFound() {
        this.heap.insert(5);
        this.heap.insert(10);
        int indexOf = this.heap.indexOf(999);
        assertEquals(-1, indexOf);
    }

    @Test
    public void heapSizeIsDoubledWhenNeeded() {
        BinaryHeap smallHeap = new BinaryHeap(3);
        assertEquals(3, smallHeap.getHeap().length);
        smallHeap.insert(10);
        smallHeap.insert(20);
        smallHeap.insert(30);
        smallHeap.insert(40);
        assertEquals(6, smallHeap.getHeap().length);
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
        assertEquals(20, deletedValue);
    }

    @Test
    public void deletingMaxValueCausesTheSecondGreatestValueToBecomeMaxValue() {
        this.heap.insert(10);
        this.heap.insert(20);
        this.heap.insert(15);
        this.heap.deleteMax();
        assertEquals(15, this.heap.returnMax());
    }
}
