![logo](/logo.png)

<a href="https://github.com/KartikChugh/genetic-seeker/releases/latest/download/Seeker.jar">
<p align="center">
  	<img src="https://img.shields.io/github/v/release/KartikChugh/genetic-seeker.svg?logo=java&label=run%20simulation&style=for-the-badge&color=red">
</p>
</a>

## Overview

Genetic Seeker is an evolutionary simulation in which agents compete to locate a moving target with no information other than genetic instructions. The application is built in Swing and serves as a pure Java re-implementation of the [CodeBullet project](https://medium.com/@mik.szuga/the-genetic-algorithm-explained-with-intelligent-dots-319088f22d68) done in Processing.

<a href="https://github.com/KartikChugh/genetic-seeker/releases/latest/download/Seeker.jar">
	<p align="center">
	  	<img src="/example.gif"> 
</p>
</a>

### Genetic Algorithms

GAs are a machine learning approach that employ random search and natural selection to discover heuristic solutions to optimization problems, as opposed to gradient-guided techniques. They are rooted in the idea that machine learning models can "evolve" to become better over time, with some inspiration from biology. At the heart of the algorithm are genes, which shape the behavior of individuals and spread among the broader population.

### Evolutionary Design
A brief summary of the genetic operators and design decisions in Seeker:

- **Fitness scores results and efficiency.** The fitness function scores agents first according to their distance from the target and second according to the steps they took to get there, to ensure convergence to a straight path. 
- **Fitness-proportionate selection.** FPS offers a balance between evolutionary pressure and genetic diversity by selecting population members with a chance proportional to their fitness (e.g. a fitness score of 6.0 provides twice the odds of survival over a score of 3.0).
- **Genetic representation.** An agent's genome consists of several hundred acceleration vectors, which are read sequentially to guide motion.
- **Visual phenotype.** Agents are colored differently based on their genetic codes. This tends to reveal clusters and highlights the growth and decline of sub-groups over time.
- **Cloning with mutation.** Single-parent cloning is the simplest mechanism for genetic recombination, but suffices for this problem. Parents are culled from each generation and cloned until repopulation, passing down their genes with small chances of point mutation (randomized new genes).
- **Elitist selection.** The fittest agent of each generation is automatically survived to the next. Elitism prevents populations from wholly regressing by mutation. 
- **Dynamic problem.** Every now and then, the target moves to a new location, putting selective pressure on the population learn a new path.

## Experimentation

You can [run the latest version](https://github.com/KartikChugh/genetic-seeker/releases/latest/download/Seeker.jar) or tinker with the code to experiment with different settings. Note that you'll need Java 7 or up.

### Instructions

These instructions are for working with the code.

1. First, you'll need to clone this repo to your local machine, using `git clone https://github.com/KartikChugh/genetic-seeker.git` or [downloading it as a zip](https://github.com/KartikChugh/genetic-seeker/archive/master.zip).

2. Open the project in the editor of your choice (IntelliJ recommended) and make tweaks as desired. Most hyperparameters have been factored out to `SeekerPanel.java`, while a few remain in `Dot.java`. Collectively, these include:
	* **Mutation chance** - a gene's odds of being randomized (_default_: 0.5%)
	* **Population size** - how many agents to spawn (1000)
	* **Seed** - seeds the random mutation/selection processes (-1 _for random seed_)
	* **Genome length** - number of instructions for an agent (scales to resolution height)
	* **Max speed** - terminal velocity for an agent (7.0)
	* **Relocation interval** - number of generations before the target moves (15)

## Updates

Genetic Seeker will most likely be rewritten using the Visua framework. Stay tuned!
