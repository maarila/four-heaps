package components;

/**
 * This class provides the basic implementation of a binary heap. It will be
 * used as a point of comparison for other more uncommon implementations of the
 * heap data structure.
 * 
 * @author Mika Äärilä
 */

public class BinaryHeap {

    /**
     * The basic elements of a binary heap - a heap array representing the heap
     * and a heapSize pointer pointing at the end of heap.
     */
    private int[] heap;
    private int heapSize;

    /**
     * Constructor for the binary heap.
     * 
     * @param heapSize the starting size of the heap.
     */
    public BinaryHeap(int heapSize) {
        this.heap = new int[heapSize];
        this.heapSize = 0;
    }

    /**
     * Returns the index of the parent of the current node.
     * 
     * @param index the index of the current node.
     * @return the index of the parent node.
     */
    public int parent(int index) {
        return index / 2;
    }

    /**
     * Returns the index of the left child node.
     * 
     * @param index the index of the current node.
     * @return the index of the left child node.
     */
    public int leftChild(int index) {
        return 2 * index;
    }

    /**
     * Returns the index of the right child node.
     * 
     * @param index the index of the current node.
     * @return the index of the right child node.
     */
    public int rightChild(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the maximum value of the heap.
     * 
     * @return the maximum value located on the first position of the array.
     */
    public int returnMax() {
        return this.heap[0];
    }
    
    /**
     * Deletes the maximum value of the heap and returns it. At the same time
     * subtracts one from the pointer at end of the heap and fixes the heap via the heapify
     * operation.
     * 
     * @return the maximum value that was just deleted.
     */
    public int deleteMax() {
        int maxValue = this.heap[0];
        this.heap[0] = this.heap[this.heapSize];
        this.heapSize--;
        heapify(this.heap, 0);

        return maxValue;
    }

    /**
     * Inserts a new value into the heap. Also, adds one to the pointer at the 
     * end of the heap and, if necessary, increases the size of the heap
     * (doubles it, to be more precise).
     * 
     * @param newValue the value to be inserted into the heap.
     */
    public void insert(int newValue) {
        this.heapSize++;
        if (this.heapSize >= this.heap.length) {
            increaseHeapSize();
        }
        
        int index = this.heapSize - 1;

        while (index > 0 && this.heap[parent(index)] < newValue) {
            this.heap[index] = this.heap[parent(index)];
            index = parent(index);
        }

        this.heap[index] = newValue;
    }
    
    /**
     * Fixes the heap if it is broken at the given index - "broken" meaning
     * that the key at the given index is smaller than either of its children. 
     * The fixing procedureis continued iteratively downwards until the heap is 
     * no longer broken.
     * 
     * @param heap the heap to be fixed.
     * @param index the index of the node to be checked.
     */
    public void heapify(int[] heap, int index) {
        int largest = 0;

        while (index <= this.heapSize) {
            int l = leftChild(index);
            int r = rightChild(index);

            if (l <= this.heapSize && heap[l] > heap[index]) {
                largest = l;
            } else {
                largest = index;
            }

            if (r <= this.heapSize && heap[r] > heap[largest]) {
                largest = r;
            }

            if (largest == index) {
                return;
            }

            int helperVariable = heap[index];
            heap[index] = heap[largest];
            heap[largest] = helperVariable;
            index = largest;
        }
    }
    
    /**
     * Increases the value of the key in the given node index.
     * 
     * @param index the index of the node for which the value of the key is to
     *              be increased.
     * @param newKey the new value of the key.
     */
    public void increaseKey(int index, int newKey) {
        if (newKey > this.heap[index]) {
            this.heap[index] = newKey;
            while (index > 0 && this.heap[parent(index)] < this.heap[index]) {
                int helperVariable = this.heap[index];
                this.heap[index] = this.heap[parent(index)];
                this.heap[parent(index)] = helperVariable;
                index = parent(index);
            }
        }
    }
    
    /**
     * Decreases the value of the key in the given node index.
     * 
     * @param index the index of the node for which the value of the key is to
     *              be decreased.
     * @param newKey the new value of the key.
     */
    public void decreaseKey(int index, int newKey) {
        if (newKey < this.heap[index]) {
            this.heap[index] = newKey;
            heapify(this.heap, index);
        }
    }
    
    /**
     * Doubles the size of the heap whenever the current size is not adequate
     * for inserting new nodes.
     */
    public void increaseHeapSize() {
        int[] newHeap = new int[this.heap.length * 2];
        
        for (int i = 0; i < this.heap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        
        this.heap = newHeap;        
    }
    
    /**
     * Returns the information whether the heap is empty or not.
     * 
     * @return true or false depending on the state of the heap.
     */
    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    /**
     * Returns the heap as an integer array.
     * 
     * @return array containing the heap.
     */
    public int[] getHeap() {
        return heap;
    }

    /**
     * Set an integer array to act as the current heap.
     * 
     * @param heap the new integer array 
     */
    public void setHeap(int[] heap) {
        this.heap = heap;
    }

    /**
     * Returns the current size of the heap.
     * 
     * @return size of heap.
     */
    public int getHeapSize() {
        return heapSize;
    }
}