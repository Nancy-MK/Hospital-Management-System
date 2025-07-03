 
import java.util.List;
import java.util.ArrayList;

class Patient {
    private String id;
    private String name;
    private String gender;
    private ContactDetails contactDetails;
    private MedicalHistory medicalHistory;
    private InsuranceDetails insuranceDetails;
    private List<AdmissionRecord> admissions;
    private List<Medication> medications;

    public Patient(String id, String name, String gender, ContactDetails contactDetails,
                   MedicalHistory medicalHistory, InsuranceDetails insuranceDetails) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contactDetails = contactDetails;
        this.medicalHistory = medicalHistory;
        this.insuranceDetails = insuranceDetails;
        this.admissions = new ArrayList<>();
        this.medications = new ArrayList<>();
    }

    public  Patient(){

        this.id = "N/A";
        this.name = "N/A";
        this.gender = "N/A";
        this.contactDetails = null;
        this.medicalHistory = null;
        this.insuranceDetails = null;
        this.admissions = null;
        this.medications = null;
    }

    // Getter and Setter for medications
    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
    
    // Add new admission record, ensure no duplicate admission
    public void addAdmission(AdmissionRecord admission) {
        if (!admissions.contains(admission)) {
            this.admissions.add(admission);
        } else {
            System.out.println("Admission already exists for patient " + name);
        }
    }
    
    // Remove admission
    public void removeAdmission(AdmissionRecord admission) {
        if (admissions.contains(admission)) {
            this.admissions.remove(admission);
        } else {
            System.out.println("Admission not found for patient " + name);
        }
    }
    
    // Get all admissions for the patient
    public List<AdmissionRecord> getAdmissions() { 
        return admissions; 
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public InsuranceDetails getInsuranceDetails() {
        return insuranceDetails;
    }

    @Override
    public String toString() {
        return "======== Patient Details =======" +
               "\nPatient ID: " + id +
               "\nName: " + name +
               "\nGender: " + gender +
               "\n\nContact Info:" + contactDetails +
               "\n\nMedical History:" + medicalHistory +
               "\n\nInsurance Details:" + insuranceDetails +
               "\n\nAdmissions: " + (admissions.isEmpty() ? "No admissions yet" : admissions);
    }
}
