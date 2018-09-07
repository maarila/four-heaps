## General structure of the program

The program consists of four implementations of the heap data structure: binary heap, binomial heap, pairing heap and Fibonacci heap. In addition, the program has a rudimentary console-based command line interface for running performance tests on the heap operations of the aforementioned heaps (for further information, see [Testing Report](https://github.com/maarila/four-heaps/blob/master/documentation/TestingReport.md)).

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

Binary heap retains the knowledge of the maximum heap in the first position of an array. Hence, the operation returning said value is a constant time operation which simply returns the key in the binary heap array position 0. Both the pairing heap and the Fibonacci heap track the maximum value in a single root node (known as 'max' node in Fibonacci heap). Therefore the time complexity for returning their maximum value is also constant: check that the root/max node is not null, then get and return the key connected to the root/max node.

Binomial heap differs from the other heaps in that it does not track the maximum value. Instead, binomial heap has a head node, which is one the nodes in the roots which comprise the binomial heap. Finding the maximum value is, thus, an operation which requires going through the entire root list and finding out which of the root nodes has the largest value and then returning said value. As there are O(log n) trees from which to find the maximum value, the time complexity of return-max for binomial heap is O(log n).

__delete-max:__

For the binary heap, deleting, and then replacing, the maximum value consists of constant time operations. The method replaces the maximum key with a value from the furthest leaf and then proceeds to push it down to its correct place via the heapify operation. As the binary heap is in the form of a binary tree, its height is logarithmic with regards to the number of its nodes, and therefore the operation has a worst-case time complexity of O(log n) if the replacing value needs to be taken all the way down to the leaf of the tree.

The binomial heap starts its delete-max operation by finding the current maximum value with an O(log n) operation (as in return-max). The subtrees of the node with the maximum value are then ordered from smallest to largest order and merged into the heap. The worst-case time complexity of the ordering is O(log n) as each of the roots of the subtrees can have log n children.

For the pairing heap, once the root with the maximum value is cut from the heap, its subtrees need to be melded into a new tree. This is accomplished with an operation that goes through all the subtrees and melds two consecutive roots into pairs after which during the second pass the pairs are melded into the last pair that was formed on the first pass. The worst-case time complexity of the pairing heap's delete-max operation, according to Fredman (2005), is O(n) as the number of trees remaining after the deletion of the root node is n. Amortized, the time complexity is O(log n).

The Fibonacci heap, due to the way it handles its insert operation, has the most complex delete-max operation. First, the node with the maximum value is deleted from the root list, its children are then added to the root list, after which the heap is 'consolidated'. Consolidation means that the the root list is iterated through and all nodes with the same degree (i.e. number of children) are linked until all nodes have unique degrees. Then, finally, the new root list is built from the unique nodes. The amortized time complexity of the delete-max operations is O(log n).

__insert:__

Binary heap's insert operation is more or less the opposite of its delete-max operation. The new value is inserted into a new leaf at the bottom of the tree and then 'bubbled-up' potentially all the way up to the top of the tree. As in the delete-max operation, the worst-case time complexity is O(log n) due to the tree's logarithmic height.

The other heaps approach inserting a new value differently. In the case of binomial heap and pairing heap, a new heap is created with the new node as its only member, and the heap is then merged with the existing heap. In the case of the binomial heap, the insert operation is somewhat demanding as the nodes need to be organised in such manner that the heap condition is not broken. The union operation following the insertion takes O(log n) time. For the pairing heap the new heap is either made a subroot of the current root or vice versa with constant time O(1) operations. The approach of the Fibonacci heap is the most straight-forward one: the new node is simply added to the root list of the heap with no re-organising required. The time-complexity of said addition is O(1).

## Performance comparisons

For a detailed look into how the operations compare to each other, please consult the [testing report](https://github.com/maarila/four-heaps/blob/master/documentation/TestingReport.md).

## Things that could be improved

I implemented the increase key method for all heaps, but it did not really lead anywhere - it was just implementing for implementation's sake. Not that is was not useful, per se, but it did not really affect the project in any way. It might have been interesting to implement the storing of the location of the nodes, as well, so that they could have been handily located for the increase key operations, and then test their performance.

Same thing could be said about the merge (or union) operation. Implementing it for all heaps could have shed some more light into the differences between the heaps and their best use-cases. If nothing else, it would have revealed the weaknesses of the binary heap, which otherwise seems to be an automatic choice when implementing a heap.

I became a little sceptical about my test methods after discovering a simple error in the final days of the project. The unit tests are mostly focused on line coverage (which I think is reasonably good), but outside line coverage, I am no longer 100% convinced I managed to test all edge cases - especially when it comes to checking whether the heaps are in their intended shape after some heap operations (usually delete-max).

The UI is not exactly a thing of beauty, but rather falls into the category of 'good enough'. I could have spent a little more time with it - I received some really good suggestions from the code reviews on how it could have been improved.

There was also one practical issue I was not able to resolve: running the tests more than eight million times. My computer simply could not handle larger input sizes except for the binary heap. Although I do not think the results would have drastically changed with a sample size of, say, fifteen or twenty million, if would have been interesting to see how much bigger the differences would have become for the different heaps. At eight million, the differences were only just starting to show clearly.

## Sources

Cormen, T. H., Leiserson, C. E., Rivest, R. L. & Stein C. 2001. _Introduction to Algorithms_. Second Edition. The MIT Press.

Cormen, T. H., Leiserson, C. E., Rivest, R. L. & Stein C. 2009. _Introduction to Algorithms_. Third Edition. The MIT Press.

Fredman, M. L., Sedgeweick, R., Sleator, D. D. & Tarjan, R. E. (1986). The Pairing Heap: A New Form of Self-Adjusting Heap. _Algorithmica_, 1, 111-129.

Fredman, M. L. (2005). Binomial, Fibonacci, and Pairing Heaps. In Mehta, D. P. & Sahni, S. (Eds.), _Handbook of data structures and applications_. Boca Raton, FL, USA: Chapman & Hall/CRC.

Kivinen, J. 2018. _Tietorakenteet ja algoritmit_. Course material.

Wikipedia [1]. 2018. _Binomial heap_. Read on 25.7.2018.

Wikipedia [2]. 2018. _Pairing heap_. Read on 25.7.2018.
