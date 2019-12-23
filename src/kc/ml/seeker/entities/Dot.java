package kc.ml.seeker.entities;

import java.awt.*;

import static kc.ml.seeker.main.SeekerPanel.*;

public class Dot extends Entity implements Cloneable {

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

    private Dot(double posX, double posY, Genome genome) {
        super(posX, posY);
        posX_start = posX;
        posY_start = posY;
        this.genome = genome;
        initialize();
    }

    Dot(double posX, double posY) {
        this(posX, posY, new Genome(400));
    }

    Dot(Dot dot, double mutationChance) {
        this(dot.posX_start, dot.posY_start, new Genome(dot.genome, mutationChance));
    }

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

    public void update() {

        if (!genome.hasNextAcc() || isTouchingWall() || isTouchingGoal()) {
            moving = false;
            return;
        }

        move();
    }

    private boolean isTouchingGoal() {
        //reachedGoal = true;
        return isTouching(GOAL);
    }

    private boolean isTouchingWall() {
        return (getPosX() < 0 || getPosX() > WIDTH-DIAMETER) || (getPosY() < 0 || getPosY() > HEIGHT-DIAMETER);
    }

    private void move() {
        final double[] acc = genome.nextAcc();
        accX = acc[0];
        accY = acc[1];

        velX += accX;
        velY += accY;

        velX = clamp(velX, -7, 7);
        velY = clamp(velY, -7, 7);

        changePosX(velX);
        changePosY(velY);
    }

    public void evaluateFitness() {
        final double dist = squareDistanceFrom(GOAL);
        final double cost = dist;

        fitness = 1/cost;
    }

    boolean isMoving() {
        return moving;
    }

    private double clamp(double num, double min, double max) {
        return Math.max(min, Math.min(max, num));
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
