import java.util.Random;
import java.util.Scanner;

public class SortingComparison_3 {

    // Partition function to rearrange the array around a pivot
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Take the last element as pivot
        int i = (low - 1);  // Index of smaller element

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i + 1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;  // Return the pivot index
    }

    // QuickSort function
    public static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);  // Partitioning index

            // Recursively sort the two subarrays
            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
    }

    // Generate the worst case for quicksort (sorted array)
    public static void generateWorstCase(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
    }

    // Generate the best case for quicksort (randomized array)
    public static void generateBestCase(int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100000) + 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take the size of the array (n) from the user
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        // Best-case scenario: Random array
        int[] bestCaseData = new int[n];
        generateBestCase(bestCaseData);

        // Worst-case scenario: Sorted array
        int[] worstCaseData = new int[n];
        generateWorstCase(worstCaseData);

        // QuickSort time measurement for best-case
        long startTime = System.nanoTime();
        quicksort(bestCaseData, 0, bestCaseData.length - 1);
        long endTime = System.nanoTime();
        long quickSortBestCaseTime = endTime - startTime;

        // QuickSort time measurement for worst-case
        startTime = System.nanoTime();
        quicksort(worstCaseData, 0, worstCaseData.length - 1);
        endTime = System.nanoTime();
        long quickSortWorstCaseTime = endTime - startTime;

        // Display results
        System.out.println("QuickSort time for best-case (randomized) with " + n + " values: " + quickSortBestCaseTime / 1000000 + " milliseconds");
        System.out.println("QuickSort time for worst-case (sorted) with " + n + " values: " + quickSortWorstCaseTime / 1000000 + " milliseconds");

        scanner.close();
    }
}
