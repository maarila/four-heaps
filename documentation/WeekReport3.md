## Report for week 3

I started the week by building the basic structure of the UI for the software. The UI does nothing yet, but once I start implementing performance testing (which probably happens after I finish the binomial heap) the UI should prove easy to extend. After the UI, I started researching how binomial heaps are supposed to work. The Wikipedia article on binomial heaps turned out, in the end, to be rather lacking, but fortunately the second edition of Cormen's Introduction to Algorithms had a much more extensive description of the basics of binomial heap. (I updated the Project Definition document to note this.)

Regardless, it took me quite a while and also lots of pen-and-paper simulating to come to grips with how it is supposed to work. I was especially confused by the union operation (containing the merge operation). My goal was to first understand all of it to the best of my ability before starting any coding, but, in hindsight, I am not quite sure whether that was the best strategy. 'Sketching' the operating of the binomial heap on Netbeans might have helped. I will probably try it that way for the next heap.

Despite the pen-and-paper work, I spent a lot of time trying to debug the workings of the binomial heap. Luckily, only after a couple of hours I remembered Matti Luukkainen always saying "the bug is always in the place you are sure is 100% correct". It turned out to be true once again, as I found the bug in my merge function which I thought was a thing of beauty. Now it is no longer a thing of beauty, but at least it works. Or so I like to think. Due to the excessive time spent debugging, I have not yet started writing tests for binomial heap. Not that I was planning on finishing the heap this week, but I was hoping it had been a little further along.

A couple of things to note: I tried to make sense of the operating of the binomial heap with naming - it is perhaps overly verbose at places, but at least it is so by design... Also, like I mentioned, tests for the binomial heap are still missing - they are next on the agenda. I am fully expecting that the implementation of the binomial heap will still improve along with the tests - I have so far only been able to test it manually and I am not entirely convinced I have been thorough enough. This is especially true for the increaseKey method which, as it is, is next to useless with no way of getting the node that has the key to be increased (except for the head).

One question: Should the UI also be tested?

Current, updated plan is below. The third week went more or less as planned, even though testing has not yet begun.

Week 1: done
Week 2: done

Week 3: research binomial heap, begin implementation and testing

Week 4: finish binomial heap, research pairing heap and begin implementation

Week 5: finish implementing and testing pairing heap, research fibonacci heap, begin documentation

Week 6: begin implementing and testing fibonacci heap, continue documentation

Week 7: finish fibonacci heap, finish documentation, return project

__Time spent this week:__

11 hours

__What next:__

First, finish the work on binomial heap and then begin the work on pairing heap. Even though it is not mentioned in the plan, I think this week would also be a good time to start doing work on the actual testing and comparing of the heaps. I will probably try to create some kind of a blueprint for the tests and see where it takes me. I also need to decide what to do with the indexing of both binary and binomial heap for the increase and decrease key methods.
