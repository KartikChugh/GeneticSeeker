package kc.ml.seeker.entities;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class Population {

    private Dot[] dots;

    public Population(int size, double posX, double posY) {
        dots = new Dot[size];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot(posX, posY);
        }
    }

    public boolean isMoving() {
        for (Dot dot : dots) {
            if (dot.isMoving()) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        for (Dot dot : dots) {
            if (dot.isMoving()) {
                dot.update();
            }
        }
    }

    private void evaluateFitness() {
        for (Dot dot : dots) {
            dot.evaluateFitness();
        }
    }

    private void reproduce(double mutationChance) {
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

    public void doNaturalSelection(double mutationChance) {
        evaluateFitness();
        reproduce(mutationChance);
    }

    public void draw(Graphics2D g2d) {
        for (Dot dot : dots) {
            dot.draw(g2d);
        }
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
