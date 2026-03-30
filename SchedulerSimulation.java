
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

// ANSI Color Codes for enhanced terminal output
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
}
// Class representing a process that implements Runnable to be run by a thread
class Process implements Runnable {
    private String name; // Name of the process
    private int burstTime; // Total time the process requires to complete (in milliseconds)
    private int timeQuantum; // Time slice (time quantum) allowed per CPU access (in milliseconds)
    private int remainingTime; // Time left for the process to finish its execution
// FEATURE 1: Add priority field to process class integer 1-5, where 5 is highest:
    private int priority; // Priority of the process 
    // FEATURE 3:  Add fields to track when each process was created and total time spent waiting
    private long creationTime; // Time when process was created (in milliseconds)
    private long totalWaitingTime; // Total time spent waiting in queue (in milliseconds)
    private long lastReadyTime; // Last time the process entered the ready queue

    // Constructor to initialize the process with name, burst time, and time quantum
    public Process(String name, int burstTime, int timeQuantum, int priority) // FEATURE 1: Added priority parameter to constructor 
    // FEATURE 3: Initialize timing fields and calculate waiting time for each process
       {
        this.name = name;
        this.burstTime = burstTime;
        this.timeQuantum = timeQuantum;
        this.remainingTime = burstTime; // Initially, remaining time is equal to the burst time
        this.priority = priority; // FEATURE 1: Initialize priority field
         // FEATURE 3: Initialize the timing fields and  Calculate waiting time for each proces
        this.creationTime = System.currentTimeMillis(); // Record when process is created
        this.totalWaitingTime = 0; // Start with 0 waiting time
        this.lastReadyTime = this.creationTime; // Initially, process is ready at creation
    }
    // This method will be called when the thread for this process is started
    @Override
    public void run() {
        // Simulate running for either the time quantum or remaining time, whichever is
        // smaller
        int runTime = Math.min(timeQuantum, remainingTime); // Run for the smaller of the two times

        // Show quantum execution starting
        String quantumBar = createProgressBar(0, 15);
        System.out.println(Colors.BRIGHT_GREEN + "  ▶ " + Colors.BOLD + Colors.CYAN + name +
                Colors.RESET + Colors.GREEN + " executing quantum" + Colors.RESET +
                " [" + runTime + "ms] ");

        try {
            // Simulate quantum execution with progress updates
            int steps = 5; // Number of progress updates
            int stepTime = runTime / steps;

            for (int i = 1; i <= steps; i++) {
                Thread.sleep(stepTime);
                int quantumProgress = (i * 100) / steps;
                quantumBar = createProgressBar(quantumProgress, 15);

                // Clear line and show updated progress
                System.out.print("\r  " + Colors.YELLOW + "⚡" + Colors.RESET +
                        " Quantum progress: " + quantumBar);
            }
            System.out.println(); // New line after quantum completion

        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "\n  ✗ " + name + " was interrupted." + Colors.RESET);
        }

        remainingTime -= runTime; // Deduct the run time from the remaining time
        int overallProgress = (int) (((double) (burstTime - remainingTime) / burstTime) * 100);
        String overallProgressBar = createProgressBar(overallProgress, 20);

        System.out.println(Colors.YELLOW + "  ⏸ " + Colors.CYAN + name + Colors.RESET +
                " completed quantum " + Colors.BRIGHT_YELLOW + runTime + "ms" + Colors.RESET +
                " │ Overall progress: " + overallProgressBar);
        System.out.println(Colors.MAGENTA + "     Remaining time: " + remainingTime + "ms" + Colors.RESET);

