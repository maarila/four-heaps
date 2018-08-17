## Report for week 4

First of all, the implementation of the pairing heap is not yet complete. In fact, quite the opposite. There is a mistake in there somewhere, and I need to track it down. Everything thus far implemented seems to work fine, but trying to implement the increase key operation led to some rather unsolvable kinds of problems - with the current structure, that is.  

My current assumption is that I implemented the pairing heap too much like the binomial heap, and I should rather have implemented it as a doubly-linked list, but that is yet to be tested as this week's schedule required some focus (and time) on working on the documentation as well. Also, my own plan did not expect pairing heap to be yet completed, so all hope is not lost...

What I did finish this week was the work that was left on binomial heap from last week. I also began the work on actually testing the efficiency of the various heaps. I only created a very basic structure of the testing procedure so far and only did the comparison work for one function and two heap implementations, but expanding that should not require that much work anymore. 

Also, did something new this week. The melding and linking operations of the pairing heap (similar to the merge from binomial heap) were once again a little confusing at times, but since I knew (or at least assumed that I knew) how it was supposed to work, I began writing tests for them before they were finished. Workflow became 'write some aspect of the method, write tests for said aspect -> when successful rinse and repeat'. It worked better than I initially thought and reduced frustration quite a bit. I wish I had come up with this while implementing binomial heap, but at least I now have something to look forward to regarding fibonacci heap... After the tests were complete, I felt pretty comfortable refactoring the meld method into something more readable knowing that the tests would make sure I do not break it.

Updated plan, as usual, is below. The fourth week went more or less according to plan schedule-wise, and provided that I find the problem in the immediate near future, I might even be ahead of schedule a little bit.

Week 1: done

Week 2: done

Week 3: done

Week 4: finish binomial heap, research pairing heap and begin implementation, begin documentation

Week 5: finish implementing and testing pairing heap, research fibonacci heap, continue documentation

Week 6: begin implementing and testing fibonacci heap, continue documentation

Week 7: finish fibonacci heap, finish documentation, return project

__Time spent this week:__

14 hours

__What next:__

Finish the work on pairing heap: track down the problem it currently has, and then finish the work on both its tests and JavaDocs. After that, start the work on fibonacci heap.
