package gui;

import javax.swing.JFrame;

import containers.DoctorSetAccess;
import entities.Doctor;

/**
 * The frame for the window to display the information for a doctor, and accept operations on the
 * doctor.
 */
public class DoctorFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 400;

    /**
     * Create the frame to display the information for a patient.
     * 
     * @param docName the name of the doctor
     * @precond docName has to be an existing doctor name
     */
    public DoctorFrame(String docName) {
        Doctor doc = DoctorSetAccess.dictionary().get(docName);
        if (doc != null) {
            setTitle(doc.getName());
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            DoctorPanel panel = new DoctorPanel(doc);
            add(panel);
        } else
            throw new RuntimeException("Invalid doctor name " + docName);
    }

    public static final long serialVersionUID = 1;
}