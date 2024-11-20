import java.util.ArrayList;

public class NQueens {
    
    // Function to check if a queen can be safely placed at the given position
    public static boolean isSafe(int[] board, int row, int col) {
        // Check the previous rows for the same column and diagonals
        for (int i = 0; i < row; i++) {
            if (board[i] == col || board[i] - i == col - row || board[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }

    // Function to solve the N-Queens problem using backtracking
    public static void solveNQueens(int[] board, int row, int N, ArrayList<int[]> solutions) {
        if (row == N) {
            // If all queens are placed, save the solution
            int[] solution = new int[N];
            System.arraycopy(board, 0, solution, 0, N);
            solutions.add(solution);
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                solveNQueens(board, row + 1, N, solutions);
                board[row] = -1;  // Backtrack
            }
        }
    }

    // Function to measure the time taken and the number of solutions for N-Queens
    public static long timeNQueens(int N) {
        int[] board = new int[N];
        ArrayList<int[]> solutions = new ArrayList<>();
        long startTime = System.nanoTime();
        solveNQueens(board, 0, N, solutions);
        long endTime = System.nanoTime();
        return (endTime - startTime);  // Return time in nanoseconds
    }

    public static void main(String[] args) {
        System.out.println("Time for N-Queens (4 to 8):");
        
        for (int N = 4; N <= 8; N++) {
            long timeTaken = timeNQueens(N);
            System.out.println("N = " + N + ": Time = " + timeTaken / 1_000_000.0 + " milliseconds");
        }
    }
}
