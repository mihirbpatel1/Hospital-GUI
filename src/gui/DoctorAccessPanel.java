package gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * The panel for accessing a certain doctor in the system. A doctor is to be searched by name, and if a match occurs
 * a new window will open containing all possible operations for this doctor, including information display
 * and assigning any patients.
 */
public class DoctorAccessPanel extends JPanel {
    /**
     * The text field for the entry of the doctor's health number
     */
    JTextField textField;

    /**
     * Create the panel with the prompt label and text field. If data is entered into the text field
     * that is not a valid int value, a brief error message is entered at the front of the text
     * field. Otherwise, a new window is created with the patient's data and operations on the
     * patient.
     */
    public DoctorAccessPanel() {
        JLabel promptLabel = new JLabel("Access doctor by name:");
        add(promptLabel);
        textField = new JTextField(10);
        add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String valueAsString = textField.getText();
                if (valueAsString != null && valueAsString.length() > 0) {
                    DoctorFrame frame = null;
                    try {
                        frame = new DoctorFrame(valueAsString); 
                    }
                    catch(Exception e) {
                        textField.setText("Doctor not found");
                        textField.revalidate();
                        return;
                    }
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setVisible(true);
                    textField.setText("");
                    textField.revalidate();
                } else {
                    textField.setText("Empty field: " + textField.getText());
                    textField.revalidate();
                }
            }
        });
    }

    public static final long serialVersionUID = 1;
}