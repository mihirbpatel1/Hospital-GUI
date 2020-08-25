package gui;

import javax.swing.JFrame;
import startup.HospitalSystem;

public class OperationFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 350;
    public static final int DEFAULT_HEIGHT = 200;

    public OperationFrame() {
        setTitle("Hospital System Main Menu");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        OperationPanel panel = new OperationPanel();
        add(panel);
    }

    public static void main(String[] args) {
        
        HospitalSystem system = new HospitalSystem();
        system.initialize();
        OperationFrame frame = new OperationFrame();
        frame.setLocation(300,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static final long serialVersionUID = 1;
}