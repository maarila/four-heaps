package services;

import components.BinaryHeap;
import components.BinomialHeap;
import components.FibonacciHeap;
import components.Heap;
import components.PairingHeap;
import java.util.PriorityQueue;

public class ComparisonService {

    long timesEachTestRun;
    long timeAtStart;
    long timeAtEnd;
    long totalTime;

    public ComparisonService() {
        this.timesEachTestRun = 10;
    }

    public void runInsertTests(int numberOfTimes) {
        binaryHeapInsert(numberOfTimes);
        binomialHeapInsert(numberOfTimes);
        pairingHeapInsert(numberOfTimes);
        fibonacciHeapInsert(numberOfTimes);
        priorityQueueInsert(numberOfTimes);
    }

    public void runReturnMaxTests(int numberOfTimes) {
        binaryHeapReturnMax(numberOfTimes);
        binomialHeapReturnMax(numberOfTimes);
        pairingHeapReturnMax(numberOfTimes);
        fibonacciHeapReturnMax(numberOfTimes);
        priorityQueueReturnMax(numberOfTimes);
    }

    public void runDeleteMaxTests(int numberOfTimes) {
        binaryHeapDeleteMax(numberOfTimes);
        binomialHeapDeleteMax(numberOfTimes);
        pairingHeapDeleteMax(numberOfTimes);
        fibonacciHeapDeleteMax(numberOfTimes);
        priorityQueueDeleteMax(numberOfTimes);
    }

    public void binaryHeapInsert(int numberOfTimes) {
        BinaryHeap binaryHeap = new BinaryHeap(16);
        long average = insert(binaryHeap, numberOfTimes);
        System.out.println("Binary heap insert: average " + average + " milliseconds.");
    }

    public void binomialHeapInsert(int numberOfTimes) {
        BinomialHeap binomialHeap = new BinomialHeap();
        long average = insert(binomialHeap, numberOfTimes);
        System.out.println("Binomial heap insert: average " + average + " milliseconds.");
    }

    public void pairingHeapInsert(int numberOfTimes) {
        PairingHeap pairingHeap = new PairingHeap();
        long average = insert(pairingHeap, numberOfTimes);
        System.out.println("Pairing heap insert: average " + average + " milliseconds.");
    }

    public void fibonacciHeapInsert(int numberOfTimes) {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        long average = insert(fibonacciHeap, numberOfTimes);
        System.out.println("Fibonacci heap insert: average " + average + " milliseconds.");
    }

    public void binaryHeapReturnMax(int numberOfTimes) {
        BinaryHeap binaryHeap = new BinaryHeap(16);
        long average = returnMax(binaryHeap, numberOfTimes);
        System.out.println("Binary heap return-max: average " + average + " milliseconds.");
    }

    public void binomialHeapReturnMax(int numberOfTimes) {
        BinomialHeap binomialHeap = new BinomialHeap();
        long average = returnMax(binomialHeap, numberOfTimes);
        System.out.println("Binomial heap return-max: average " + average + " milliseconds.");
    }

    public void pairingHeapReturnMax(int numberOfTimes) {
        PairingHeap pairingHeap = new PairingHeap();
        long average = returnMax(pairingHeap, numberOfTimes);
        System.out.println("Pairing heap return-max: average " + average + " milliseconds.");
    }

    public void fibonacciHeapReturnMax(int numberOfTimes) {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        long average = returnMax(fibonacciHeap, numberOfTimes);
        System.out.println("Fibonacci heap return-max: average " + average + " milliseconds.");
    }

    public void binaryHeapDeleteMax(int numberOfTimes) {
        BinaryHeap binaryHeap = new BinaryHeap(16);
        long average = deleteMax(binaryHeap, numberOfTimes);
        System.out.println("Binary heap delete-max: average " + average + " milliseconds.");
    }

    public void binomialHeapDeleteMax(int numberOfTimes) {
        BinomialHeap binomialHeap = new BinomialHeap();
        long average = deleteMax(binomialHeap, numberOfTimes);
        System.out.println("Binomial heap delete-max: average " + average + " milliseconds.");
    }

