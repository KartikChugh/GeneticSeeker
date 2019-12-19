package kc.ml.seeker.agents;

import static kc.ml.seeker.main.SeekerPanel.*;

public class Dot {

    public static int RADIUS = 10;

    private double posX = WIDTH/2;
    private double posY = HEIGHT-100;
    private double velX = 0;
    private double velY = 0;
    private double accX = 0;
    private double accY = 0;

    private boolean moving = true;
    private final Genome genome;

    Dot() {
        genome = new Genome(1000);
    }

    public void update() {

        if (!genome.hasNextAcc() || isTouchingWall()) {
            moving = false;
        }

        if (!moving) {
            return;
        }

        move();
    }

    private boolean isTouchingWall() {
        return (posX < 0 || posX > WIDTH-RADIUS) || (posY < 0 || posY > HEIGHT-RADIUS);
    }

    private void move() {
        final double[] acc = genome.nextAcc();
        accX = acc[0];
        accY = acc[1];

        velX += accX;
        velY += accY;

        velX = clamp(velX, -7, 7);
        velY = clamp(velY, -7, 7);

        posX += velX;
        posY += velY;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    private double clamp(double num, double min, double max) {
        return Math.max(min, Math.min(max, num));
    }

}
