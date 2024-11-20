import java.util.Random;
import java.util.Scanner;

public class MergeSortExample {

    // Merge function to merge two halves
    public static void merge(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Recursive call to sort the left and right halves
            merge(arr, left, mid);
            merge(arr, mid + 1, right);

            // Merge the sorted halves
            mergeHalves(arr, left, mid, right);
        }
    }

    // Function to merge the two halves
    public static void mergeHalves(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        // Merge the arrays
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Function to perform Merge Sort
    public static void performMergeSort(int[] arr) {
        merge(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input for array size
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        scanner.close();

        Random rand = new Random();

        // Best case: Already sorted array
        int[] bestCaseArray = new int[size];
        for (int i = 0; i < size; i++) {
            bestCaseArray[i] = i;
        }

        long startTime = System.nanoTime();
        performMergeSort(bestCaseArray);
        long endTime = System.nanoTime();
        System.out.println("Merge Sort Best Case Time: " + (endTime - startTime) + " nanoseconds");

        // Worst case: Reverse sorted array
        int[] worstCaseArray = new int[size];
        for (int i = 0; i < size; i++) {
            worstCaseArray[i] = size - i - 1;
        }

        startTime = System.nanoTime();
        performMergeSort(worstCaseArray);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Worst Case Time: " + (endTime - startTime) + " nanoseconds");
    }
}
