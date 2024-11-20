import java.util.*;

public class TSPCrossover {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        int n = sc.nextInt();

        int[] parent1 = new int[n];
        int[] parent2 = new int[n];

        System.out.println("Enter the Parent 1 Chromosome (tour): ");
        for (int i = 0; i < n; i++) {
            parent1[i] = sc.nextInt();
        }

        System.out.println("Enter the Parent 2 Chromosome (tour): ");
        for (int i = 0; i < n; i++) {
            parent2[i] = sc.nextInt();
        }

        int[] child = crossover(parent1, parent2);

        System.out.println("Parent 1 Chromosome: ");
        System.out.println(Arrays.toString(parent1));

        System.out.println("Parent 2 Chromosome: ");
        System.out.println(Arrays.toString(parent2));

        System.out.println("Child Chromosome after Crossover: ");
        System.out.println(Arrays.toString(child));

        sc.close();
    }

    public static int[] crossover(int[] parent1, int[] parent2) {
        int n = parent1.length;
        int[] child = new int[n];
        Arrays.fill(child, -1); // Initialize child array with -1

        Random rand = new Random();
        int start = rand.nextInt(n);  // Random start point
        int end = rand.nextInt(n);    // Random end point

        // Ensure start < end
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        // Copy segment from Parent 1 to Child
        for (int i = start; i <= end; i++) {
            child[i] = parent1[i];
        }

        // Fill remaining positions from Parent 2, maintaining order
        HashSet<Integer> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            set.add(child[i]);
        }

        int childIndex = 0;
        for (int i = 0; i < n; i++) {
            if (child[i] == -1) {
                // Fill from Parent 2
                while (set.contains(parent2[childIndex])) {
                    childIndex++;
                }
                child[i] = parent2[childIndex++];
            }
        }

        return child;
    }
}
