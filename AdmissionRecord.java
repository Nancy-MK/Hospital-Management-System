 

import java.util.Date;

public class AdmissionRecord {
    private Patient patient;
    private Ward ward;
    private Doctor doctor;
    private Date admissionDate;
    private String notes;  // Optional notes for the admission

    public AdmissionRecord(Patient patient, Doctor doctor, Ward ward) {
        this.patient = patient;
        this.ward = ward;
        this.doctor = doctor;
        this.admissionDate = new Date();  // Sets the current date/time
    }

    public Patient getPatient() {
        return patient;
    }

    public Ward getWard() {
        return ward;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Optional: Display summary for debug/logging
    @Override
    public String toString() {
        return
                "\n Doctor= " + doctor.getName() +
                "\n Ward= " + ward.getWardNumber() +
                "\n AdmissionDate= " + admissionDate +
                (notes != null ? ", notes='" + notes + '\'' : "") +
                '\n';
    }
}
