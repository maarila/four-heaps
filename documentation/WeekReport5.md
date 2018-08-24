## Report for week 5

At the beginning of the week I finished, or rather fixed, the implementation of the pairing heap. As I suspected, I had implemented the pairing node wrong - and now I implemented it correctly. In other words, three of the four heaps are now complete, but the fourth one, fibonacci heap, is still in the early phases. Two of the three basic heap operations already work, insert and return maximum (trivial as it is), but deleting maximum (as well as increase key) is still far from finished. This is mostly due to the fact that the core of the fibonacci heap, the consolidate function, is giving me little problems - and something of a headache. I have yet to figure it out. Tests for the completed portions are already made, and so are the JavaDocs.

As a positive sidenote (which I have mentioned in the previous weekly reports as well): one lesson definitely learned so far in this project is the role of testing in implementing tricky methods. Tests enable me to actually trust the code I have created, which is infinitely better than the old "I sure hope it works" approach.

I am a little bit behind on my documentation (project and testing report, mainly), but that is not something I am majorly concerned over. They will be easier to write in one go rather than try building them up little by little. Also, the copy-paste performance tests in the code at the moment are there just so I have been able to confirm that my computer does not explode from any of the heaps...

My plan originally called for finishing the implementation of the fibonacci heap on week 7, but at the moment that does not seem like a wise plan anymore. I need to finish fibonacci heap, and consequently the majority of this project, in the upcoming week, and only do fine tuning and adding final touches on the final week. Otherwise, I will run into some major scheduling problems once the autumn study period begins.

Updated plan is, once again, below. The fifth week went according to plan, but I am a little bit worried about the fibonacci heap, since at the moment I am still quite lost with the consolidate function. Fortunately, I have no other major plans for next week, so I should have plenty of time to make sense of it.

Week 1-4: done

Week 5: finish implementing and testing pairing heap, research fibonacci heap, continue documentation

Week 6: finish implementing and testing fibonacci heap, continue documentation

Week 7: do final edits, finish documentation, return project

__Time spent this week:__

16 hours

__What next:__

Finish the work on the fibonacci heap: make sense of the consolidate function and implement it. Also, the performance tests need to be 99% (or more) ready after next week - and documentation as well. Final week should only consist of minor tasks. (Also: do a second code review.)
