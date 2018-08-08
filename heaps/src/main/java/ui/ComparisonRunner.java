
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
        
        commands.put("1", "Run timed tests for binary heap's insert operation");
        commands.put("2", "More to come...");
        commands.put("q", "Quit");
    }
    
    public void start() {
        System.out.println();
        System.out.println("Welcome to comparing four heaps!");
        System.out.println();
        showOptions();
        
        commandLoop: while (true) {
            System.out.println();
            System.out.println("Enter command: ");
            String command = inputReader.nextLine();
            
            if (!commands.keySet().contains(command)) {
                System.out.println();
                System.out.println("Unknown command!");
                System.out.println();
                showOptions();
            }
            
            switch (command) {
                case "1":
                    runBinaryHeapInsert();
                    break;
                case "2":
                    System.out.println();
                    System.out.println("Nothing too see here.");
                    System.out.println();
                    break;
                case "q":
                    break commandLoop;
            }
        }
    }

    public void showOptions() {
        commands.entrySet().forEach(entry -> System.out.println(entry.getKey() + "\t" + entry.getValue()));

    }
    
    public void runBinaryHeapInsert() {
        System.out.println();
        System.out.println("Hello heaps!");
    }
    
}
