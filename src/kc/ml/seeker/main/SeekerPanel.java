package kc.ml.seeker.main;

import kc.ml.seeker.entities.Goal;
import kc.ml.seeker.entities.Population;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SeekerPanel extends JPanel {

    // Evolution Hyperparameters
    private static final double MUTATION_CHANCE = 0.005;
    private static final int POPULATION_SIZE = 1000;

    // Visual Settings
    public static int WIDTH;
    public static int HEIGHT;
    private static final int TPS_DESIRED = 100;

    public static Goal goal;
    private final Population population;
    private int gen = 0;

    SeekerPanel(int size) {
        initGUI(size);

        population = new Population(POPULATION_SIZE, WIDTH/2.0, HEIGHT-100);
        goal = new Goal(WIDTH/2.0, 100);

        final Timer timer = new Timer(1000/TPS_DESIRED, this::tick);
        timer.start();
    }

    private void initGUI(int size) {
        HEIGHT = size;
        WIDTH = size;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
    }

    private void tick(ActionEvent e) {
        if (population.isMoving()) {
            population.update();
            repaint();
        } else {
            population.doNaturalSelection(MUTATION_CHANCE);
            gen++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        population.draw(g2d);
        goal.draw(g2d);

        g2d.drawString("Gen: " + gen, 15, 15);
    }
}
