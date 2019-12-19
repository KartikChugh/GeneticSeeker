package kc.ml.seeker.entities;

import java.util.Random;

class Genome {

    private final double[] directions;
    private final Random rng;
    private int geneIndex;

    Genome(int size) {
        rng = new Random();
        directions = new double[size];

        for (int i = 0; i < directions.length; i++) {
            directions[i] = rng.nextDouble() * 360.0;
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
