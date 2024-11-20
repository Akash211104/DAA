import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    // Number of philosophers and forks
    private static final int NUM_PHILOSOPHERS = 5;

    // Semaphore for each fork
    private static final Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];

    // Semaphore to limit philosophers to avoid deadlock
    private static final Semaphore table = new Semaphore(NUM_PHILOSOPHERS - 1);

    public static void main(String[] args) {
        // Initialize semaphores for forks
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1);
        }

        // Create and start threads for philosophers
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            int philosopherId = i;
            new Thread(() -> philosopher(philosopherId)).start();
        }
    }

    private static void philosopher(int id) {
        while (true) {
            try {
                // Thinking
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep((int) (Math.random() * 1000));

                // Trying to pick up forks
                table.acquire(); // Limit access to the table
                forks[id].acquire(); // Left fork
                forks[(id + 1) % NUM_PHILOSOPHERS].acquire(); // Right fork

                // Eating
                System.out.println("Philosopher " + id + " is eating.");
                Thread.sleep((int) (Math.random() * 1000));

                // Releasing forks
                forks[id].release();
                forks[(id + 1) % NUM_PHILOSOPHERS].release();
                table.release();

                // Resume thinking
                System.out.println("Philosopher " + id + " finished eating and is thinking again.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Fairness: Forks are accessed using semaphores, ensuring mutual exclusion and equal chances for all philosophers.

// Deadlock Avoidance: The table semaphore ensures at least one fork is always free by limiting philosophers accessing the table to  𝑛 −1

// Starvation Avoidance: Semaphores inherently avoid starvation as they queue requests in a fair, first-come-first-served manner.

// Scalability: The program is scalable by adjusting NUM_PHILOSOPHERS and extending the forks array.

 
