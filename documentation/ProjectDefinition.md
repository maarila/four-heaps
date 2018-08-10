## Project Definition

This project is about comparing the backbone of, for instance, various graph algorithms, such as Dijkstra's algorithm. In other words: the heap datastructure. The goal is to find out the differences in efficiency in various heap operations, and to clarify the usefulness - i.e. discover the advantages and disadvantages - of different heaps in different situations. As the heap data structure is central in the efficient working of graph algorithms, it seems prudent to find out which version of the heap is useful where - and whether the differences are actually significant enough to warrant different implementations in different situations.

The program will be given integers as inputs, and it will then calculate the time the operations take given different input sizes.

The selected heaps are:

__Binary heap__

* find-min: O(1), other operations: O(log n)
* commonly used data structure
* will act as a baseline when comparing other heaps

__Binomial heap__

* insert: O(1), others: O(log n)
* focus on insert operations

__Pairing heap__

* find-min and insert: O(1), others: O(log n)
* combines the best sides of binary and binomial heap?
* should act as a prelude to the implementation of the actual Fibonacci heap
* "the performance in practice is excellent" (Wikipedia [2], 2018).

__Fibonacci heap__

* delete-min: O(log n), others: O(1)
* theoretically the best of all heaps, but what about in practice?

The time complexities given above are all based on the summary of running times found on Wikipedia ([1], 2018).

### Sources:

Cormen, T. H., Leiserson, C. E., Rivest, R. L. & Stein C. 2001. Introduction to Algorithms. Second Edition. The MIT Press.

Cormen, T. H., Leiserson, C. E., Rivest, R. L. & Stein C. 2009. Introduction to Algorithms. Third Edition. The MIT Press.

Kivinen, J. 2018. Tietorakenteet ja algoritmit. Course material.

Wikipedia [1]. 2018. Binomial heap. Read on 25.7.2018.

Wikipedia [2]. 2018. Pairing heap. Read on 25.7.2018.

