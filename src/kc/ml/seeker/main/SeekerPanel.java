package kc.ml.seeker.main;

import kc.ml.seeker.agents.Dot;
import kc.ml.seeker.agents.Population;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SeekerPanel extends JPanel {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;
    private static final int TPS = 30;

    private int tick = 0;
    private final Timer timer;
    private Population population;

    SeekerPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        population = new Population(100);

        timer = new Timer(1000/TPS, this::tick);
        timer.start();
    }

    private void tick(ActionEvent e) {
        repaint();
        for (Dot dot : population.getDots()) {
            dot.update();
        }
        tick++;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        for (Dot dot : population.getDots()) {
            g2d.fillOval((int) dot.getPosX(), (int) dot.getPosY(), Dot.RADIUS, Dot.RADIUS);
        }
    }
}
