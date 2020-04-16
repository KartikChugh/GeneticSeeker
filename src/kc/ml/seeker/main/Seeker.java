package kc.ml.seeker.main;

import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Seeker {

    public static void main(String[] args) throws FileNotFoundException {
        //System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt"))));
        SwingUtilities.invokeLater(Seeker::init);
    }

    private static void init() {

        final JFrame frame = new JFrame("Genetic Seeker");
        frame.getContentPane().add(new SeekerPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
