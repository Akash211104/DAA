import java.util.Random;
import java.util.Scanner;

public class SortingComparison {

    // Quicksort Algorithm
    public static int[] quicksort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int pivot = arr[arr.length / 2];
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        int leftIndex = 0, rightIndex = 0;

        // Partitioning
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < pivot) {
                left[leftIndex++] = arr[i];
            } else if (arr[i] > pivot) {
                right[rightIndex++] = arr[i];
            }
        }

        left = quicksort(trimArray(left, leftIndex));
        right = quicksort(trimArray(right, rightIndex));

        int[] sorted = new int[arr.length];
        System.arraycopy(left, 0, sorted, 0, left.length);
        sorted[left.length] = pivot;
        System.arraycopy(right, 0, sorted, left.length + 1, right.length);

        return sorted;
    }

    // Helper function to trim array to actual size
    public static int[] trimArray(int[] arr, int size) {
        int[] newArr = new int[size];
        System.arraycopy(arr, 0, newArr, 0, size);
        return newArr;
    }

    // Mergesort Algorithm
    public static int[] mergesort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int middle = arr.length / 2;
        int[] left = new int[middle];
        int[] right = new int[arr.length - middle];

        System.arraycopy(arr, 0, left, 0, middle);
        System.arraycopy(arr, middle, right, 0, arr.length - middle);

        left = mergesort(left);
        right = mergesort(right);

        return merge(left, right);
    }

    // Merging two sorted arrays
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take the size of the array (n) from the user
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        Random rand = new Random();

        // Generate random dataset of size n
        int[] data1 = new int[n];
        for (int i = 0; i < data1.length; i++) {
            data1[i] = rand.nextInt(100000) + 1;  // Larger range of numbers
        }

        // Quicksort time measurement
        long startTime = System.nanoTime();
        quicksort(data1);
        long endTime = System.nanoTime();
        long quickSortTime = endTime - startTime;

        // Mergesort time measurement
        int[] data2 = new int[n];
        System.arraycopy(data1, 0, data2, 0, data1.length);
        startTime = System.nanoTime();
        mergesort(data2);
        endTime = System.nanoTime();
        long mergeSortTime = endTime - startTime;

        // Display results
        System.out.println("Quicksort time for " + n + " values: " + quickSortTime / 1000000 + " milliseconds");
        System.out.println("Mergesort time for " + n + " values: " + mergeSortTime / 1000000 + " milliseconds");

        scanner.close();
    }
}
