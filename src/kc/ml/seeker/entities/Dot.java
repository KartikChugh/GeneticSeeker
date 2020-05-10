package kc.ml.seeker.entities;

import java.awt.*;

import static kc.ml.seeker.main.SeekerPanel.*;

public class Dot extends Entity {

    // Functioning Hyperparameters
    private static final int NUM_GENES = 400;
    private static final double MAX_VELOCITY = 7.0;

    private final double posX_start;
    private final double posY_start;
    private double velX;
    private double velY;
    private double accX;
    private double accY;

    private double fitness;
    private boolean moving;
    private boolean mostFit;
    private final Genome genome;

    /**
     * Spawns a dot.
     * @param posX starting x position
     * @param posY starting y position
     * @param genome genetic instructions
     */
    private Dot(double posX, double posY, Genome genome) {
        super(posX, posY);
        posX_start = posX;
        posY_start = posY;
        this.genome = genome;
        initialize();
    }

    /**
     * Spawns a dot with a randomized genome.
     * @param posX starting x position
     * @param posY starting y position
     */
    Dot(double posX, double posY) {
        this(posX, posY, new Genome(NUM_GENES));
    }

    /**
     * Clones this dot with a mutated genome
     * @param mutationChance chance of gene mutation
     * @return cloned dot
     */
    Dot cloned(double mutationChance) {
        return new Dot(this.posX_start, this.posY_start, genome.cloned(mutationChance));
    }

    /**
     * Sets default field values upon construction
     */
    private void initialize() {
        setPosX(posX_start);
        setPosY(posY_start);
        velX = 0;
        velY = 0;
        accX = 0;
        accY = 0;
        moving = true;
        fitness = 0;
    }

    /**
     * Checks if dot can no longer move, otherwise moves it
     */
    public void update() {
        if (!genome.hasNextAcc() || isTouchingWall() || isTouchingGoal()) {
            moving = false;
        } else {
            move();
        }
    }

    private boolean isTouchingGoal() {
        //reachedGoal = true;
        return isTouching(goal);
    }

    private boolean isTouchingWall() {
        return (getPosX() < 0 || getPosX() > WIDTH-DIAMETER) || (getPosY() < 0 || getPosY() > HEIGHT-DIAMETER);
    }

    /**
     * Retrieves acceleration to update velocity and position
     */
    private void move() {
        final double[] acc = genome.nextAcc();
        accX = acc[0];
        accY = acc[1];

        velX += accX;
        velY += accY;
        velX = clamp(velX);
        velY = clamp(velY);

        changePosX(velX);
        changePosY(velY);
    }

    /**
     * Evaluates fitness based on distance
     */
    public void evaluateFitness() {
        final double dist = squareDistanceFrom(goal);
        final double cost = dist;

        fitness = 1/cost;
    }

    boolean isMoving() {
        return moving;
    }

    private double clamp(double num) {
        return Math.max(-MAX_VELOCITY, Math.min(MAX_VELOCITY, num));
    }

    @Override
    protected Color getColor() {
        return mostFit ? Color.GREEN : Color.BLACK;
    }

    public double getFitness() {
        return fitness;
    }

    public void setMostFit(boolean mostFit) {
        this.mostFit = mostFit;
    }
}
