## What was tested and how?

The project consists of four heap data structures: binary heap, binomial heap, pairing heap and Fibonacci heap. All heaps have the standard heap operations for maximum heaps: insert (implemented as _insert(int newKey)_), return maximum value (implemented as _returnMax()_) and delete maximum value (implemented as _deleteMax()_).

In addition to the aforementioned heaps, the performance tests were also run for the Java standard implementation priority queue data structure and its respective operations _add_, _peak_ and _poll_.

### Unit testing

The purpose of the unit tests is two-fold: firstly, to make sure that the functions return values that are correct, and thus, to confirm that the functions behave as intended. Secondly, to make sure that the heap retains its intended shape after the various heap operations.

The tests strive to achieve both things via various heap manipulations. Insertion is confirmed functional by making sure that the new node is not only inserted into the heap, but that it also finds its correct place.

Tests for finding the maximum value stored in the heap are rather straight-forward: since all data is handpicked, confirming that the returned maximum value matches the given maximum value is trivial.

The testing of the shape of the heap is achieved by examining the root node (known as 'head' in binomial heap and 'max' in Fibonacci heap) and its relationship to its siblings and children. And conversely, the relationship of the children to their parent nodes.

The heap is manipulated by extracting nodes via delete-max operation. The tests will not only confirm that the operation returns the correct value, but also deletes said value and recreates, or rather reshapes, the heap correctly in the process.

Increase key operations are confirmed functional by not only making sure that the operations bring new maximum keys to the top of the heap, but also by making sure that the heap condition (no node should have children with higher key value than itself has) remains valid.

### Performance testing

The operations were tested using integer values and by running the tests ten times with each input size. The end result is the average of the test results. The input sizes were divided into five categories - from a hundred thousand to eight million. Even larger input sizes were also considered and tested, but they were found infeasible to run due to some of the tests crashing the computer.

---

__insert operation:__

![Insert comparison](https://github.com/maarila/four-heaps/blob/master/img/insert-linear.png)

The results for the insert operations show that the binary heap is uniformly the fastest data structure. All the other heaps are more or less equally fast or slow, with some differences only starting to show at the largest input size. The Java priority queue implementation is noticeably slower than the binary heap at large input sizes albeit clearly faster than the other heaps.

---

__returnMax operation:__

![Return max comparison](https://github.com/maarila/four-heaps/blob/master/img/return-max-linear.png)

The result for the tests of the return max operation are somewhat divided. Binary heap and priority queue returned the data in roughly one millisecond (both shown in the graph as a single blue line), which is consistent with the expectation of them having O(1) time complexity. Pairing heap and Fibonacci heap started showing some delay with large input sizes which was somewhat surprising considering that both only require three constant time operations for returning the maximum value (one condition check, getting the maximum value from the root node, returning said value). 

The binomial heap was clearly the slowest one of all, which is not surprising considering its expected time complexity was O(log n). Starting from a sample size of a hundred thousand, it started trailing the other heaps more and more due to having to go through its entire root list to locate the maximum value.

---

__deleteMax operation:__

![Delete max comparison](https://github.com/maarila/four-heaps/blob/master/img/delete-max-linear.png)

Delete max operation turned out to have rather divided results. Binary heap and priority queue were, once again, clearly the fastest of all implementations, but binomial heap and pairing heap both also fared fairly well. The Fibonacci heap, however, was consistently slow starting from a sample size as low as a hundred thousand. Perhaps this was the result of the Fibonacci heap doing most of its heap manipulation operations after the delete operation?

## Conclusions

As far as the basic heap operations go, binary heap is by far the fastest and most reliable choice. It had the fastest insert operations, a no-delay O(1) return-max and a competitive delete-max operation. The only one that can compete with the binary heap is the Java priority queue implementation - with real differences only showing at large-scale insert operations. The pairing heap was an all-round average performer, with no real weaknesses, but with no strengths, either.

The binomial heap and the Fibonacci heap were more or less underwhelming. The binomial heap is not useful as an all-purpose heap due to the O(log n) return max operation. Fibonacci heap's weakness is the delete max operation which trails all other tested heap structures starting from small input sizes. The case for Fibonacci heap is further weakened by the fact that it is arduous, to say the least, to implement.

## How to reproduce the test results?

The program offers a text-based command line interface that enables the running of all tests with all input sizes used in the project. Thus, reproducing the test results can be achieved simply by running the program and performing any and all desired tests. For further instructions on running the program and the tests, please see [User Guide](https://github.com/maarila/four-heaps/blob/master/documentation/UserGuide.md).
