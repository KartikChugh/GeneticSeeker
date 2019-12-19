package kc.ml.seeker.main;

import kc.ml.seeker.entities.Dot;
import kc.ml.seeker.entities.Goal;
import kc.ml.seeker.entities.Population;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SeekerPanel extends JPanel {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;
    private static final int TPS = 30;
    public static final Goal GOAL = new Goal(WIDTH/2, 100);

    private int gen = 0;
    private int tick = 0;
    private final Timer timer;
    private Population population;

    SeekerPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        population = new Population(100, WIDTH/2, HEIGHT-100);

        timer = new Timer(1000/TPS, this::tick);
        timer.start();
    }

    private void tick(ActionEvent e) {
        if (!population.isMoving()) {
            gen++;
        }

        for (Dot dot : population.getDots()) {
            dot.update();
        }
        repaint();
        tick++;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        for (Dot dot : population.getDots()) {
            dot.draw(g2d);
        }
        GOAL.draw(g2d);

        //g2d.drawString("Gen: " + gen, 15, 15);
    }
}
