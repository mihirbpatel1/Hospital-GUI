package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OperationPanel extends JPanel {
    public OperationPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

      
        JButton patientOpsButton = new JButton("Patient ");
        patientOpsButton.setMaximumSize(patientOpsButton.getPreferredSize());
        add(patientOpsButton);
        patientOpsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        patientOpsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                PatientOpsFrame frame = new PatientOpsFrame();
             
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());

        
        JButton doctorOpsButton = new JButton("Doctor");
        doctorOpsButton.setMaximumSize(doctorOpsButton.getPreferredSize());
        add(doctorOpsButton);
        doctorOpsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctorOpsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                DoctorOpsFrame frame = new DoctorOpsFrame();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());
        
 
        JButton wardInfoButton = new JButton("Ward Info");
        wardInfoButton.setMaximumSize(wardInfoButton.getPreferredSize());
        add(wardInfoButton);
        wardInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wardInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                WardOpsFarme frame = new WardOpsFarme();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());

        //Quit button
        JButton quitButton = new JButton("Quit");
        quitButton.setMaximumSize(quitButton.getPreferredSize());
        add(quitButton);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        quitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                Object[] quitOptions = {"Yes", "No"};
                int choice = JOptionPane.showOptionDialog(null, 
                            "Are you sure you want to quit?",
                            "Quit Hospital System",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            quitOptions,
                            quitOptions[1]);
                if (choice == 0) System.exit(0);
            }
        });
        add(Box.createVerticalGlue());
    }

    public static final long serialVersionUID = 1;
}