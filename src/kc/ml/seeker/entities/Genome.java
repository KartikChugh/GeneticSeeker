package kc.ml.seeker.entities;

import java.util.Arrays;
import java.util.Random;

class Genome {

    private static final Random SEEDER = new Random(13); // TODO remove
    private final double[] directions;
    private final Random randomMutator;
    private int geneIndex;

    Genome(int size) {
        randomMutator = new Random(SEEDER.nextInt());
        directions = new double[size];
        randomizeDirections();
    }

    // TODO directions constructor?
    private Genome(Genome genome, double mutationChance) {
        randomMutator = genome.randomMutator;
        directions = genome.directions.clone();
        mutateDirections(mutationChance);
    }

    Genome cloned(double mutationChance) {
        return new Genome(this, mutationChance);
    }

    private void randomizeDirections() {
        mutateDirections(1.00);
    }

    private void mutateDirections(double mutationChance) {
        for (int i = 0; i < directions.length; i++) {
            if (randomMutator.nextDouble() < mutationChance) {
                directions[i] = randomMutator.nextDouble() * 360.0;
            }
        }
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
