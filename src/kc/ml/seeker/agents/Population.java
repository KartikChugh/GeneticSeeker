package kc.ml.seeker.agents;

public class Population {

    private final Dot[] dots;

    public Population(int size) {
        dots = new Dot[size];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
        }
    }

    public Dot[] getDots() {
        return dots;
    }

}
