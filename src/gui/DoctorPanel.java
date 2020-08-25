package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;

import commands.DropAssocCommand;
import commands.AssignPatientCommand;
import entities.Doctor;
import entities.Surgeon;

/**
 * The panel that displays information about a doctor in the system. Information includes the doctor's
 * name, their specialty (normal doctor or a surgeon), and a list of currently assigned patients.
 * Patient information can also be accessed through this window as well.
 * May have to implement the ability to assign a patient through this window.
 */
public class DoctorPanel extends JPanel {
    /**
     * Create the panel to display the doctor's information and accept operations on the doctor.
     * 
     * @param doctor the doctor whose information is to be displayed and on whom operations can be
     *        done
     */
    public DoctorPanel(Doctor doctor) {
      
        build(doctor);
    }

    /**
     * Fill in the panel to display the doctor's information and accept operations on the doctor.
     * 
     * @param doctor the doctor whose information is to be displayed and on whom operations can be
     *        done
     */
    private void build(Doctor doctor) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel docNameLabel = new JLabel("Name: " + doctor.getName());
        String docSpecialty = null; 
        if (doctor instanceof Surgeon) {
            docSpecialty = "Surgeon";
        }
        else docSpecialty = "Doctor";
        JLabel docSpecialtyLabel = new JLabel(docSpecialty);
        add(docNameLabel);
        docNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(docSpecialtyLabel);
        docSpecialtyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel assignPatientPanel = assignPatientPanel(doctor);
        add(assignPatientPanel);
        assignPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

      
        JPanel accessAssignedPatientPanel = accessAssignedPatientPanel(doctor);
        add(accessAssignedPatientPanel);
        accessAssignedPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

       
        JPanel unassignPatientPanel = unassignPatientPanel(doctor);
        add(unassignPatientPanel);
        unassignPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    /**
     * A panel that assigns a patient to a doctor. Takes in a patient number and searches for a patient with that number,
     * executing the appropriate command.
     *
     * @param doctor the current doctor
     * @return a panel to associate a new doctor with this patient
     */
    private JPanel assignPatientPanel(final Doctor doctor) {
        JPanel assignPatientPanel = new JPanel();
        assignPatientPanel.add(new JLabel("Assign patient by health number:"));
        final JTextField textField = new JTextField(10);
        assignPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String patientNumString = textField.getText();
                int patientNum = -1;
                if (patientNumString != null && patientNumString.length() > 0) {
                    try {
                        patientNum = Integer.parseInt(patientNumString);
                    }
                    catch (Exception e) {
                        textField.setText("Not int:" + textField.getText());
                        textField.revalidate();
                        return;
                    }
                }
                AssignPatientCommand addAssoc = new AssignPatientCommand();
                addAssoc.assignPatient(patientNum, doctor.getName());
                if (addAssoc.wasSuccessful()) {
                    
                    JOptionPane.showMessageDialog(null, "Patient successfully assigned");
                    removeAll();
                    build(doctor);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, addAssoc.getErrorMessage());
                }
            }
        });
        return assignPatientPanel;
    }

    /**
     * A panel that accesses a doctor's currently assigned patients. Takes in an integer input and if the patient
     * is assigned to the doctor its Patient panel is opened.
     *
     * @param doctor the current doctor
     * @return a panel to access a patient assigned to the doctor
     */
    private JPanel accessAssignedPatientPanel(final Doctor doctor) {
        JPanel accessAssignedPatientPanel = new JPanel();
        accessAssignedPatientPanel.add(new JLabel("Access assigned patient by health number"));
        final JTextField textField = new JTextField(10);
        accessAssignedPatientPanel.add(textField);
        textField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                String patientNumString = textField.getText();
                int patientNum = -1;
                if (patientNumString != null && patientNumString.length() > 0) {
                    try {
                        patientNum = Integer.parseInt(patientNumString);
                    }
                    catch (Exception e) {
                        textField.setText("Not int:" + textField.getText());
                        textField.revalidate();
                        return;
                    }
                }
                if (doctor.hasPatient(patientNum)) {
                    PatientFrame frame = new PatientFrame(patientNum);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setVisible(true);
                    textField.setText("");
                    textField.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Patient not assigned: " + textField.getText());
                    textField.revalidate();
                    return;
                }
            }
        });
        return accessAssignedPatientPanel;
    }

    /**
     * A panel that unassigns an assigned patient from the doctor by patient number. A text field takes in integer input
     * and unassigns the patient when it is assigned to the doctor, otherwise show an error message.
     *
     * @param doctor the current doctor
     * @return a panel to unassign the patient from this doctor
     */
    private JPanel unassignPatientPanel(final Doctor doctor) {
        JPanel unassignPatientPanel = new JPanel();
        unassignPatientPanel.add(new JLabel("Unassign an assigned patient by number"));
        final JTextField textField = new JTextField(10);
        unassignPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String patientNumString = textField.getText(); 
                int patientNum = -1;
                if (patientNumString != null && patientNumString.length() > 0) {
                    try {
                        patientNum = Integer.parseInt(patientNumString);
                    }
                    catch (Exception e) {
                        textField.setText("Not int:" + textField.getText()); //Error on non-int inputs
                        textField.revalidate();
                        return;
                    }
                }
                if (doctor.hasPatient(patientNum)) { 
                    DropAssocCommand unassignPatient = new DropAssocCommand();
                    unassignPatient.dropAssociation(doctor.getName(), patientNum);
                    if (unassignPatient.wasSuccessful()) {
                        JOptionPane.showMessageDialog(null,"Patient successfully unassigned"); 
                        return;
                    }
                    else {
                        JOptionPane.showMessageDialog(null, unassignPatient.getErrorMessage()); 
                        textField.setText("");
                        textField.revalidate();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Patient not assigned: " + textField.getText()); 
                    textField.setText("");
                    textField.revalidate();
                    return;
                }
            }
        });
        return unassignPatientPanel;
    }

    public static final long serialVersionUID = 1;
}