        // If the process still has remaining time, it yields CPU for the next process
        if (remainingTime > 0) {
            System.out.println(Colors.BLUE + "  ↻ " + Colors.CYAN + name + Colors.RESET +
                    " yields CPU for context switch" + Colors.RESET);
        } else {
            // If no time is left, the process has finished its execution
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name +
                    Colors.RESET + Colors.BRIGHT_GREEN + " finished execution!" +
                    Colors.RESET);
        }
        System.out.println();
    }

    // Helper method to create a visual progress bar
    private String createProgressBar(int progress, int width) {
        int filled = (progress * width) / 100;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < width; i++) {
            if (i < filled) {
                bar.append(Colors.GREEN + "█" + Colors.RESET);
            } else {
                bar.append(Colors.WHITE + "░" + Colors.RESET);
            }
        }
        bar.append("] ").append(progress).append("%");
        return bar.toString();
    }

    // Method to run the last process to completion, ignoring the time quantum
    public void runToCompletion() {
        try {
            // Run for the remaining time without splitting into smaller time slices
            System.out.println(Colors.BRIGHT_CYAN + "  ⚡ " + Colors.BOLD + Colors.CYAN + name +
                    Colors.RESET + Colors.BRIGHT_CYAN + " is the last process, running to completion" +
                    Colors.RESET + " [" + remainingTime + "ms]");
            Thread.sleep(remainingTime); // Run until completion
            remainingTime = 0; // Mark the process as completed
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name +
                    Colors.RESET + Colors.BRIGHT_GREEN + " finished execution!" + Colors.RESET);
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "  ✗ " + name + " was interrupted." + Colors.RESET);
        }
    }

    // Getter methods for process name, burst time, and remaining time
    public String getName() {
        return name;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }
     // FEATURE 1: Getter for priority
    public int getPriority() {
        return priority;
    }
    // Check if the process has finished (i.e., no remaining time)
    public boolean isFinished() {
        return remainingTime <= 0;
    }
     // FEATURE 3: Getter for creation time
    public long getCreationTime() {
        return creationTime;
    }
    
    // FEATURE 3: Getter for total waiting time
    public long getTotalWaitingTime() {
        return totalWaitingTime;
    }
    
    // FEATURE 3: Getter for last ready time
    public long getLastReadyTime() {
        return lastReadyTime;
    }
    // FEATURE 3: Method to update waiting time when process is ready to run
    // Call this when process starts executing to calculate how long it was waiting
    public void updateWaitingTime() {
        long currentTime = System.currentTimeMillis();
        long waitTime = currentTime - lastReadyTime; // Time spent waiting since last added to queue
        totalWaitingTime += waitTime;
    }
    
    // FEATURE 3: Method to set last ready time when process reenters queue
    public void setLastReadyTime(long time) {
        this.lastReadyTime = time;
    }
}

public class SchedulerSimulation {
    // FEATURE 2: Static counter for context switches
    private static int contextSwitchCount = 0;
     // FEATURE 3: List to store all completed processes for summary
    private static List<Process> completedProcesses = new ArrayList<>();
    public static void main(String[] args) {
        // ⚠️ IMPORTANT: Put your student ID here to seed the random number generator
        // This makes your output unique to you - DO NOT forget to change this!
        int studentID = 445050192; // ← CHANGE THIS TO YOUR ACTUAL STUDENT ID

        Random random = new Random(studentID);

        // Define the time quantum in milliseconds (the maximum time a process gets in
        // one round)
        // Choose a random number between 2000 and 5000 ms with a step of 1000 ms
        int timeQuantum = 2000 + random.nextInt(4) * 1000; // Random: 2000, 3000, 4000, or 5000

        // Generate random number of processes between 10 and 20
        int numProcesses = 10 + random.nextInt(11); // Random number between 10 and 20

        // Queue to manage processes in a First-In-First-Out (FIFO) order
        Queue<Thread> processQueue = new LinkedList<>();

        // Map to associate each thread with its respective process object
        Map<Thread, Process> processMap = new HashMap<>();

        // Print simulation header with elegant formatting
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_CYAN +
                "╔═══════════════════════════════════════════════════════════════════════════════════════╗" +
                Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                Colors.BG_BLUE + Colors.BRIGHT_WHITE + Colors.BOLD +
                "                          CPU SCHEDULER SIMULATION                                " +
                Colors.RESET + Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                "╠═══════════════════════════════════════════════════════════════════════════════════════╣" +
                Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                Colors.YELLOW + "  ⚙ Processes:     " + Colors.RESET + Colors.BRIGHT_YELLOW +
                String.format("%-65s", numProcesses) +
                Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                Colors.YELLOW + "  ⏱ Time Quantum:  " + Colors.RESET + Colors.BRIGHT_YELLOW +
                String.format("%-65s", timeQuantum + "ms") +
                Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                Colors.YELLOW + "  🔑 Student ID:    " + Colors.RESET + Colors.BRIGHT_YELLOW +
                String.format("%-65s", studentID) +
                Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                "╚═══════════════════════════════════════════════════════════════════════════════════════╝" +
                Colors.RESET + "\n");

