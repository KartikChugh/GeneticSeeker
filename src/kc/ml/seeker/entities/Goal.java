package kc.ml.seeker.entities;

import static kc.ml.seeker.main.SeekerPanel.*;

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

    public void relocate(double bounds) {
        double randomX = (WIDTH - bounds)/2.0; // min
        randomX += rng.nextDouble() * bounds;
        setPosX(randomX);
    }

}
