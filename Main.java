import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    
    public static void main(String[] args) {

        System.out.println("Welcome to Sort Evaluator");

        String[] files = {
            "data1.txt",
            "data2.txt",
            "data3.txt"
        };

        String[] labels = {
            "1 (random)",
            "2 (ascending)",
            "3 (descending)"
        };

        try (PrintWriter output = new PrintWriter(
                new FileWriter("report.txt"))) {

            output.printf(
                "%-20s %-25s %15s %20s %15s %20s%n",
                "Sort Name",
                "File",
                "Swaps",
                "Comparisons",
                "Loops",
                "NanoSeconds"
            );
            //Used copilot to generate the following line of code
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] data =
                (ArrayList<Integer>[]) new ArrayList<?>[3];

            // Read the three input files.
            for (int i = 0; i < 3; i++) {
                data[i] = readFile(files[i]);
            }

            // BUBBLE SORT
            for (int i = 0; i < 3; i++) {

                ArrayList<Integer> numbers =
                    new ArrayList<>(data[i]);

                SortStats stats =
                    new SortStats("Bubble", labels[i]);

                Sorts.bubbleSort(numbers, stats);

                output.println(stats);

                if (!isSorted(numbers)) {
                    System.out.println("Bubble Sort failed!");
                }
            }

            // INSERTION SORT
            for (int i = 0; i < 3; i++) {

                ArrayList<Integer> numbers =
                    new ArrayList<>(data[i]);

                SortStats stats =
                    new SortStats("Insertion", labels[i]);

                Sorts.insertionSort(numbers, stats);

                output.println(stats);

                if (!isSorted(numbers)) {
                    System.out.println("Insertion Sort failed!");
                }
            }

            // MERGE SORT
            for (int i = 0; i < 3; i++) {

                ArrayList<Integer> numbers =
                    new ArrayList<>(data[i]);

                SortStats stats =
                    new SortStats("MergeSort", labels[i]);

                Sorts.mergeSort(numbers, stats);

                output.println(stats);

                if (!isSorted(numbers)) {
                    System.out.println("MergeSort failed!");
                }
            }

            // QUICK SORT
            for (int i = 0; i < 3; i++) {

                ArrayList<Integer> numbers =
                    new ArrayList<>(data[i]);

                SortStats stats =
                    new SortStats("QuickSort", labels[i]);

                Sorts.quickSort(
                    numbers, 0, numbers.size() - 1, stats);

                output.println(stats);

                if (!isSorted(numbers)) {
                    System.out.println("QuickSort failed!");
                }
            }

            // SELECTION SORT
            for (int i = 0; i < 3; i++) {

                ArrayList<Integer> numbers =
                    new ArrayList<>(data[i]);

                SortStats stats =
                    new SortStats("Selection", labels[i]);

                Sorts.selectionSort(numbers, stats);

                output.println(stats);

                if (!isSorted(numbers)) {
                    System.out.println("Selection Sort failed!");
                }
            }

            System.out.println("All sorting tests completed.");
            System.out.println(
                "Results saved to report.txt");

        } catch (IOException e) {

            System.out.println("File error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println(
                "Invalid number in input file: " + e.getMessage());
        }
    }


    
    public static ArrayList<Integer> readFile(
        String filename) throws IOException {

        ArrayList<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] values = line.split(",");

                for (String value : values) {

                    value = value.trim();

                    if (!value.isEmpty()) {
                        numbers.add(Integer.parseInt(value));
                    }
                }
            }
        }

        return numbers;
    }


    
    public static boolean isSorted(
        ArrayList<Integer> numbers) {

        for (int i = 0; i < numbers.size() - 1; i++) {

            if (numbers.get(i) > numbers.get(i + 1)) {
                return false;
            }
        }

        return true;
    }
}