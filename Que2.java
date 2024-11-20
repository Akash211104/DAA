import java.util.*;

/* 
PART A: Implement QuickSort using divide and conquer strategy.
PART B: Compare its time required wrt Merge sort OR Randomized Quick Sort
*/

// Harshada Borse
public class Que2 {
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, p - 1);
            randomizedQuickSort(arr, p + 1, high);
        }
    }

    public static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomIndex = low + rand.nextInt(high - low + 1); // Generate random pivot index
        swap(arr, randomIndex, high); // Swap pivot with the last element
        return partition(arr, low, high);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Pivot is the last element
        int i = low - 1; // Pointer for the smaller elements

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high); // Place pivot in the correct position
        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println("------------------- Randomized Quick Sort -------------------");
        int arr[] = { 12, 11, 13, 5, 6, 7 };

        System.out.println("Original array:");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Sorting the array
        randomizedQuickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}
