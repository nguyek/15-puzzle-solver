# 15-puzzle-solver

This sample is a strong example of my OOP design, and Java programming skills.

This problem will solve a 15-puzzle configuration with a user specified
graph search technique. More info here: https://en.wikipedia.org/wiki/15_puzzle

It will report the depth the solution was found,
the number of nodes created, number of nodes examined, and the maximum
size of the fringe.

Solved goal states include: "123456789ABCDEF " or "123456789ABC DFE"

To run the program, please use the command line to travel to the src directory:
	type 'make' to compile all java files then

	java FifteenProblemMain ["start state"] [search_technique] [option]

	e.g. : java FifteenProblemMain "123456789ABC DFE" BFS

The possible search techniques are BFS, DFS, DLS, GBFS, Astar.
	
Options are only for DLS, GBFS, Astar. 

For DLS it will be an integer representing the maximum depth to be searched. 

For GBFS and Astar, it will be h1 or h2, where h1 is the Manhattan distance 
heuristic and h2 is the Straight Line Heuristic.
