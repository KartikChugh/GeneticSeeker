![logo](/logo.png)

## Overview

Genetic Seeker is an evolutionary simulation in which agents compete to locate a moving target. The application is built in Swing and serves as a pure Java re-implementation of the [CodeBullet project](https://medium.com/@mik.szuga/the-genetic-algorithm-explained-with-intelligent-dots-319088f22d68) done in Processing.

## Evolutionary Design

- **Fitness-proportionate selection.** FPS offers a balance between evolutionary pressure and genetic diversity by selecting population members with a chance proportional to their fitness (e.g. a fitness of 4 would grant twice the odds of survival over a fitness of 2). This is more robust than unsophisticated algorithms like truncation selection, which are more likely to eliminate weaker members that possess _some_ useful genetic information.
- **Cloning with mutation.** Single-parent cloning is the simplest mechanism for genetic recombination, but is sufficient for this problem. Parents are selected each generation, and then duplicated until the population size fully recovers. Cloned genomes contain mutated genes, which explores the search space for new solutions.
- **Fitness score values results and efficiency.**
- **Genetic representation**
