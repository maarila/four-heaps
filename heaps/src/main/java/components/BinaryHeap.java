package components;

public class BinaryHeap {

    private int[] heap;
    private int heapSize;

    public BinaryHeap(int heapSize) {
        this.heap = new int[heapSize];
        this.heapSize = heapSize;
    }

    public int parent(int index) {
        return index / 2;
    }

    public int leftChild(int index) {
        return 2 * index;
    }

    public int rightChild(int index) {
        return 2 * index + 1;
    }

    public int returnMax() {
        return this.heap[0];
    }
    
    public int deleteMax() {
        int maxValue = this.heap[0];
        this.heap[0] = this.heap[this.heapSize];
        this.heapSize--;
        heapify(this.heap, 0);

        return maxValue;
    }

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
    
    public void decreaseKey(int index, int newKey) {
        if (newKey < this.heap[index]) {
            this.heap[index] = newKey;
            heapify(this.heap, index);
        }
    }
    
    public void increaseHeapSize() {
        int[] newHeap = new int[this.heap.length * 2];
        
        for (int i = 0; i < this.heap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        
        this.heap = newHeap;        
    }
    
}
