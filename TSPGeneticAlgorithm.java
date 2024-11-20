import java.util.*;

public class TSPGeneticAlgorithm {

    // Helper function to calculate the total distance of a tour
    public static int calculateDistance(int[] tour, int[][] distanceMatrix) {
        int totalDistance = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            totalDistance += distanceMatrix[tour[i]][tour[i + 1]];
        }
        totalDistance += distanceMatrix[tour[tour.length - 1]][tour[0]]; // return to start
        return totalDistance;
    }

    // Function to perform crossover between two parents
    public static int[] crossover(int[] parent1, int[] parent2) {
        Random rand = new Random();
        int[] offspring = new int[parent1.length];
        Arrays.fill(offspring, -1);

        // Randomly select a crossover point
        int start = rand.nextInt(parent1.length);
        int end = rand.nextInt(parent1.length);

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        // Copy the selected segment from parent1 to offspring
        for (int i = start; i <= end; i++) {
            offspring[i] = parent1[i];
        }

        // Fill the remaining cities from parent2
        int currentIndex = 0;
        for (int i = 0; i < parent2.length; i++) {
            if (!contains(offspring, parent2[i])) {
                while (offspring[currentIndex] != -1) {
                    currentIndex++;
                }
                offspring[currentIndex] = parent2[i];
            }
        }

        return offspring;
    }

    // Helper function to check if a city is in the tour
    public static boolean contains(int[] tour, int city) {
        for (int i : tour) {
            if (i == city) return true;
        }
        return false;
    }

    // Function to perform mutation
    public static void mutate(int[] tour) {
        Random rand = new Random();
        int index1 = rand.nextInt(tour.length);
        int index2 = rand.nextInt(tour.length);

        // Swap the two cities to mutate the tour
        int temp = tour[index1];
        tour[index1] = tour[index2];
        tour[index2] = temp;
    }

    // Genetic Algorithm to solve TSP
    public static int[] geneticAlgorithm(int[][] distanceMatrix, int populationSize, int generations) {
        Random rand = new Random();
        int numCities = distanceMatrix.length;
        
        // Generate the initial population
        List<int[]> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            int[] tour = new int[numCities];
            for (int j = 0; j < numCities; j++) {
                tour[j] = j;
            }
            shuffleArray(tour, rand);
            population.add(tour);
        }

        for (int gen = 0; gen < generations; gen++) {
            // Evaluate fitness and select parents
            population.sort(Comparator.comparingInt(tour -> calculateDistance(tour, distanceMatrix)));
            List<int[]> newPopulation = new ArrayList<>();

            // Selection, Crossover, and Mutation
            while (newPopulation.size() < populationSize) {
                int[] parent1 = population.get(rand.nextInt(populationSize));
                int[] parent2 = population.get(rand.nextInt(populationSize));

                int[] offspring = crossover(parent1, parent2);
                if (rand.nextDouble() < 0.1) {
                    mutate(offspring); // Mutation with a certain probability
                }
                newPopulation.add(offspring);
            }
            population = newPopulation;
        }

        // Return the best tour found
        return population.get(0);
    }

    // Helper function to shuffle an array (for random initial population)
    public static void shuffleArray(int[] array, Random rand) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] distanceMatrix = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        int populationSize = 100;
        int generations = 1000;

        int[] bestTour = geneticAlgorithm(distanceMatrix, populationSize, generations);
        System.out.println("Best tour found using Genetic Algorithm: " + Arrays.toString(bestTour));
        System.out.println("Total distance: " + calculateDistance(bestTour, distanceMatrix));
    }
}
