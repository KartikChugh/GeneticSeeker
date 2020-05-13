package kc.ml.seeker.entities;

import java.awt.*;

public abstract class Entity implements Drawable {

    private double posX;
    private double posY;

    Entity(double posX, double posY) {
        setPosX(posX);
        setPosY(posY);
    }

    protected abstract Color getColor();

    protected abstract int getDiameter();

    final double getPosX() {
        return posX;
    }

    final double getPosY() {
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
        return posX + getDiameter()/2.0;
    }

    private double getCenterY() {
        return posY + getDiameter()/2.0;
    }

    final double squareDistanceFrom(Entity o) {
        final double dx = getCenterX() - o.getCenterX();
        final double dy = getCenterY() - o.getCenterY();
        return dx*dx + dy*dy;
    }

    final boolean isTouching(Entity o) {
        double minDist = (getDiameter() + o.getDiameter())/2.0;
        return squareDistanceFrom(o) <= (minDist * minDist);
    }

    @Override
    public final void draw(Graphics2D g2d) {
        g2d.setColor(getColor());
        g2d.fillOval((int) getPosX(), (int) getPosY(), getDiameter(), getDiameter());
    }

}
