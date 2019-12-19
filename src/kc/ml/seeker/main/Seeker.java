package kc.ml.seeker.main;

import javax.swing.*;

public class Seeker {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Seeker::init);
    }

    private static void init() {

        final JFrame frame = new JFrame("Seeker");
        frame.getContentPane().add(new SeekerPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
