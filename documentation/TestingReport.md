## What was tested and how?

All heaps have the standard heap operations for maximum heaps: insert (implemented as insert(int newKey) for all heaps), return maximum value (implemented as 'returnMax()') and delete maximum value (implemented as 'deleteMax()'). In addition, the heaps also have an operation for increasing the key value (implemented as 'increaseKey(int newKey)').

__Unit testing__

The purpose of the unit tests is two-fold: firstly, to make sure that the functions return values that are correct, and thus, to confirm that the functions behave as intended. Secondly, to make sure that the heap retains its intended shape after the various heap operations.

The tests strive to achieve both things via various heap manipulations. Insertion is confirmed functional by making sure that the new node is not only inserted into the heap, but that it also finds its correct place.

Tests for finding the maximum value stored in the heap are rather straight-forward: since all data is handpicked, confirming the returned maximum value matches the given maximum value is trivial.

The testing of the shape of the heap is achieved by examining the root node (known as 'head' in binomial heap and 'max' in fibonacci heap) and its relationship to its siblings and children. And conversely, the relationship of the children to their parent nodes.

The heap is manipulated by extracting nodes via deleteMax() operation. The tests will not only confirm that the operation returns the correct value, but also deletes said value and recreates, or rather reshapes, the heap correctly in the process.

Increase key operations are confirmed functional by not only making sure that the operations bring new maximum keys to the top of the heap, but also make sure that the heap condition of no node having children with higher key value than their parents is valid.

The nodes are identified through the value of their keys.

__Performance testing__

The operations were tested using integer values and by running the tests with each input size ten times. The end result is the average of the test results.

_insert operation:_



_returnMax operation:_



_deleteMax operation:_


## Conclusions




## How to reproduce the test results?

The program offers a text-based command line interface that enables the running of all tests with all input sizes used in the project. Thus, reproducing the test results can be achieved simply by running the program and performing any and all desired tests. For further instructions on running the program and the tests, please see [User Guide](https://github.com/maarila/four-heaps/blob/master/documentation/UserGuide.md).
