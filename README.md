![logo](/logo.png)

## Overview

Genetic Seeker is an evolutionary simulation in which agents compete to locate a moving target with no information other than genetic instructions. The application is built in Swing and serves as a pure Java re-implementation of the [CodeBullet project](https://medium.com/@mik.szuga/the-genetic-algorithm-explained-with-intelligent-dots-319088f22d68) done in Processing.

<a href="https://github.com/KartikChugh/genetic-seeker/releases/latest/download/Seeker.jar">
<p align="center">
  <img src="/example-small.gif"> 
  <br>
  <img src="https://img.shields.io/github/v/release/KartikChugh/genetic-seeker.svg?logo=java&label=try%20it%20out&style=for-the-badge&color=red">
</p>
</a>

### Genetic Algorithms

GAs are a machine learning approach that employ random search and natural selection to discover heuristic solutions to optimization problems, as opposed to gradient-guided techniques. They are rooted in the idea that machine learning models can "evolve" to become better over time with some inspiration from biology. At the heart of the algorithm are _genes_, which underly the behavior of individuals and can spread among the broader population.

### Evolutionary Design
A brief summary of the genetic operators and design decisions in Seeker:

- **Fitness scores results and efficiency.** The fitness function scores agents first according to their distance from the target and second according to the steps they took to get there, to ensure convergence to a straight path. 
- **Fitness-proportionate selection.** FPS offers a balance between evolutionary pressure and genetic diversity by selecting population members with a chance proportional to their fitness (e.g. a fitness score of 6.0 provides twice the odds of survival over a score of 3.0).
- **Genetic representation.** An agent's genome consists of several hundred acceleration vectors, which are read sequentially to guide motion.
- **Cloning with mutation.** Single-parent cloning is the simplest mechanism for genetic recombination, but suffices for this problem. Parents are culled from each generation and cloned until repopulation, passing down their genes with small chances of point mutation (randomized new genes).
- **Elitist selection.** The fittest agent of each generation is automatically survived to the next. Elitism prevents populations from wholly regressing by mutation. 

## Experimentation

You can [run the latest version](https://github.com/KartikChugh/genetic-seeker/releases/latest/download/Seeker.jar) or tinker with the code to experiment with different settings. Note that you'll need Java 7 or up.

### Instructions

These instructions are for working with the code.

1. First, you'll need to clone this repo to your local machine, using `git clone https://github.com/KartikChugh/genetic-seeker.git` or [downloading it as a zip](https://github.com/KartikChugh/genetic-seeker/archive/master.zip).

2. Open the project in the editor of your choice (IntelliJ recommended) and make tweaks as desired. Most hyperparameters have been factored out to `SeekerPanel.java`, while a few remain in `Dot.java`. Collectively, these include:
	* **Mutation chance** (default: 0.5%)
	* **Population size** (1000)
	* **Seed for mutations/selection** (-1 for random seed)
	* **Genome length** (200)
	* **Max speed** (7.0)
