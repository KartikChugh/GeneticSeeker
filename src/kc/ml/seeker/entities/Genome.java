package kc.ml.seeker.entities;

import java.util.Arrays;
import java.util.Random;

class Genome {

    private static final Random SEEDER = new Random(13); // TODO remove
    private final double[] directions;
    private final Random rng;
    private int geneIndex;

    Genome(int size) {
        rng = new Random(SEEDER.nextInt());
        directions = new double[size];
        randomizeDirections();
    }

    // TODO directions constructor
    Genome(Genome genome, double mutationChance) {
        rng = genome.rng;
        directions = Arrays.copyOf(genome.directions, genome.directions.length);
        mutateDirections(mutationChance);
    }

    private void randomizeDirections() {
        mutateDirections(1.00);
    }

    private void mutateDirections(double mutationChance) {
        for (int i = 0; i < directions.length; i++) {
            if (rng.nextDouble() < mutationChance) {
                directions[i] = rng.nextDouble() * 360.0;
            }
        }
    }

    int getGeneIndex() {
        return geneIndex;
    }

    boolean hasNextAcc() {
        return (geneIndex + 1) < directions.length;
    }

    double[] nextAcc() {
        final double[] acc = new double[2];
        acc[0] = Math.cos(directions[geneIndex]);
        acc[1] = Math.sin(directions[geneIndex]);
        geneIndex++;
        return acc;
    }

}
