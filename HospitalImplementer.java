
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

// The HospitalImplementer class implements the HMSInterface
public class HospitalImplementer implements HMSInterface {
    private Map<String, List<Medication>> patientMedicationsMap = new HashMap<>();
    private List<Patient> allPatients = new ArrayList<>();
    private List<AdmissionRecord> currentAdmissions = new ArrayList<>();
    private List<TreatedRecord> allTreatedRecords = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Ward> wards = new ArrayList<>();  // List<Ward> instead of String[] for wards

    // Initialize the Hospital System
    @Override
    public void InitializeHospitalSystem(int totalPatients, int totalDoctors, int totalWards) {
        if (totalDoctors == 0) {
            System.out.println("Error: Cannot initialize hospital system with zero doctors.");
            return;
        }

        // Initialize the lists with the specified sizes
        this.allPatients = new ArrayList<>(totalPatients);
        this.doctors = new ArrayList<>(totalDoctors);
        this.wards = new ArrayList<>(totalWards);

        String[] doctorsNames = {"Mahmoud", "Maha", "Saif"};
        String[] names = {"Nancy", "Bob", "Jana", "Hana", "Julia"};
        String[] genders = {"Female", "Male"};
        String[] Specialization ={"nero","cardio","blood"};
        String[] wardNames = {"W1001", "W1002"};
        String[] InsuranceproviderName = {"صيدليات سيف","y.co."};
        // Initialize wards
        for (int i = 0; i < totalWards; i++) {
            String wardName =  wardNames[i % names.length];
            int wardNumber = i + 1;

            List<Bed> beds = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                beds.add(new Bed(j + 1, true)); // You can set how many beds per ward here
            }

            List<Equipment> equipment = new ArrayList<>();
            equipment.add(new Equipment("ECG", "Diagnostic", true));
            equipment.add(new Equipment("IV Pump", "Support", false));  // maybe not operational

            wards.add(new Ward(wardName, wardNumber, beds, equipment));
        }

        // Sample data for patients
        for (int i = 0; i < totalPatients; i++) {
            List<Medication> medications = new ArrayList<>();
            if (i % 2 == 0) {
                medications.add(new Medication("Paracetamol", (250 + i * 10) + "mg", "Take twice daily", 3 + (i % 5)));
                medications.add(new Medication("Ibuprofen", (200 + i * 5) + "mg", "Take once daily", 5 + (i % 7)));
            } else {
                medications.add(new Medication("Amoxicillin", (500 + i * 5) + "mg", "Take after meals", 7 + (i % 3)));
                medications.add(new Medication("Vitamin D", (1000 + i * 10) + "IU", "Take every morning", 10 + (i % 4)));
            }

            String patientId = "P" + (100 + i);
            String name = names[i % names.length] ;
            String gender = genders[i % genders.length];
            String address = (100 + i) + " Main Street, Zone " + (i % 5);
            String emergencyPhone = "012" + (70000000 + i);
            String personalPhone = "011" + (10000000 + i);

            String providerName = InsuranceproviderName[i % genders.length];
            String policyNumber = "POL" + (10000 + i);
            String coverageType = (i % 3 == 0) ? "full" : (i % 3 == 1) ? "half" : "none";
            String expiryDate = String.format("%02d/%02d/%d", (i % 12) + 1, (i % 28) + 1, 2026 + (i % 5));

            // Create new patient with a unique name
            Patient patient = new Patient(
                    patientId,
                    name,
                    gender,
                    new ContactDetails(address, emergencyPhone, personalPhone),
                    new MedicalHistory(List.of("None"), List.of("None"), List.of("None"), List.of("No allergies")),
                    new InsuranceDetails(providerName, policyNumber, coverageType, expiryDate)
            );

            // Assign medications to patient by name
            patientMedicationsMap.put(patient.getName(), medications);

            // Assign patient to an available bed
            boolean assigned = false;
            for (Ward ward : wards) {
                Bed availableBed = findAvailableBed(ward);
                if (availableBed != null) {
                    availableBed.setAvailable(false);
                    availableBed.setPatient(patient);
                    assigned = true;
                    break;
                }
            }

            if (!assigned) {
                System.out.println("No available beds for patient: " + patient.getName());
            }

            // Add patient to the allPatients list
            allPatients.add(patient);
        }

        // Initialize sample data for doctors and assign them to wards
        for (int i = 0; i < totalDoctors; i++) {

            Specialization specialization = new Specialization(Specialization[i % Specialization.length]);

            Doctor doctor = new Doctor(
                    "Doctor" + (i + 1),               // Doctor ID
                      (doctorsNames[i %doctorsNames.length]), // Doctor Name
                    specialization,                    // Specialization
                    new Schedule(                      // Schedule (Available Days and Times)
                            List.of(LocalDate.now()), // Assign available day (e.g., today)
                            List.of(java.time.LocalTime.of(9, 0)) // Assign available time (e.g., 9:00 AM)
                    )
            );

            if (!wards.isEmpty()) {
                Ward assignedWard = wards.get(i % wards.size());
                doctor.assignToWard(assignedWard);
            }
            doctors.add(doctor);
        }

        // Display information about the initialized hospital system
        System.out.println("        *** Hospital System Initialized ***\n");
        System.out.println(" > Total Patients: " + totalPatients);
        System.out.println(" > Total Doctors : " + totalDoctors);
        System.out.println(" > Total Wards   : " + totalWards + "\n");

