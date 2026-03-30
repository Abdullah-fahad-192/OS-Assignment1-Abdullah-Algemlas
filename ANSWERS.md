# Assignment Questions

## Instructions
Answer all 4 questions with detailed explanations. Each answer should be **3-5 sentences minimum** and demonstrate your understanding of the concepts.

---

Question 1: Thread vs Process
Question: Explain the difference between a thread and a process. Why did we use threads in this assignment instead of creating separate processes?
Your Answer:
[a process is a program in execution that progresses sequentially and it has a heavyweight entity with own address space while thread is basic unit of cpu utilization and called light weight process and each thread is associated with a process and share the same memory with other threads in the same process.
Because of this, communication between threads is easier and faster , switching between processes (context switching) is more expensive than switching between threads , and processes are heavier and take more time to create and manage, while threads are lighter and faster
In this assignment, we used threads instead of processes because the simulation needs fast execution and frequent switching between tasks. Threads they share memory and can communicate easily, which makes the scheduling process more efficient and fast  ]

Question 2 : In Round-Robin scheduling, what happens when a process doesn't finish within its time quantum? Explain using an example from your program output.

Your Answer:
[In Round-Robin scheduling, if a process does not finish within its time quantum it is stopped and move in last on ready queue It will wait for its turn again and run later when the scheduler selects it. This continues until the process finishes completely 
]
Example from my output:
[Priority number 5 executing quantum [3000ms]
  ظأة Quantum progress: [ظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûê] 100%
  ظ╕ Priority number 5 completed quantum 3000ms ظ¤é Overall progress: [ظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûêظûّظûّظûّ] 87%
     Remaining time: 825ms
  ظ╗ Priority number 5 yields CPU for context switch]
Explanation of example: [In this example, process P5 did not finish during its first turn, so it was sent back to the queue,After other processes got their turn, P5 was selected again and continued execution, Each time it runs, its remaining time decreases until it reaches zero and Overall progress get 100]

Question 3 : A thread can be in different states: New, Runnable, Running, Waiting, Terminated. Walk through these states for one process (P1) from your simulation.

Your Answer:

[P1 moves through the thread states step by step while the scheduler is running. At first the thread for P1 is created new (), then it becomes ready to run rennable() ,then it gets CPU time and starts executing. If it still has remaining time, it stop and go to ready queue and  wait for another turn. Finally, when its remaining time becomes zero, it finishes and its thread ends and being terminated ]

New: [P1 is in the New state when its thread is created but has not started yet. This happens when the program creates the thread object for P1 before calling start()]

Runnable: [P1 becomes Runnable when start() is called on its thread. In this state, the thread is ready to run and waiting for the CPU to be assigned by the scheduler]

Running: [P1 is in the Running state when the scheduler selects it and its run() method starts executing. During this time, P1 uses the CPU for its time quantum or until it finishes]

Waiting: [P1 can be in a Waiting state when it is not currently using the CPU and is waiting for its next turn. In the simulation and if sleep() is used during execution, that also puts the thread into a waiting like state for a short time  and also join() main process wait for thread to completed before continuning ]

Terminated: [P1 becomes Terminated when it finishes all of its remaining time and the run() method ends This means P1 is fully done and comopleted execution, ]

Question 4: Real-World Applications
Question: Give TWO real-world examples where Round-Robin scheduling with threads would be useful. Explain why this scheduling algorithm works well for those scenarios.

Your Answer:

Example 1: [brave website ]
Description: [In the Brave Browser, multiple tasks happen at the same time. For example, one thread loads the webpage, another handles user actions like scrolling or clicking, and another may load ads blocking or background]

Why Round-Robin works well here: [Round-Robin works well because it gives each task a small amount of CPU time, so no task blocks the others. This keeps the browser responsive, even while pages are loading, so everything runs smoothly without freezing ,]

Example 2: [Steam Application]
Description: [in the Steam application, multiple tasks happen at the same time. For example, one thread handles downloading  games, another manages the user interface, and another may run background services like chat or community .]

Why Round-Robin works well here: [Round-Robin works well because it gives each task a fair share of CPU time. This means downloading a game will not freeze the application, and the user can still browse the store or chat with friends smoothly. It also improves responsiveness by quickly switching between tasks]

Summary
Key concepts I understood through these questions: 1. 2. 3.
answers:
1:The difference between threads and processes, and why threads are faster and more efficient.
2:How Round-Robin scheduling works and how processes take turns using the CPU.
3:How threads move between states (new, runnable, running, waiting, terminated) during execution.

Concepts I need to study more:Real-world applications are a broad and deep concept that can be studied in more than one way, whether in games or social media, and performance varies depending on the site or program.
