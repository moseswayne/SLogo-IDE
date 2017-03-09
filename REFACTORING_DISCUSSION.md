##Refactoring Discussion
###Names: Moses Wayne, Yuxiang He
###netIDs: msw38, yh129
The CommandNode class was completely refactored into an inheritance hierarchy. This removes extraneous code form any given node where it doesn't need it. By doing this, nodes now are single function and don't rely on boolean evaluations to determine how they are run.
This class was chosen to be refactored because of inefficient use of code. Because the node relied heavily on boolean evaluations for which particular parts to run, there were many dependencies on how the code ran. 