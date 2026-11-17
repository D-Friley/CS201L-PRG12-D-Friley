import java.util.ArrayList;
import java.util.Collections;
//Starter code copied from Lecture 13 repository
public class Sorts {

    
    public static void selectionSort(
        ArrayList<Integer> numbers, SortStats stats) {

        long startTime = System.nanoTime();

        int i;
        int j;
        int indexSmallest;
        int temp;

        for (i = 0; i < numbers.size(); ++i) {

            stats.loops++;

            indexSmallest = i;

            for (j = i + 1; j < numbers.size(); ++j) {

                stats.loops++;
                stats.comparisons++;

                if (numbers.get(j) < numbers.get(indexSmallest)) {
                    indexSmallest = j;
                }
            }

            
            temp = numbers.get(i);
            numbers.set(i, numbers.get(indexSmallest));
            numbers.set(indexSmallest, temp);

            stats.swaps++;
        }

        stats.timeNano = System.nanoTime() - startTime;
    }


    
    public static void insertionSort(
        ArrayList<Integer> numbers, SortStats stats) {

        long startTime = System.nanoTime();

        int i;
        int j;
        int temp;

        for (i = 1; i < numbers.size(); ++i) {

            stats.loops++;

            j = i;

            while (j > 0 && numbers.get(j) < numbers.get(j - 1)) {

                stats.comparisons++;

                temp = numbers.get(j - 1);
                numbers.set(j - 1, numbers.get(j));
                numbers.set(j, temp);

                stats.swaps++;
                stats.loops++;

                j--;
            }
        }

        stats.timeNano = System.nanoTime() - startTime;
    }


    
    public static void bubbleSort(
        ArrayList<Integer> numbers, SortStats stats) {

        long startTime = System.nanoTime();

        int i;
        int j;

        for (i = numbers.size() - 1; i > 0; --i) {


            for (j = 0; j < i; j++) {

                stats.loops++;
                stats.comparisons++;

                if (numbers.get(j) > numbers.get(j + 1)) {

                    Collections.swap(numbers, j, j + 1);

                    stats.swaps++;
                }
            }
        }

        stats.timeNano = System.nanoTime() - startTime;
    }


    
    public static void mergeSort(
        ArrayList<Integer> numbers, SortStats stats) {

        long startTime = System.nanoTime();

        ArrayList<Integer> sorted =
            mergeSortRecursive(numbers, stats);

        numbers.clear();
        numbers.addAll(sorted);

        stats.timeNano = System.nanoTime() - startTime;
    }


    
    private static ArrayList<Integer> mergeSortRecursive(
        ArrayList<Integer> numbers, SortStats stats) {

        if (numbers.size() <= 1) {
            return numbers;
        }

        int mid = numbers.size() / 2;

        ArrayList<Integer> left =
            new ArrayList<>(numbers.subList(0, mid));

        ArrayList<Integer> right =
            new ArrayList<>(
                numbers.subList(mid, numbers.size()));

        left = mergeSortRecursive(left, stats);
        right = mergeSortRecursive(right, stats);

        return merge(left, right, stats);
    }


    
    private static ArrayList<Integer> merge(
        ArrayList<Integer> left,
        ArrayList<Integer> right,
        SortStats stats) {

        ArrayList<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {

            stats.loops++;
            stats.comparisons++;

            if (left.get(i) <= right.get(j)) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {

            stats.loops++;

            result.add(left.get(i));
            i++;
        }

        while (j < right.size()) {

            stats.loops++;

            result.add(right.get(j));
            j++;
        }

        return result;
    }


    
    public static void quickSort(
        ArrayList<Integer> numbers,
        int low,
        int high,
        SortStats stats) {

        long startTime = System.nanoTime();

        quickSortRecursive(numbers, low, high, stats);

        stats.timeNano = System.nanoTime() - startTime;
    }


    
    private static void quickSortRecursive(
        ArrayList<Integer> numbers,
        int low,
        int high,
        SortStats stats) {

        if (low < high) {

            int pi = partition(numbers, low, high, stats);

            quickSortRecursive(numbers, low, pi - 1, stats);
            quickSortRecursive(numbers, pi + 1, high, stats);
        }
    }


    
    private static int partition(
        ArrayList<Integer> list,
        int low,
        int high,
        SortStats stats) {

        int pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {

            stats.loops++;
            stats.comparisons++;

            if (list.get(j) <= pivot) {

                i++;

                
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);

                stats.swaps++;
            }
        }

        
        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        stats.swaps++;

        return i + 1;
    }
}