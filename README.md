![logo](/logo.png)

## Overview

Genetic Seeker is an evolutionary simulation in which agents compete to locate a moving target with no information other than genetic instructions. The application is built in Swing and serves as a pure Java re-implementation of the [CodeBullet project](https://medium.com/@mik.szuga/the-genetic-algorithm-explained-with-intelligent-dots-319088f22d68) done in Processing.

### Genetic Algorithms

GAs are a machine learning approach that employ random search and natural selection to discover heuristic solutions to optimization problems, as opposed to gradient-guided techniques. They are rooted in the idea that machine learning models can "evolve" to become better over time with some inspiration from biology. At the heart of the algorithm is the concept of _genes_, which underly the behavior of individuals and can spread among the broader population.

### Evolutionary Design
A brief summary of the genetic operators and design decisions in Seeker:

- **Fitness scores results and efficiency.** The fitness function scores agents first according to their distance from the target and second according to the steps they took to get there, to ensure convergence to a straight path. 
- **Fitness-proportionate selection.** FPS offers a balance between evolutionary pressure and genetic diversity by selecting population members with a chance proportional to their fitness (e.g. a fitness score of 6.0 provides twice the odds of survival over a score of 3.0). This is more robust than unsophisticated algorithms like truncation selection, which are more likely to eliminate weaker members that possess _some_ useful genetic information.
- **Genetic representation.** An agent's genome consists of several hundred acceleration vectors, which are read sequentially to guide motion.
- **Cloning with mutation.** Single-parent cloning is the simplest mechanism for genetic recombination, but suffices for this problem. Parents are culled from each generation and cloned until repopulation, passing down their genes with small chances of point mutation (randomized new genes).
- **Elitist selection.** The fittest agent of each generation is automatically survived to the next. Elitism prevents populations from wholly regressing by mutation. 
