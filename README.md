# Genetic Algorithm - Year 3 University Project

## Task
###### Part I
Write a genetic algorithm in Java. The goal is to evolve a random binary string to be all 1s.

###### Part II (extension)
Modify the genetic algorithm to also work as a classifier based on a ruleset (not implemented).

## Assessment
The code was submitted alongside a report investigating genetic algorithms as classifiers.

## Code
###### Controller
Contains the main() method. Initialises parameters for population size, chromosome length, crossover rate, mutation rate, and maximum number of generations. Then steps through each stage of the evolutionary algorithm by calling the respective methods in GA.java, which are:

0. Initialise random population
1. Tournament selection
2. Single-point crossover
3. Bit-wise mutation
4. Elitism
4a. Output generation to CSV file
5. Fitness evaluation
5a. Check for termination

###### GA
Logic for each step of the genetic algorithm (besides mutation). Heavy lifting done here.

###### Individual
Logic for initialising an individual as part of a population, evaluating fitness, getters and setters. Mutation also included here for convenience although I suppose it should have been in GA.java.

## Result
I got 69%, just missing out on a first as I failed to implement the extension part II.