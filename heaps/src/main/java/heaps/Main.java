package heaps;

import java.util.Scanner;
import ui.ComparisonRunner;
import services.ComparisonService;

public class Main {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        ComparisonService comparisonService = new ComparisonService();

        ComparisonRunner comparisonRunner = new ComparisonRunner(inputReader, comparisonService);
        comparisonRunner.start();
    }
}
