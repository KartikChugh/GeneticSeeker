package kc.ml.seeker.entities;

import java.awt.*;

import static kc.ml.seeker.main.SeekerPanel.*;

public class Dot extends Entity {

    private double velX = 0;
    private double velY = 0;
    private double accX = 0;
    private double accY = 0;

    private double fitness;
    private boolean moving = true;
    private final Genome genome;

    Dot(double posX, double posY) {
        super(posX, posY);
        genome = new Genome(1000);
    }

    public void update() {

        if (!genome.hasNextAcc() || isTouchingWall() || isTouchingGoal()) {
            moving = false;
        }

        if (!moving) {
            return;
        }

        move();
    }

    private boolean isTouchingGoal() {
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

    }

    boolean isMoving() {
        return moving;
    }

    private double clamp(double num, double min, double max) {
        return Math.max(min, Math.min(max, num));
    }

    @Override
    protected Color getColor() {
        return Color.BLACK;
    }
}
