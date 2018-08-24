## General structure of the program

The program consists of four implementations of the heap data structure: binary heap, binomial heap, pairing heap and fibonacci heap. In addition, the program has a rudimentary console-based command line interface for running performance tests on the heap operations of the aforementioned heaps (for further information, see [Testing Report](https://github.com/maarila/four-heaps/blob/master/documentation/TestingReport.md)).

All the heap implementations are maximum heaps and they have the standard heap operations:

* heap-insert(H, k): add key _k_ into heap _H_
* heap-max(H): return the maximum key value in heap _H_
* heap-del-max(H): delete and return the maximum key value in heap _H_.

The heaps also have a selection of helper methods as required by the above operations.

## Time and space complexities

__Binary heap:__ 

_insert_: most operations require a constant time, except for bringing a new item to its correct position ('bubbling-up'). As the binary heap is in the form of a binary tree, its height is logarithmic in regards to the number of its nodes, and therefore the operation (below) has a worst-case time complexity of O(log n).

```
while (index > 0 && this.heap[parent(index)] < newValue) {
  this.heap[index] = this.heap[parent(index)];
  index = parent(index);
}
```

Also, should the array require doubling, the operation has an amortized time complexity of O(1). All in all, then, the insert operation has a worst-case time complexity of O(log n).

_return max_: performs a single operation to return the first item in the heap array, and thus requires a constant time. Time complexity is O(1).

_delete max_: deleting and replacing the maximum value consists of constant time operations, but subsequent heapify operation, which is the inverse of the bubble-up operation in insert, has a worst-case time complexity of O(log n), if the replacing value needs to be taken all the way down to the leaf of the tree.

__Binomial heap:__

* insert:
* return max:
* delete max:

__Pairing heap:__

* insert:
* return max:
* delete max:

__Fibonacci heap:__

* insert:
* return max:
* delete max:

## Performance comparisons



## Things that could be improved



## Sources

Cormen, T. H., Leiserson, C. E., Rivest, R. L. & Stein C. 2001. Introduction to Algorithms. Second Edition. The MIT Press.

Cormen, T. H., Leiserson, C. E., Rivest, R. L. & Stein C. 2009. Introduction to Algorithms. Third Edition. The MIT Press.

Fredman, M. L., Sedgeweick, R., Sleator, D. D. & Tarjan, R. E. (1986). The Pairing Heap: A New Form of Self-Adjusting Heap. __Algorithmica__, 1, 111-129.

Fredman, M. L. (2005). Binomial, Fibonacci, and Pairing Heaps. In Mehta, D. P. & Sahni, S. (Eds.), Handbook of data structures and applications. Boca Raton, FL, USA: Chapman & Hall/CRC.

Kivinen, J. 2018. Tietorakenteet ja algoritmit. Course material.

Wikipedia [1]. 2018. Binomial heap. Read on 25.7.2018.

Wikipedia [2]. 2018. Pairing heap. Read on 25.7.2018.

