## What was tested and how?

The project consists of four heap data structures: binary heap, binomial heap, pairing heap and fibonacci heap. All heaps have the standard heap operations for maximum heaps: insert (implemented as _insert(int newKey)_), return maximum value (implemented as _returnMax()_) and delete maximum value (implemented as _deleteMax()_).

In addition to the aforementioned heaps, the performance tests were also run for the Java standard implementation priority queue data structure and its respective operations: _add_, _peak_ and _poll_.

__Unit testing__

The purpose of the unit tests is two-fold: first, to make sure that the functions return values that are correct, and thus, to confirm that the functions behave as intended. Second, to make sure that the heap retains its intended shape after the various heap operations.

The tests strive to achieve both things via various heap manipulations. Insertion is confirmed functional by making sure that the new node is not only inserted into the heap, but that it also finds its correct place.

Tests for finding the maximum value stored in the heap are rather straight-forward: since all data is handpicked, confirming the returned maximum value matches the given maximum value is trivial.

The testing of the shape of the heap is achieved by examining the root node (known as 'head' in binomial heap and 'max' in fibonacci heap) and its relationship to its siblings and children. And conversely, the relationship of the children to their parent nodes.

The heap is manipulated by extracting nodes via deleteMax() operation. The tests will not only confirm that the operation returns the correct value, but also deletes said value and recreates, or rather reshapes, the heap correctly in the process.

Increase key operations are confirmed functional by not only making sure that the operations bring new maximum keys to the top of the heap, but also by making sure that the heap condition (no node should have children with higher key value than itself has) is valid.

__Performance testing__

The operations were tested using integer values and by running the tests with each input size ten times. The end result is the average of the test results. The input sizes were divided into five categories - from a hundred thousand to eight million. Even larger input sizes were also considered and tested, but they were found infeasible to run due to the tests crashing the computer.

---

_insert operation:_

![Insert comparison](https://github.com/maarila/four-heaps/blob/master/img/insert-linear.png)

The results for the insert operations show that the binary heap is uniformly the fastest data structure. All the other heaps (including the Java priority queue) are more or less equally fast or slow, with real differences only starting to show with the largest input size.

---

_returnMax operation:_

![Return max comparison](https://github.com/maarila/four-heaps/blob/master/img/return-max-linear.png)

The result of the tests for the return max operation is quite divided. All other data structures returned the data in under a millisecond (shown in the graph as a single blue line), which is consistent with the expectation of them having O(1) time complexity. The only exception was the binomial heap with its expected time complexity of O(log n). Starting from a sample size of a hundred thousand, it started trailing the other heaps more and more.

---

_deleteMax operation:_

![Delete max comparison](https://github.com/maarila/four-heaps/blob/master/img/delete-max-linear.png)

Delete max operation turned out to be the most interesting one of all operations. The other heaps, including the priority queue, showed rather uniform results but for the pairing heap, things were more varied. When the pairing heap was given its input in sequential order (1 -> n) the delete max operation was clearly the fastest one of all. If, however, the input was given in reverse sequential order (n -> 1), the delete max operation for the pairing heap was the slowest one. On average, the pairing heap falls in line with the other data structures, which is consistent with its amortized time complexity of O(log n).

## Conclusions

As far as the basic heap operations go, binary heap is by far the fastest and most reliable choice. It had the fastest insert operations, a no-delay O(1) return max and a competitive delete max operation. The only one that can compete with the binary heap, to some extent, is the pairing heap - and even that only if delete max operations are the main focus of the data structure and there is some guarantee that the items in the heap are at least somewhat ordered.

Binomial heap, fibonacci heap and the Java standard implementation priority queue are all more or less underwhelming. Priority queue suffers from a slow-ish insert operation when dealing with large input sizes, where as the binomial heap is not useful as an all-purpose heap due to the O(log n) return max operation. Fibonacci heap's weakness is the delete max operation which trails all other tested heap structures starting from small input sizes. The case for fibonacci heap is further weakened by the fact that it is arduous, to say the least, to implement.

## How to reproduce the test results?

The program offers a text-based command line interface that enables the running of all tests with all input sizes used in the project. Thus, reproducing the test results can be achieved simply by running the program and performing any and all desired tests. For further instructions on running the program and the tests, please see [User Guide](https://github.com/maarila/four-heaps/blob/master/documentation/UserGuide.md).
