package kc.ml.seeker.entities;

import java.util.Arrays;
import java.util.Random;

class Genome {

    private static final Random SEEDER = new Random(13); // TODO remove
    private final double[] directions;
    private final Random randomMutator;
    private int geneIndex;

    /**
     * Forms genome with random genes.
     * @param size number of genes
     */
    Genome(int size) {
        randomMutator = new Random(SEEDER.nextInt());
        directions = new double[size];
        randomizeDirections();
    }

    // TODO directions constructor?
    /**
     * Copy constructor with mutation chance
     * @param genome
     * @param mutationChance
     */
    private Genome(Genome genome, double mutationChance) {
        randomMutator = genome.randomMutator;
        directions = genome.directions.clone();
        mutateDirections(mutationChance);
    }

    /**
     * Clones this genome with mutated genes
     * @param mutationChance
     * @return mutated genome
     */
    Genome cloned(double mutationChance) {
        return new Genome(this, mutationChance);
    }

    /**
     * Completely randomizes all genes
     */
    private void randomizeDirections() {
        mutateDirections(1.00);
    }

    /**
     * Randomizes genes by chance
     * @param mutationChance chance of randomizing a gene
     */
    private void mutateDirections(double mutationChance) {
        for (int i = 0; i < directions.length; i++) {
            if (randomMutator.nextDouble() < mutationChance) {
                directions[i] = randomMutator.nextDouble() * 360.0;
            }
        }
    }

    /**
     * Indicates whether genome has a new instruction available
     * @return true if available, false otherwise
     */
    boolean hasNextAcc() {
        return (geneIndex + 1) < directions.length;
    }

    /**
     * Returns an acceleration vector from the current genetic instruction
     * @return array of x and y accelerations
     */
    double[] nextAcc() {
        final double[] acc = new double[2];
        acc[0] = Math.cos(directions[geneIndex]);
        acc[1] = Math.sin(directions[geneIndex]);
        geneIndex++;
        return acc;
    }

}