    public void pairingHeapDeleteMax(int numberOfTimes) {
        PairingHeap pairingHeap = new PairingHeap();
        long average = deleteMax(pairingHeap, numberOfTimes);
        System.out.println("Pairing heap delete-max: average " + average + " milliseconds.");
    }

    public void fibonacciHeapDeleteMax(int numberOfTimes) {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        long average = deleteMax(fibonacciHeap, numberOfTimes);
        System.out.println("Fibonacci heap delete-max: average " + average + " milliseconds.");
    }

    public long insert(Heap heap, int numberOfTimesRun) {
        totalTime = 0;

        for (int i = 0; i < timesEachTestRun; i++) {
            timeAtStart = System.currentTimeMillis();

            for (int j = 0; j < numberOfTimesRun; j++) {
                heap.insert(j);
            }

            timeAtEnd = System.currentTimeMillis();
            totalTime += (timeAtEnd - timeAtStart);
        }

        return totalTime / timesEachTestRun;
    }

    public void priorityQueueInsert(int numberOfTimesRun) {
        PriorityQueue queue = new PriorityQueue();
        totalTime = 0;

        for (int i = 0; i < timesEachTestRun; i++) {
            timeAtStart = System.currentTimeMillis();

            for (int j = 0; j < numberOfTimesRun; j++) {
                queue.add(j);
            }

            timeAtEnd = System.currentTimeMillis();
            totalTime += (timeAtEnd - timeAtStart);
        }

        System.out.println("Priority queue insert: average " + totalTime / timesEachTestRun + " milliseconds.");
    }

    public long returnMax(Heap heap, int numberOfTimesRun) {
        totalTime = 0;

        for (int i = 0; i < numberOfTimesRun; i++) {
            heap.insert(i);
        }

        for (int i = 0; i < timesEachTestRun; i++) {
            timeAtStart = System.currentTimeMillis();

            for (int j = 0; j < numberOfTimesRun; j++) {
                heap.returnMax();
            }

            timeAtEnd = System.currentTimeMillis();
            totalTime += (timeAtEnd - timeAtStart);
        }

        return totalTime / timesEachTestRun;
    }

    public void priorityQueueReturnMax(int numberOfTimesRun) {
        PriorityQueue queue = new PriorityQueue();
        totalTime = 0;

        for (int i = 0; i < numberOfTimesRun; i++) {
            queue.add(i);
        }

        for (int i = 0; i < timesEachTestRun; i++) {
            timeAtStart = System.currentTimeMillis();

            for (int j = 0; j < numberOfTimesRun; j++) {
                queue.peek();
            }

            timeAtEnd = System.currentTimeMillis();
            totalTime += (timeAtEnd - timeAtStart);
        }

        System.out.println("Priority queue return-max: average " + totalTime / timesEachTestRun + " milliseconds.");
    }

    public long deleteMax(Heap heap, int numberOfTimesRun) {
        totalTime = 0;

        for (int i = 0; i < timesEachTestRun; i++) {

            for (int j = 0; j < numberOfTimesRun; j++) {
                heap.insert(i);
            }

            timeAtStart = System.currentTimeMillis();

            for (int k = 0; k < numberOfTimesRun; k++) {
                heap.deleteMax();
            }

            timeAtEnd = System.currentTimeMillis();
            totalTime += (timeAtEnd - timeAtStart);
        }

        return totalTime / timesEachTestRun;
    }

    public void priorityQueueDeleteMax(int numberOfTimesRun) {
        PriorityQueue queue = new PriorityQueue();
        totalTime = 0;

        for (int i = 0; i < numberOfTimesRun; i++) {
            queue.add(i);
        }

        for (int i = 0; i < timesEachTestRun; i++) {
            timeAtStart = System.currentTimeMillis();

            for (int j = 0; j < numberOfTimesRun; j++) {
                queue.poll();
            }

            timeAtEnd = System.currentTimeMillis();
            totalTime += (timeAtEnd - timeAtStart);
        }

        System.out.println("Priority queue delete-max: average " + totalTime / timesEachTestRun + " milliseconds.");
    }
}
