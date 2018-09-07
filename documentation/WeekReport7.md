## Report for week 7

I began the final week by implementing the rest of the functionality for the Fibonacci heap: increase-key and union. It went strangely well after the difficulties I had with the consolidate function. I am guessing the consolidate function introduced me to the inner workings of the Fibonacci heap to the extent that anything less than that started to seem simple and straight-forward.

The other big thing was creating and running the performance tests. I had had high hopes that one of the other heaps could compete with the binary heap, at least to some extent in some operation, but there was no such luck. The tests, as such, were rather straight-forward to implement and run. Although, and I have no idea why, during the first round of testing, the tests showed priority queue as performing rather poorly. I was somewhat surprised by this and repeated the tests, but always ended up with similar results. Also, the pairing heap did remarkably well in delete-max tests when the input was sequentially ordered. 

Luckily, I remained somewhat sceptical of the results, and after everything was more or less finalised, I decided to run the tests once more. Now, priority queue behaved more as expected, and the pairing heap had also lost its dependence on the order of the input. I am guessing the tests had some weird behaviour which ended up disappearing when I refactored the tests into their final form. Note to self: do not run any kind of tests if they are yet susceptible to change.

The week also included a large variety of smaller tasks: finalising the user interface, going through the code once again and making minor improvements here and there (and also discovering a silly mistake in the binary heap delete-max method), writing and re-writing the JavaDocs, creating an executable jar file and, finally, writing the rest of the documentation.

The week also included the demo event: I was impressed not only by the quality of the other projects but the presentations, as well. I now kind of regret that I did not try my hand in the graph algorithms myself (it might have been a bad idea leading nowhere fast, but still...).

The weekly plan has now come to an end. Tasks for week 7 are completed and so is the project. 

Week 1-6: done

Week 7: do final edits, finish documentation, return project

__Time spent this week:__

13 hours (not including the demo event)

__What next:__

End of project.