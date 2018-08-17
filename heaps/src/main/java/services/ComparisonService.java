package services;

import components.BinaryHeap;
import components.BinomialHeap;

public class ComparisonService {

    public ComparisonService() {
    }

    public void runBinaryHeapInsert() {
        BinaryHeap heap = new BinaryHeap(1);
        long timeAtStart;
        long timeAtEnd;
        long average = 0;

        for (int i = 0; i < 10; i++) {
            timeAtStart = System.currentTimeMillis();
            
            for (int j = 0; j < 1000000; j++) {
                heap.insert(j);
            }
            
            timeAtEnd = System.currentTimeMillis();
            average += (timeAtEnd - timeAtStart);
        }

        System.out.println("Binary heap insert: average " + average / 10 + " milliseconds.");
    }
    
    public void runBinomialHeapInsert() {
        BinomialHeap heap = new BinomialHeap();
        long timeAtStart;
        long timeAtEnd;
        long average = 0;

        for (int i = 0; i < 10; i++) {
            timeAtStart = System.currentTimeMillis();
            
            for (int j = 0; j < 1000000; j++) {
                heap.insert(j);
            }
            
            timeAtEnd = System.currentTimeMillis();
            average += (timeAtEnd - timeAtStart);
        }

        System.out.println("Binomial heap insert: average " + average / 10 + " milliseconds.");
    }
}
