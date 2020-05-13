package kc.ml.seeker.entities;

import java.util.Random;

import static kc.ml.seeker.main.SeekerPanel.rng;

class Genome {

    private final double[] directions;
    private int geneIndex;

    /**
     * Forms genome with random genes.
     * @param size number of genes
     */
    Genome(int size) {
        directions = new double[size];
        randomizeDirections();
    }

    // TODO directions constructor?
    /**
     * Copy constructor with mutation chance
     * @param genome genome to copy
     * @param mutationChance chance of mutating a gene
     */
    private Genome(Genome genome, double mutationChance) {
        directions = genome.directions.clone();
        mutateDirections(mutationChance);
    }

    /**
     * Clones this genome with mutated genes
     * @param mutationChance chance of mutating a gene
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
            final double mutationRoll = rng.nextDouble();
            if (mutationRoll < mutationChance) {
                directions[i] = rng.nextDouble() * 360.0;
            }
        }
    }

    /**
     * Indicates whether genome has a new instruction available
     * @return true if available, false otherwise
     */
    boolean hasNextAcc() {
        return geneIndex < directions.length;
    }

    /**
     * Returns an acceleration vector from the current genetic instruction
     * @return array of x and y accelerations
     */
    double[] nextAcc() {
        final double[] acc = new double[2];
        acc[0] = Math.cos(Math.toRadians(directions[geneIndex]));
        acc[1] = Math.sin(Math.toRadians(directions[geneIndex]));
        geneIndex++;
        return acc;
    }

}
