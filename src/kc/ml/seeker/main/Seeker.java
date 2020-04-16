package kc.ml.seeker.main;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Seeker {

    public static void main(String[] args) throws FileNotFoundException {
        SwingUtilities.invokeLater(Seeker::init);
    }

    private static void init() {

        final JFrame frame = new JFrame("Genetic Seeker");
        frame.getContentPane().add(new SeekerPanel(computeSize()));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * Computes dimensions of the panel.
     * @return length of one side of the square
     */
    public static int computeSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets bounds = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration());
        // Heuristic - height minus twice the taskbar size
        final double size = screenSize.getHeight() - 2 * (bounds.top + bounds.bottom);
        return (int) size;
    }

}
