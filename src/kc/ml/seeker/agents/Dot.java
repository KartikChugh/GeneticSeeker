package kc.ml.seeker.agents;

public class Dot {

    private double posX = 450;
    private double posY = 800;
    private double velX = 0;
    private double velY = 0;
    private double accX = 0;
    private double accY = 0;

    private final Genome genome;

    Dot() {
        genome = new Genome(1000);
    }

    public void move() {
        final double[] acc = genome.nextAcc();
        accX = acc[0];
        accY = acc[1];

        velX += accX;
        velY += accY;

        clamp(velX, -5, 5);
        clamp(velY, -5, 5);

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
