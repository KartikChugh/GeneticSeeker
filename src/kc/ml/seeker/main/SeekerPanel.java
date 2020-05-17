package kc.ml.seeker.main;

import kc.ml.seeker.entities.Goal;
import kc.ml.seeker.entities.Population;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class SeekerPanel extends JPanel {

    // Evolution Parameters
    private static final double MUTATION_CHANCE = 0.005;
    private static final int POPULATION_SIZE = 1000;
    private static final long SEED = -1; // 222 - competition, 333 - convergence

    // Visual Parameters
    public static int WIDTH;
    public static int HEIGHT;
    public static final double DT = 0.01;
    private static final int TPS_DESIRED = 100;
    private static final int TPS_INTERVAL = TPS_DESIRED;

    // Timing
    public static double deltaTime;
    public static double dt_accumulator;
    private int ticks;
    private long t;
    private double dt_interval;
    private int tps;

    public static Random rng;
    public static Goal goal;
    private final Population population;
    private int gen = 0;

    SeekerPanel(int size) {
        initGUI(size);

        rng = new Random();
        if (SEED != -1) rng.setSeed(SEED);

        population = new Population(POPULATION_SIZE, WIDTH/2.0, HEIGHT-100);
        goal = new Goal(WIDTH/2.0, 100);

        t = System.currentTimeMillis();
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
        updateTime();
        if (population.isMoving()) {
            while (dt_accumulator >= DT) {
                population.update();
                dt_accumulator -= DT;
            }
            repaint();
        } else {
            population.doNaturalSelection(MUTATION_CHANCE);
            gen++;
        }
        ticks++;
    }

    private void updateTime() {
        long t_ = System.currentTimeMillis();
        deltaTime = (t_ - t)/1000.0;
        t = t_;
        dt_accumulator += deltaTime;

        // Re-calculate tps every TPS_INTERVAL milliseconds
        if (ticks % TPS_INTERVAL == 0) {
            tps = (int) (TPS_INTERVAL/dt_interval);
            dt_interval = 0;
        } else {
            dt_interval += deltaTime;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        goal.draw(g2d);
        population.draw(g2d);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Gen: " + gen, 15, 15);
        g2d.drawString("Frames: " + tps, WIDTH-60, 15);
    }
}
