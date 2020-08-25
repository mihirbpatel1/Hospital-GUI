package gui;

import javax.swing.JFrame;

/**
 * The frame for the window to display everything related to Ward information and ward related operaitons
 */
public class WardOpsFarme extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 400;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 300;

    /**
     * Create the frame for the Ward information, and stuff involving ward operations
     */
    public WardOpsFarme() {
        setTitle("Ward Information");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        WardOpsPanel panel = new WardOpsPanel();
        add(panel);
    }

    /**
     * Main method that creates and instantiates this frame
     */
    public static void main(String[] args) {
        WardOpsFarme frame = new WardOpsFarme();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static final long serialVersionUID = 1;
}