        System.out.println("List of Patients:");
        for (Patient p : allPatients) {
            System.out.println(" - " + p.getId() + ": " + p.getName());
        }

        System.out.println("\nList of Doctors:");
        for (Doctor d : doctors) {
            System.out.println(" - " + d.getId() + ": " + d.getName() + " (" + d.getSpecialization() + ")");
        }

        System.out.println("\nList of Wards:");
        for (Ward w : wards) {
            System.out.println(" - Ward " + w.getWardNumber() + ", Beds occupied: " + w.getBeds().stream().filter(b -> !b.isAvailable()).count());
        }

        System.out.println();
    }

    // Admit a patient
    @Override
    public AdmissionRecord Admit(Patient patient, Doctor doctor, Ward ward) {
        for (AdmissionRecord r : currentAdmissions) {
            if (r.getPatient().equals(patient) && r.getDoctor().equals(doctor) && r.getWard().equals(ward)) {
                return null; // Already admitted to this doctor and ward
            }
        }

        Bed availableBed = findAvailableBed(ward);
        if (availableBed == null) {
            printAvailableBeds(ward);
            System.out.println("No available beds in ward " + ward.getWardNumber());
            return null;
        }

        availableBed.setAvailable(false);
        availableBed.setPatient(patient);

        AdmissionRecord admissionRecord = new AdmissionRecord(patient, doctor, ward);
        currentAdmissions.add(admissionRecord);

        return admissionRecord;
    }

    private Bed findAvailableBed(Ward ward) {
        return ward.getBeds().stream()
                .filter(Bed::isAvailable)
                .findFirst()
                .orElse(null);
    }

    private void printAvailableBeds(Ward ward) {
        System.out.println("Checking available beds in ward " + ward.getWardNumber() + ":");
        ward.getBeds().forEach(bed -> System.out.println("Bed " + bed.getBedNumber() + " is " + (bed.isAvailable() ? "available" : "occupied")));
    }

    // Treat the patient
    @Override
    public TreatedRecord Treat(AdmissionRecord admissionRecord, TreatmentPlan treatmentPlan, List<Medication> medications) {
        if (admissionRecord == null) {
            System.out.println("Error: No valid admission record found for treatment.");
            return null;
        }

        TreatedRecord treatedRecord = new TreatedRecord(
                admissionRecord.getPatient(),
                admissionRecord.getDoctor(),
                admissionRecord.getWard(),
                treatmentPlan,
                medications
        );

        allTreatedRecords.add(treatedRecord);
        return treatedRecord;
    }

    // Discharge the patient
    @Override
    public DischargedRecord Discharge(TreatedRecord anyTreatedRecord, DischargeSummary dischargeSummary, BillingDetails billingDetails) {
        Patient patient = anyTreatedRecord.getPatient();

        List<AdmissionRecord> patientAdmissions = currentAdmissions.stream()
                .filter(ad -> ad.getPatient().equals(patient))
                .collect(Collectors.toList());

        List<TreatedRecord> patientTreatments = allTreatedRecords.stream()
                .filter(tr -> tr.getPatient().equals(patient))
                .collect(Collectors.toList());

        if (patientAdmissions.size() != patientTreatments.size()) {
            System.out.println(" !!! Not all treatments completed for patient '" + patient.getName() + "'. Cannot discharge.");
            return null;
        }

        // Ensure doctor and ward are available in this context
        Doctor doctor = anyTreatedRecord.getDoctor();
        Ward ward = anyTreatedRecord.getWard();

        // Release the bed
        for (AdmissionRecord ar : patientAdmissions) {
            ar.getWard().getBeds().stream()
                    .filter(bed -> !bed.isAvailable())
                    .findFirst()
                    .ifPresent(bed -> bed.setAvailable(true));
        }

        currentAdmissions.removeIf(ad -> ad.getPatient().equals(patient));

        // Calculate cost and handle insurance
        double totalCost = billingDetails.getTotalCost();
        double insuranceCoverage = billingDetails.getInsuranceCoverage();
        String coverageType = billingDetails.getCoverageType();

        String coverageAmount = String.valueOf(insuranceCoverage);
        InsuranceDetails insuranceDetails = new InsuranceDetails(coverageAmount, coverageType, patient.getInsuranceDetails().getCoverageType(), "2/6/2029");

        // Prepare the DischargedRecord
        TreatmentPlan treatmentPlan = anyTreatedRecord.getTreatmentPlan();

        DischargedRecord dischargedRecord = new DischargedRecord(
                patient,
                doctor,            // Now correctly using doctor
                ward,              // Now correctly using ward
                treatmentPlan,
                anyTreatedRecord.getMedications(),
                dischargeSummary,
                totalCost,         // Pass totalCost as a double
                insuranceDetails   // Pass InsuranceDetails
        );
        return dischargedRecord;
    }


    // Getter method to retrieve all doctors
    public List<Doctor> getDoctors() {
        return doctors;
    }
    // Getter method to retrieve all doctors
    public List<Patient> getAllPatients() {
        return allPatients;
    }
    // Getter method to retrieve all doctors
    public List<Ward> getWards() {
        return wards;
    }

}
