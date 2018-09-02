## General structure of the program

The program consists of four implementations of the heap data structure: binary heap, binomial heap, pairing heap and fibonacci heap. In addition, the program has a rudimentary console-based command line interface for running performance tests on the heap operations of the aforementioned heaps (for further information, see [Testing Report](https://github.com/maarila/four-heaps/blob/master/documentation/TestingReport.md)).

All the heap implementations are maximum heaps and they have the standard heap operations:

* heap-max(H): return the maximum key value in heap _H_
* heap-del-max(H): delete and return the maximum key value in heap _H_
* heap-insert(H, k): add key _k_ into heap _H_.

The heaps also have a selection of helper methods as required by the above operations.

## Time complexities

The time complexities of the four heaps are summarized in Wikipedia (2018, [1]) as follows (time complexities marked with an asterisk are amortized):

| Operation  | Binary   | Binomial | Pairing   | Fibonacci |
|----------- |--------- |--------- |---------- |---------- |
| return-max | O(1)     | O(log n) | O(1)      | O(1)      |
| delete-max | O(log n) | O(log n) | O(log n)* | O(log n)* |
| insert     | O(log n) | O(1)*    | O(1)      | O(1)      |

__return-max:__

Binary heap retains the knowledge of the maximum heap in the first position of an array. Hence, the operation returning said value is a constant time operation which simply returns the key in the binary heap array position 0. Both the pairing heap and the fibonacci heap track the maximum value in a single root node (known as 'max' node in fibonacci heap). Therefore the time complexity for returning their maximum value is also constant: return the key connected to the root/max node.

Binomial heap differs from the other heaps in that it does not track the maximum value. Instead, binomial heap has a head node, which is one the nodes in the roots which comprise the binomial heap. Finding the maximum value is, thusly, an operation which requires going through the entire root list and finding out which of the root nodes has the largest value and then returning said value. As there are O(log n) trees from which to find the maximum value, the time complexity of return-max for binomial heap is O(log n).

__delete-max:__

For the binary heap, deleting, and then replacing, the maximum value consists of constant time operations. The method replaces the maximum key with a value from the furthest leaf and then proceeds to push it down to its correct place via the heapify operation. As the binary heap is in the form of a binary tree, its height is logarithmic in regards to the number of its nodes, and therefore the operation has a worst-case time complexity of O(log n), if the replacing value needs to be taken all the way down to the leaf of the tree.

(TODO: finish writing.)

__insert:__

Binary heaps insert operation is more or less the opposite of its delete-max operation. The new value is inserted into a new leaf at the bottom of the tree and the 'bubbled-up' potentially all the way up to the top of the tree. As in the delete-max operation, the worst-case time complexity is O(log n) due to the trees logarithmic height.

(TODO: finish writing.)

## Performance comparisons

For a detailed look into how the operations compare to each other, please consult the [testing report](https://github.com/maarila/four-heaps/blob/master/documentation/TestingReport.md).

## Things that could be improved

I implemented the increase key method for all heaps, but it did not really lead anywhere - it was just implementing for implementation's sake. Not that is was not useful, per se, but it did not really affect the project in any way. It might have been interesting to implement the storing of the location of the nodes, as well, so that they can be handily located for the increase key operations, and then test their performance.

Same thing could be said about the merge (or union) operation I implemented for the Fibonacci heap. Implementing it for the other heaps as well could have shed some more light into the differences between the heaps and their best use-cases.

The unit tests are mostly focused on line coverage (which I think is reasonably good), but outside line coverage, I am not entirely convinced I managed to test all edge cases - especially when it comes to checking whether the heaps are in their intended shape after some heap operations (usually delete max). The might check them well enough, but like I said, I am not a hundred-percent certain of that.

The UI is not exactly a thing of beauty, but rather falls into the category of 'good enough'. I could have spent a little more time with it - I got some really good suggestions from the code review on how it could be improved.

There was also one practical issue I was not able to resolve: running the tests more than eight million times. My computer simply could not handle larger input sizes except for the binary heap. Although I do not think the results would have drastically changed with a sample size of, say, fifteen or twenty million, if would have been interesting to see how much bigger the differences would have become for the different heaps. At eight million, the differences were just starting to show clearly.

## Sources

Cormen, T. H., Leiserson, C. E., Rivest, R. L. & Stein C. 2001. _Introduction to Algorithms_. Second Edition. The MIT Press.

Cormen, T. H., Leiserson, C. E., Rivest, R. L. & Stein C. 2009. _Introduction to Algorithms_. Third Edition. The MIT Press.

Fredman, M. L., Sedgeweick, R., Sleator, D. D. & Tarjan, R. E. (1986). The Pairing Heap: A New Form of Self-Adjusting Heap. _Algorithmica_, 1, 111-129.

Fredman, M. L. (2005). Binomial, Fibonacci, and Pairing Heaps. In Mehta, D. P. & Sahni, S. (Eds.), _Handbook of data structures and applications_. Boca Raton, FL, USA: Chapman & Hall/CRC.

Kivinen, J. 2018. _Tietorakenteet ja algoritmit_. Course material.

Wikipedia [1]. 2018. _Binomial heap_. Read on 25.7.2018.

Wikipedia [2]. 2018. _Pairing heap_. Read on 25.7.2018.
