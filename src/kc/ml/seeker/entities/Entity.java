package kc.ml.seeker.entities;

import java.awt.*;

public abstract class Entity {

    protected static int DIAMETER = 10;
    private double posX;
    private double posY;

    Entity(double posX, double posY) {
        setPosX(posX);
        setPosY(posY);
    }

    public final double getPosX() {
        return posX;
    }

    public final double getPosY() {
        return posY;
    }

    final void setPosX(double posX) {
        this.posX = posX;
    }

    final void setPosY(double posY) {
        this.posY = posY;
    }

    final void changePosX(double posX) {
        this.posX += posX;
    }

    final void changePosY(double posY) {
        this.posY += posY;
    }

    private double getCenterX() {
        return posX + DIAMETER/2;
    }

    private double getCenterY() {
        return posY + DIAMETER/2;
    }

    final boolean isTouching(Entity o) {
        // TODO optimize center calculations
        return Math.pow(getCenterX() - o.getCenterX(), 2) + Math.pow(getCenterY() - o.getCenterY(), 2) <= Math.pow(DIAMETER, 2);
    }

    public final void draw(Graphics2D g2d) {
        g2d.setColor(getColor());
        g2d.fillOval((int) getPosX(), (int) getPosY(), DIAMETER, DIAMETER);
    }

    protected abstract Color getColor();

}
