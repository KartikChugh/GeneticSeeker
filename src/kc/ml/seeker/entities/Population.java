package kc.ml.seeker.entities;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;

public class Population implements Drawable {

    private Dot[] dots;

    /**
     * Spawns a population of dots.
     * @param size number of dots in the population
     * @param posX starting x position
     * @param posY starting y position
     */
    public Population(int size, double posX, double posY) {
        dots = new Dot[size];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot(posX, posY);
        }
    }

    /**
     * Returns whether any of the dots is moving.
     * @return true if any of the dots is moving, false otherwise.
     * @see Dot#isMoving()
     */
    public boolean isMoving() {
        for (Dot dot : dots) {
            if (dot.isMoving()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates all moving dots.
     * @see Dot#update()
     */
    public void update() {
        for (Dot dot : dots) {
            if (dot.isMoving()) {
                dot.update();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (Dot dot : dots) {
            dot.draw(g2d);
        }
    }

    /**
     * Evaluates fitness of each dot and performs reproduction accordingly.
     * @param mutationChance chance of a dot's gene mutating
     */
    public void doNaturalSelection(double mutationChance) {
        evaluateFitness();
        dots = reproduce(mutationChance);
    }

    private void evaluateFitness() {
        for (Dot dot : dots) {
            dot.evaluateFitness();
        }
    }

    /**
     * Produces the next generation of dots from the existing one
     * @param mutationChance chance of a dot's gene mutating
     * @return
     */
    private Dot[] reproduce(double mutationChance) {
        final int populationSize = dots.length;
        final Dot[] descendants = new Dot[populationSize];
        final Dot mostFit = Arrays.stream(dots).max(Comparator.comparingDouble(Dot::getFitness)).get();
        final Dot mostFitClone = mostFit.cloned(0);
        mostFitClone.setMostFit(true);
        descendants[populationSize-1] = mostFitClone;

        for (int i = 0; i < populationSize-1; i++) {
            final Dot parent = selectParent();
            final Dot child = parent.cloned(mutationChance);
            descendants[i] = child;
        }

        dots = descendants;
    }

    // TODO optimize fitness proportionate selection
    private Dot selectParent() {
        final double cumulativeFitness = Arrays.stream(dots).mapToDouble(Dot::getFitness).sum();
        final double threshold = Math.random() * cumulativeFitness;

        double runningSum = 0;
        // FIXME biased towards beginning of array
        for (Dot dot : dots) {
            runningSum += dot.getFitness();
            if (runningSum > threshold) {
                return dot;
            }
        }

        return null;
    }
}
