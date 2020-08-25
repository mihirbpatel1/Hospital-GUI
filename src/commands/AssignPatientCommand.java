package commands;

import entities.Patient;
import entities.Doctor;
import containers.PatientSetAccess;
import containers.DoctorSetAccess;

/**
 * Command to assign a patient to a doctor.
 */
public class AssignPatientCommand extends CommandStatus {
    /**
     * 
     * @param patientNum the health number of the patient
     * @param doctorName the name of the doctor
     */
    public void assignPatient(int patientNum, String doctorName) {
        successful = true;
        Doctor d = DoctorSetAccess.dictionary().get(doctorName);
        if (d == null) {
            successful = false;
            errorMessage = "There is no doctor with the name " + doctorName;
        }

        Patient p = PatientSetAccess.dictionary().get(patientNum);
        if (p == null) {
            if (successful) {
                successful = false;
                errorMessage = "There is no patient with the health number " + patientNum;
            } else
                errorMessage = errorMessage + " and there is no patient with the health number " + patientNum;
        }

        if (successful) {
            try {
                d.addPatient(p);
                p.addDoctor(d);
            } catch (RuntimeException e) {
                successful = false;
                errorMessage = e.getMessage();
            }
        }
    }
}