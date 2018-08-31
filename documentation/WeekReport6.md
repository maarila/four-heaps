## Report for week 6

A rather underwhelming week. I did the code review portion at the beginning of the week - it was again useful seeing how others approach their particular projects and how they reason with their code.

After that I wrestled with the Fibonacci heap. In the end, I finally managed to get the consolidate function working. After trying to make sense of what I had done wrong with it - for quite a while, I might add - I managed to locate the error to a silly mistake I had made while implementing the insertion method. Inserting the first node turned out to not be quite the circular linked list it was supposed to be... That then led to finding further errors in the deleteMax method which uses the consolidate method. It was something of a mess. I had even made tests that highlighted the insertion mistake, but had not realised it while writing them. All is fixed now, except for the shame.

Otherwise, progress was focused on the documenting side of things. Testing report is now missing only the performance tests, which I am yet to do. UI is still as it was.

Below is what is remaining of the plan. I am actually a little bit behind schedule at the moment, not having yet finished the actual performance tests, but now that the Fibonacci heap is finally functioning (the basic functionality is, at least), the final work does not seem too arduous and should be completed over the coming weekend.

Week 1-5: done

Week 6: finish implementing and testing fibonacci heap, continue documentation

Week 7: do final edits, finish documentation, return project

__Time spent this week:__

15 hours

__What next:__

Finish implementing the UI, run the performance tests and write the testing report on their part, then finish the project report. And then, the project is more or less over.
