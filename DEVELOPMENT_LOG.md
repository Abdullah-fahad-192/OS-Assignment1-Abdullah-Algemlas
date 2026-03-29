# Development Log

## Instructions
Document your development process as you work on the assignment. Add entries showing:
- What you worked on
- Problems you encountered
- How you solved them
- Time spent

**Requirements**: Minimum 5 entries showing progression over time.

---

## Example Entry Format:

### Entry 1 - [April 1, 2026, 2:30 PM]
**What I did**: Forked the repository and set up my student ID

**Details**: 
- Created GitHub account with university email
- Forked the starter repository
- Changed student ID on line 92 to my actual ID (441234567)
- Compiled and ran the program successfully

**Challenges**: Had to install JDK first because javac wasn't recognized

**Solution**: Downloaded JDK 17 from Oracle website and set PATH variable

**Time spent**: 30 minutes

---

## Your Development Log:

Entry 1 - [March 25, 2026 - 6 PM]
What I did: 
I started creating the assignment and setting up my own account on GetHub
Details:
I read the instructions and assignment guidelines, including creating an account, Fork the Starter Repository, and RenameYourRepository, and finally, I updated my university ID number.
Challenges:
I encountered some problems, most notably my lack of understanding of the GetHub website and its structure, and I also had difficulty downloading Visual Studio Code due to my computer.
Solution:
I watched some helpful videos to solve my problems; for example, I watched a video explaining the menus for GetHub and another that gave me the most important add-ons for downloading Visual Studio Code to make my work easier and faster.
Time spent:
about 2 hours

---


Entry 2 - [march 27,2026-11 pm]
What I did:
I worked on Feature 1: Add Process Priority
Details:
 i Add a priority field to the Process class and Generate random priorities when creating processes and in the end i  Display priority when a process enters the ready queue
Challenges:
The biggest problem was that this was the first code I wrote in the program, and the Display Priority section was complicated.
Solution:
I used artificial intelligence to understand the feature and help me with the display, including updating the constructor and adding a getter method for priority.
Time spent:
about 1.5 hours
---
Entry 3 - [ [march 28,2026-2 am]
What I did:
i solve  Feature 2: Count Context Switches
Details:
Add a static counter variable  and  Increment the counter each time a new process starts running and in the end  Display total context switches 
Challenges:
Some requirements I wasn't sure were located exactly—whether in the Process class or within the main class—and this applies to all features.
Solution:
I tried placing the command in all locations, then I used artificial intelligence to identify them correctly.
Time spent:
about 1 hours
---
Entry 4 - [ [ [march 28,2026-10 pm]
What I did:
i  Track Waiting Time
Details:
i  Add fields to track when each process was created and total time spent waiting and i Use System.currentTimeMillis() to track time
Challenges:
This was the most difficult feature because it required me to track whether each transaction was completed or not and to count the number of entries.
Solution:
This command helped me: `public void updateWaitingTime() {
long currentTime = System.currentTimeMillis();
long waitTime = currentTime - lastReadyTime; // Time spent waiting since last added to queue
totalWaitingTime += waitTime;

}` because it made it easy for me to calculate the total wait time.
Time spent:
about 3.5 hours 
---
Entry 5 - [[ [march 29,2026-3 am]
What I did:
I tested the program 
Details:
I ran the code in segments, for example, Feature 1, then I ran the code, then Feature 2, then I ran the code again, in order to verify the output.
Challenges:
I was getting various output errors, and the first feature would appear correct, but adding feature 2 would result in an error in feature 1's code.
Solution:
In Visual Studio Code, I can view errors, then correct them, and see the previous error.
Time spent:
about 2 hours 
---
Entry 6 - [Optiona - [ [march 28,2026-6 am]
What I did:
I ran the program after 3 hours for end entry 5 
Details:
I ran the program a 3 hours  later to check the completed work and the save point.
Challenges:
I was surprised to find that some of the code was jumbled, some was deleted from my end, and some was incomplete.
Solution:
I went back to the last point I saved to complete some of the missing parts that I hadn't saved.
Time spent:
about 1 hour
---

## Summary

**Total time spent on assignment**: [X hours]

**Most challenging part**: 

**Most interesting learning**: 

**What I would do differently next time**: 
