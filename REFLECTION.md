# Reflection Questions

## Instructions
Answer the following questions about your learning experience. Each answer should be **at least 5-7 sentences** and show your understanding.

---

Question 1: What did you learn about multithreading?
Your Answer:
[From this assignment  I learned how multithreading works in a practical way. I learned how to create threads in Java using the Runnable interface and how each thread runs its own task
. I also understood that calling start() its method to begin thread execute not run it .
also I learned about thread states, like when a thread is created (new), then becomes runnable, then running, and finally finishes (terminated)
. I noticed that threads can also pause for some time using methods like sleep ,and can checks if thread still running by using is Alive() methods  .
Another important thing and surprised me its  I learned how threads execute concurrently. Even if there is only one CPU, the system switches between threads very fast, so it looks like they are running at the same time]

Question 2: What was the most challenging part of this assignment?
Your Answer:
[The most challenging part of this assignment was implementing the waiting time feature It was not just about writing code  but understanding how the processes move inside the ready queue
. Each process does not run once  it keeps going back to the queue multiple times, so tracking the exact waiting time was confusing 
What made it difficult is that I had to understand the flow of the program and how scheduling works step by step where multiple processes share the CPU and take turns executing  Because of this  it was not easy to calculate when a process is actually waiting versus running.
I also had a  challenge to  understanding where to place the code for updating values like waiting time and context switches , But there are two things that really helped me
, one is Use System.currentTimeMillis() to track time, seconed is     public void updateWaitingTime() {long currentTime = System.currentTimeMillis();long waitTime = currentTime - lastReadyTime; // Time spent waiting since last added to queuetotalWaitingTime += waitTime;
    }.]
	
Question 3: How did you overcome the challenges you faced?
Your Answer:
[I didn’t get it directly from the first try. My approach is  involved dividing the tasks or features and working through them step by step according to the given information or requirements.
First, I read the lecture slides and checked how the states are explained. After that, I went back to the code and tried to connect each state with what is actually happening in the program.
I also useي artificial intelligence and searched for YouTube videos to increase my level of understanding. also i see debugging by running the programو
and observing the output. This helped me see when the thread starts, runs, waits, and finishes. In some cases,
Another thing that helped me was focusing on the main methods like start(), sleep(),join() and how the thread ends  By connecting these methods with the lifecycle states the idea became clearer.]

Question 4: How can you apply multithreading concepts in real-world applications?
Your Answer:
[In real life, I see multithreading used in applications that I use daily because its responsive and performance , For example, in web browsers like brave
, one thread is responsible for loading the webpage while another handles user actions like scrolling or zooming. This makes the browser feel fast and responsive.
Another example is in games, where different threads handle different tasks ,like call of duty , rendering graphics, processing player input, and running game logic at the same time. Without threads, the game would be slow or laggy.
Also, in mobile apps, like instgram, one thread loads content from the internet while another keeps the interface smooth. This prevents the app from getting stuck while data is loading.
This connects to what I learned in this assignment, using the CPU in Round-Robin scheduling. Just like in the assignment, each task gets a small amount of time, which keeps]

Additional Reflections (Optional)
What would you like to learn more about?
[I want to know how companies that produce graphics cards and processors develop and improve the operating system, for example, by increasing the number of processor cores or expanding the graphics card's RAM.]
How confident do you feel about multithreading concepts now?
[Rate yourself : Beginner ]
[I gave this rating because I was initially worried about the requirements and didn't start the assignment early due to other commitments. However, I truly benefited from the topics covered in the course. What I want to improve is my coding skills because, frankly, I haven't relied on myself much in coding.]
Feedback on the assignment
[The assignment was very distinctive and useful in terms of the topics related to the curriculum, and its difficulty ranged from medium to difficult, entirely due to the coding. My simple suggestion is to increase the concepts related to the course while reducing the coding requirements.]

