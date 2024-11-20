import java.util.Random;
import java.util.Scanner;

public class TSPMutation {

    // Function to mutate a chromosome by swapping two cities
    public static void mutateChromosome(int[] chromosome) {
        Random rand = new Random();

        int index1 = rand.nextInt(chromosome.length);
        int index2 = rand.nextInt(chromosome.length);

        // Ensure that index1 and index2 are different
        while (index1 == index2) {
            index2 = rand.nextInt(chromosome.length);
        }

        // Swap the cities at these two positions
        int temp = chromosome[index1];
        chromosome[index1] = chromosome[index2];
        chromosome[index2] = temp;
    }

    // Function to print the chromosome
    public static void printChromosome(int[] chromosome) {
        for (int city : chromosome) {
            System.out.print(city + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of cities (n)
        System.out.print("Enter the number of cities: ");
        int n = scanner.nextInt();

        // Input: Distance matrix
        int[][] distanceMatrix = new int[n][n];
        System.out.println("Enter the distance matrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distanceMatrix[i][j] = scanner.nextInt();
            }
        }

        // Generate a chromosome representing a solution (tour)
        int[] chromosome = new int[n];
        for (int i = 0; i < n; i++) {
            chromosome[i] = i;  // Initially the cities are in order
        }

        // Print original chromosome (tour)
        System.out.println("Original chromosome (tour): ");
        printChromosome(chromosome);

        // Mutate the chromosome
        mutateChromosome(chromosome);

        // Print mutated chromosome (tour)
        System.out.println("Mutated chromosome (tour): ");
        printChromosome(chromosome);

        scanner.close();
    }
}
