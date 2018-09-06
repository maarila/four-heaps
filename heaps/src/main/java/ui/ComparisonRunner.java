package ui;

import services.ComparisonService;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

public class ComparisonRunner {

    private final Scanner inputReader;
    private final ComparisonService comparisonService;
    private int numberOfTimes;
    private Map<String, String> commands;

    public ComparisonRunner(Scanner inputReader, ComparisonService comparisonService) {
        this.inputReader = inputReader;
        this.comparisonService = comparisonService;
        this.numberOfTimes = 100000;

        commands = new TreeMap<>();

        commands.put("1", "Run performance tests for insert");
        commands.put("2", "Run performance tests for return-max");
        commands.put("3", "Run performance tests for delete-max");
        commands.put("4", "Run all performance tests");
        commands.put("q", "Quit");
    }

    public void start() {
        System.out.println();
        System.out.println("Welcome to comparing four heaps!");
        System.out.println();

        while (true) {
            showOptions();
            System.out.println();
            System.out.println("Enter command: ");
            String command = inputReader.nextLine();

            if (command.equals("q")) {
                break;
            }

            if (!commands.keySet().contains(command)) {
                System.out.println();
                System.out.println("Unknown command!");
                System.out.println();
                continue;
            }

            try {
                System.out.println("Please enter the number of operations to run:");
                numberOfTimes = Integer.parseInt(inputReader.nextLine());
                System.out.println();
            } catch (Exception e) {
                System.out.println();
                System.out.println("Please enter a valid number.");
                System.out.println();
                continue;
            }

            switch (command) {
                case "1":
                    System.out.println("Running insert tests...");
                    System.out.println();
                    runInsertTests(numberOfTimes);
                    System.out.println();
                    System.out.println("Tests completed!");
                    System.out.println();
                    break;
                case "2":
                    System.out.println("Running return-max tests...");
                    System.out.println();
                    runReturnMaxTests(numberOfTimes);
                    System.out.println();
                    System.out.println("Tests completed!");
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Running delete-max tests...");
                    System.out.println();
                    runDeleteMaxTests(numberOfTimes);
                    System.out.println();
                    System.out.println("Tests completed!");
                    System.out.println();
                    break;
                case "4":
                    System.out.println("Running all tests...");
                    System.out.println();
                    runInsertTests(numberOfTimes);
                    System.out.println();
                    runReturnMaxTests(numberOfTimes);
                    System.out.println();
                    runDeleteMaxTests(numberOfTimes);
                    System.out.println();
                    System.out.println("Tests completed!");
                    System.out.println();
                    break;
            }
        }
    }

    public void showOptions() {
        commands.entrySet().forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue()));

    }

    public void runInsertTests(int numberOfTimes) {
        comparisonService.runInsertTests(numberOfTimes);
    }

    public void runReturnMaxTests(int numberOfTimes) {
        comparisonService.runReturnMaxTests(numberOfTimes);
    }

    public void runDeleteMaxTests(int numberOfTimes) {
        comparisonService.runDeleteMaxTests(numberOfTimes);
    }
}
