## Report for week 2

Not quite the start I had planned (due to an exam on Wednesday requiring more time than I had anticipated and a surprise time-sink on Thursday), but I did manage to finish the majority of what I had scheduled. In other words, I implemented the binary heap and wrote the tests for it. The rudimentary UI I had envisioned to be completed by now was not yet started.

I felt quite a bit rusty at first not having written Java (nor having used Netbeans) in a while, but luckily it all came back to me rather quickly. First, I did a little pen-and-paper simulation of the functioning of the binary heap, and after that, the actual coding was for the most part rather straight-forward. The exception to the rule was the heapify operation. I chose to implement it iteratively rather than recursively which took me a little while, but its space-complexity is now O(1) rather than O(log n) which it would have been with a recursive implementation.

I also felt a little rusty writing JavaDocs - I think this was the second time ever I have written them. As a consequence I am not yet 100% satisfied with what I ended up, but I will clarify the documentation more once I get a good feel for documenting again. After all this, working on the tests was much more pleasant, albeit I am not quite sure whether I took enough special and edge cases into consideration. But, like with docs, I will return to them if need be.

My original plan is below. I should not have any problems with my time-allocation in the coming week, so catching up to the intended schedule should not pose any problems.

Week 1: done

Week 2: implement binary heap, write tests, create basic UI functionality

Week 3: research binomial heap, begin implementation and testing

Week 4: finish binomial heap, research pairing heap and begin implementation

Week 5: finish implementing and testing pairing heap, research fibonacci heap, begin documentation

Week 6: begin implementing and testing fibonacci heap, continue documentation

Week 7: finish fibonacci heap, finish documentation, return project

__Time spent this week:__

9 hours

__What next:__

First, catch up to the original plan ie. implement a rudimentary UI and begin at the same time thinking about how to test the heaps. After that, return to plan and research the functioning of the binomial heap more closely and begin its implementation.
