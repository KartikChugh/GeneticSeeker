package kc.ml.seeker.entities;

import java.awt.*;

public class Goal extends Entity {

    public Goal(double posX, double posY) {
        super(posX, posY);
    }

    @Override
    protected Color getColor() {
        return Color.BLACK;
    }

    @Override
    protected int getDiameter() {
        return 15;
    }
}