        // Create 'numProcesses' number of processes
        for (int i = 1; i <= numProcesses; i++) {
            // Random burst time for each process between timeQuantum/2 and 3*timeQuantum
            int burstTime = timeQuantum / 2 + random.nextInt(2 * timeQuantum + 1);
               // FEATURE 1: Generate random priority between 1 and 5 (5 is highest)
            int priority = 1 + random.nextInt(5); // Random integer mumber  between 1 and 5
            
            // Create a new process object with a unique name, burst time, time quantum, and priority
            // FEATURE 1: Added priority parameter
            Process process = new Process("p " + i, burstTime, timeQuantum, priority);
            
            // Add the process to the ready queue and the map
            addProcessToQueue(process, processQueue, processMap);
        }
        // Start of the scheduler simulation
        System.out.println(Colors.BOLD + Colors.GREEN +
                "╔════════════════════════════════════════════════════════════════════════════════╗" +
                Colors.RESET);
        System.out.println(Colors.BOLD + Colors.GREEN + "║" + Colors.RESET +
                Colors.BG_GREEN + Colors.WHITE + Colors.BOLD +
                "                        ▶  SCHEDULER STARTING  ◀                               " +
                Colors.RESET + Colors.BOLD + Colors.GREEN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.GREEN +
                "╚════════════════════════════════════════════════════════════════════════════════╝" +
                Colors.RESET + "\n");

     // Loop to manage the scheduling of processes
        while (!processQueue.isEmpty()) {
            // FEATURE 2: increment the counter each time a new process start running 
            contextSwitchCount++;
            // Get the next thread from the queue (FIFO)
            Thread currentThread = processQueue.poll(); // Dequeues the next thread
            // Print the current process queue (list of process IDs in the queue)
            System.out.println(Colors.BOLD + Colors.MAGENTA + "┌─ Ready Queue " + "─".repeat(65) + Colors.RESET);
            System.out.print(Colors.MAGENTA + "│ " + Colors.RESET + Colors.BRIGHT_WHITE + "[" + Colors.RESET);
            int queueCount = 0;
            for (Thread thread : processQueue) {
                Process process = processMap.get(thread);
                if (queueCount > 0)
                    System.out.print(Colors.WHITE + " → " + Colors.RESET);
                System.out.print(Colors.BRIGHT_CYAN + process.getName() + Colors.RESET);
                queueCount++;
            }
            if (queueCount == 0) {
                System.out.print(Colors.YELLOW + "empty" + Colors.RESET);
            }
            System.out.println(Colors.BRIGHT_WHITE + "]" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.MAGENTA + "└" + "─".repeat(79) + Colors.RESET + "\n");

            // Start the thread, which will run the process for one time quantum
            currentThread.start();

            try {
                // Wait for the thread to finish its time quantum before continuing to the next
                // process
                currentThread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }

            // Retrieve the process associated with the thread from the map
            Process process = processMap.get(currentThread);
              // FEATURE 3: Update waiting time for this process before run
            // Calculate how long it waited in queue since it was last added
            process.updateWaitingTime();
        // Check if the process is not finished
        if (!process.isFinished()) {
            // If the process still has remaining time, check if there are more processes in
            // queue
            if (!processQueue.isEmpty()) {
                 // FEATURE 3: Set last ready time when re-entering queue
                // This marks when the process started waiting again
                process.setLastReadyTime(System.currentTimeMillis());
                // Re-enqueue the process to give it another chance to run in the next round
                addProcessToQueue(process, processQueue, processMap);
            } else {
                // If this is the last process in the queue, run it to completion
                System.out.println(Colors.BRIGHT_YELLOW + "  ⚠ " + Colors.CYAN + process.getName() +
                        Colors.RESET + Colors.YELLOW + " is the last process → running to completion" +
                        Colors.RESET);
                process.runToCompletion(); // Run until the process completes
                // FEATURE 3: Add process to completed list for summary
                completedProcesses.add(process);
            }
        } else {
            // FEATURE 3: Process finished, add to completed list for summary
            completedProcesses.add(process);
        }
                } 

