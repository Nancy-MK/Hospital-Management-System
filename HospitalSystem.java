 
import java.util.*;
class HospitalSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Ward> wards;

    public HospitalSystem(int numOfPatients, int numOfWards, int numOfDoctors) {
        this.patients = new ArrayList<>(numOfPatients);
        this.doctors = new ArrayList<>(numOfDoctors);
        this.wards = new ArrayList<>(numOfWards);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addWard(Ward ward) {
        wards.add(ward);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Ward> getWards() {
        return wards;
    }
}
