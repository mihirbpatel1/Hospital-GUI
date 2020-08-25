package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import javax.swing.JCheckBox;

import commands.AddDoctorCommand;

/**
 * The panel to obtain data for the creation of a patient, and to cause the patient to be created.
 */
public class DoctorAddPanel extends JPanel {


    /**
     * A text area to be used to display an error if one should occur.
     */
    JTextArea error = null;

    /**
     * A panel for the entry of the name of a new doctor.
     */
    ValueEntryPanel namePanel;

    /**
     * Determines if the doctor to be created is a surgeon or not
     */
    boolean isSurgeon = false;
    

    /**
     * Create the panel to obtain data for the creation of a doctor, and to cause the doctor to be
     * created.
     */
    public DoctorAddPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        // add a label with a prompt to enter the doctor data
        JLabel prompt = new JLabel("Enter Doctor Information");
        prompt.setMaximumSize(prompt.getPreferredSize());
        add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        namePanel = new ValueEntryPanel("Name");
        namePanel.setMaximumSize(namePanel.getPreferredSize());
        add(namePanel);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

    
        JCheckBox surgeonCheckBox = new JCheckBox("This doctor is a surgeon");
        
        surgeonCheckBox.setSelected(false);
        
        add(surgeonCheckBox);
      
        surgeonCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        surgeonCheckBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) { //Set the boolean true/false if checked/unchecked
                JCheckBox boxChecker = (JCheckBox) event.getSource();
                if (boxChecker.isSelected()) {
                    isSurgeon = true;
                }
                else isSurgeon = false;
            }
        });
        add(Box.createVerticalGlue());
    
       
     
        JButton submitButton = new JButton("Submit");
        submitButton.setMaximumSize(submitButton.getPreferredSize());
        add(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new SubmitListener());
        add(Box.createVerticalGlue());
    }

    /**
     * The class listening for the press of the submit button. It accesses the name and health
     * number entered, and uses them to add a new Patient to the system.
     */
    private class SubmitListener implements ActionListener {
      
        public void actionPerformed(ActionEvent event) {
            if (error != null) {
                
                remove(error);
                error = null;
            }
            String name = namePanel.getValueAsString();
            AddDoctorCommand addDoctor = new AddDoctorCommand();
            addDoctor.addDoctor(name, isSurgeon);
            if (addDoctor.wasSuccessful()) {
                getTopLevelAncestor().setVisible(false);
            } else {
                error = new JTextArea(SplitString.at(addDoctor.getErrorMessage(), 40));
                error.setMaximumSize(error.getPreferredSize());
                add(error);
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(Box.createVerticalGlue());
                revalidate(); 
            }
        }
    }

    public static final long serialVersionUID = 1;
}