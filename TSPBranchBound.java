import java.util.*;

public class TSPBranchBound {

    // A utility function to calculate the minimum cost to visit all cities
    public static int calculateLowerBound(int[][] distanceMatrix, boolean[] visited, int currentCity, int citiesCount) {
        int bound = 0;
        
        // Lower bound calculation (minimum outgoing edges)
        for (int i = 0; i < citiesCount; i++) {
            if (!visited[i]) {
                bound += Math.min(distanceMatrix[currentCity][i], distanceMatrix[i][currentCity]);
            }
        }
        return bound;
    }

    // The branch and bound method to solve TSP
    public static int tspBranchBound(int[][] distanceMatrix, int citiesCount) {
        int[] bestTour = new int[citiesCount];
        int minCost = Integer.MAX_VALUE;

        // The priority queue holds nodes to explore, sorted by their lower bounds.
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.lowerBound));

        // Initialize with the starting city (0)
        ArrayList<Integer> initialTour = new ArrayList<>();
        initialTour.add(0); // Starting at city 0
        boolean[] initialVisited = new boolean[citiesCount];
        initialVisited[0] = true;

        pq.add(new Node(0, 0, initialTour, initialVisited));  // Start at city 0

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            if (currentNode.tour.size() == citiesCount) {
                int cost = currentNode.cost + distanceMatrix[currentNode.tour.get(currentNode.tour.size() - 1)][0];
                if (cost < minCost) {
                    minCost = cost;
                    bestTour = currentNode.tour.stream().mapToInt(Integer::intValue).toArray();
                }
                continue;
            }

            for (int i = 0; i < citiesCount; i++) {
                if (!currentNode.visited[i]) {
                    boolean[] visitedCopy = currentNode.visited.clone();
                    visitedCopy[i] = true;
                    ArrayList<Integer> newTour = new ArrayList<>(currentNode.tour);
                    newTour.add(i);

                    int lowerBound = calculateLowerBound(distanceMatrix, visitedCopy, i, citiesCount);

                    if (lowerBound < minCost) {
                        pq.add(new Node(currentNode.cost + distanceMatrix[currentNode.tour.get(currentNode.tour.size() - 1)][i], lowerBound, newTour, visitedCopy));
                    }
                }
            }
        }
        return minCost;
    }

    // Helper class to store a node's state
    static class Node {
        int cost;
        int lowerBound;
        ArrayList<Integer> tour;
        boolean[] visited;

        Node(int cost, int lowerBound, ArrayList<Integer> tour, boolean[] visited) {
            this.cost = cost;
            this.lowerBound = lowerBound;
            this.tour = tour;
            this.visited = visited;
        }
    }

    public static void main(String[] args) {
        // Example distance matrix (4 cities)
        int[][] distanceMatrix = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        int citiesCount = 4;
        System.out.println("Minimum cost using Branch and Bound: " + tspBranchBound(distanceMatrix, citiesCount));
    }
}