        // End of the scheduler simulation
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN +
                "╔════════════════════════════════════════════════════════════════════════════════╗" +
                Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN + "║" + Colors.RESET +
                Colors.BG_GREEN + Colors.WHITE + Colors.BOLD +
                "                     ✓  ALL PROCESSES COMPLETED  ✓                            " +
                Colors.RESET + Colors.BOLD + Colors.BRIGHT_GREEN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN +
                "╚════════════════════════════════════════════════════════════════════════════════╝" +
                Colors.RESET + "\n");
                // FEATURE 2: Display total context switches
System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE +
        "\n==================== SCHEDULER STATS ====================" +
        Colors.RESET);

System.out.println(Colors.BRIGHT_WHITE +
        "Total Context Switches: " +
        Colors.BRIGHT_GREEN + contextSwitchCount +
        Colors.RESET);

System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE +
        "========================================================\n" +
        Colors.RESET);
        // FEATURE 3: Display waiting time summary table at the end
        displayWaitingTimeSummary();
    }

    // Method to add a process to the queue and map, while printing a "ready"
    // message
     // FEATURE 1: Updated to display priority in output message
public static void addProcessToQueue(Process process, Queue<Thread> processQueue,
        Map<Thread, Process> processMap) {
    // Create a new thread to run the process
    Thread thread = new Thread(process);

    // Add the thread to the ready queue
    processQueue.add(thread);

    // Map the thread to the process, so we can track the process associated with
    // each thread
    processMap.put(thread, process);
    // Print a message indicating the process has entered the ready queue
   // FEATURE 1: Updated message with priority (different format)
    System.out.println(
            Colors.BRIGHT_CYAN + process.getName() + Colors.RESET +
            Colors.WHITE + " | Priority: " + Colors.BLUE+ process.getPriority() + Colors.RESET +
            Colors.WHITE + " | Burst time : " + Colors.RED + process.getBurstTime() + " ms" + Colors.RESET +
            Colors.WHITE + " --> added to ready queue"
    );
}

// FEATURE 3: Display summary of waiting times for all completed processes
public static void displayWaitingTimeSummary() {
    System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE +
            "\n====================================================================" +
            Colors.RESET);
    System.out.println(Colors.BOLD + Colors.BRIGHT_WHITE +
            "                 WAITING TIME REPORT FOR COMPLETED PROCESSES" +
            Colors.RESET);
    System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE +
            "====================================================================" +
            Colors.RESET);

    // Print table headings
    System.out.println(Colors.BOLD + Colors.BRIGHT_YELLOW +
            String.format("%-12s %-15s %-12s %-15s", 
                    "Process", "Burst Time", "Priority", "Waiting Time") +
            Colors.RESET);
    System.out.println(Colors.BRIGHT_BLUE +
            "--------------------------------------------------------------------" +
            Colors.RESET);

    long totalWaitingTime = 0;
    // Print process details
    for (Process process : completedProcesses) {
        System.out.println(
                Colors.BRIGHT_CYAN + String.format("%-12s", process.getName()) + Colors.RESET +
                Colors.RED + String.format("%-15s", process.getBurstTime() + "ms") + Colors.RESET +
                Colors.BLUE+ String.format("%-12s", process.getPriority()) + Colors.RESET +
                Colors.BRIGHT_GREEN + String.format("%-15s", process.getTotalWaitingTime() + "ms") + Colors.RESET
        );

        totalWaitingTime += process.getTotalWaitingTime();
    }

    System.out.println(Colors.BRIGHT_BLUE +
            "--------------------------------------------------------------------" +
            Colors.RESET);

    double avgWaitingTime = (double) totalWaitingTime / completedProcesses.size();

    System.out.println(Colors.BOLD + Colors.BRIGHT_YELLOW +
            "Average Waiting Time: " + String.format("%.2f ms", avgWaitingTime) +
            Colors.RESET);

    System.out.println(Colors.BOLD + Colors.BRIGHT_BLUE +
            "====================================================================\n" +
            Colors.RESET);
}
}
