package kc.ml.seeker.entities;

public class Population {

    private final Dot[] dots;

    public Population(int size, double posX, double posY) {
        dots = new Dot[size];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot(posX, posY);
        }
    }

    public Dot[] getDots() {
        return dots;
    }

    public boolean isMoving() {
        for (Dot dot : dots) {
            if (dot.isMoving()) {
                return true;
            }
        }
        return false;
    }

}
