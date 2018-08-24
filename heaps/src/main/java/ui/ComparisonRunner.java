package ui;

import services.ComparisonService;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

public class ComparisonRunner {

    private final Scanner inputReader;
    private final ComparisonService comparisonService;
    private Map<String, String> commands;

    public ComparisonRunner(Scanner inputReader, ComparisonService comparisonService) {
        this.inputReader = inputReader;
        this.comparisonService = comparisonService;

        commands = new TreeMap<>();

        commands.put("1", "Run timed tests for insert operation");
        commands.put("2", "Run timed tests for binary heap's insert operation");
        commands.put("3", "Run timed tests for binomial heap's insert operation");
        commands.put("4", "Run timed tests for pairing heap's insert operation");
        commands.put("5", "Run timed tests for fibonacci heap's insert operation");
        commands.put("q", "Quit");
    }

    public void start() {
        System.out.println();
        System.out.println("Welcome to comparing four heaps!");
        System.out.println();

        commandLoop:
        while (true) {
            showOptions();
            System.out.println();
            System.out.println("Enter command: ");
            String command = inputReader.nextLine();

            if (!commands.keySet().contains(command)) {
                System.out.println();
                System.out.println("Unknown command!");
                System.out.println();
            }

            switch (command) {
                case "1":
                    runInsertTests();
                    break;
                case "2":
                    runBinaryHeapInsertTests();
                    break;
                case "3":
                    runBinomialHeapInsertTests();
                    break;
                case "4":
                    runPairingHeapInsertTests();
                    break;
                case "5":
                    runFibonacciHeapInsertTests();
                    break;
                case "q":
                    break commandLoop;
            }
        }
    }

    public void showOptions() {
        commands.entrySet().forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue()));

    }

    public void runInsertTests() {
        System.out.println();
        System.out.println("Running insert tests...");
        System.out.println();
        comparisonService.runBinaryHeapInsert();
        comparisonService.runBinomialHeapInsert();
        comparisonService.runPairingHeapInsert();
        comparisonService.runFibonacciHeapInsert();
        System.out.println();
        System.out.println("Tests completed!");
    }

    public void runBinaryHeapInsertTests() {
        System.out.println();
        System.out.println("Running binary heap insert tests...");
        System.out.println();
        comparisonService.runBinaryHeapInsert();
        System.out.println();
        System.out.println("Tests completed!");
    }

    public void runBinomialHeapInsertTests() {
        System.out.println();
        System.out.println("Running binomial heap insert tests...");
        System.out.println();
        comparisonService.runBinomialHeapInsert();
        System.out.println();
        System.out.println("Tests completed!");
    }

    public void runPairingHeapInsertTests() {
        System.out.println();
        System.out.println("Running pairing heap insert tests...");
        System.out.println();
        comparisonService.runPairingHeapInsert();
        System.out.println();
        System.out.println("Tests completed!");
    }

    public void runFibonacciHeapInsertTests() {
        System.out.println();
        System.out.println("Running fibonacci heap insert tests...");
        System.out.println();
        comparisonService.runFibonacciHeapInsert();
        System.out.println();
        System.out.println("Tests completed!");
    }
}
