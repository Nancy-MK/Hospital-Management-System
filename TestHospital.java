import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestHospital {
    private HospitalImplementer hospital;
    private Patient patient;
    private Doctor doctor1;
    private Doctor doctor2;
    private Ward ward;
    private List<AdmissionRecord> admissionRecords;
    private List<TreatedRecord> treatedRecords;

    @BeforeEach
    public void setUp() {
        this.hospital = new HospitalImplementer();
        this.hospital.InitializeHospitalSystem(1, 2, 1);
        this.patient = this.hospital.getAllPatients().get(0);
        this.doctor1 = this.hospital.getDoctors().get(0);
        this.doctor2 = this.hospital.getDoctors().get(1);
        this.ward = this.hospital.getWards().get(0);
        this.admissionRecords = new ArrayList<>();
        this.treatedRecords = new ArrayList<>();
    }

    @Test
    public void testPatientAdmission() {
        AdmissionRecord admission1 = this.hospital.Admit(this.patient, this.doctor1, this.ward);
        AdmissionRecord admission2 = this.hospital.Admit(this.patient, this.doctor2, this.ward);

        Assertions.assertNotNull(admission1, "First admission should succeed");
        Assertions.assertNotNull(admission2, "Second admission should succeed");
        Assertions.assertEquals(this.patient, admission1.getPatient(), "Admission should be for the correct patient");
        Assertions.assertEquals(this.patient, admission2.getPatient(), "Admission should be for the correct patient");

        this.admissionRecords.add(admission1);
        this.admissionRecords.add(admission2);
    }

    @Test
    public void testPatientTreatment() {

        AdmissionRecord[] admissions = admitPatientToMultipleDoctors();
        AdmissionRecord admission1 = admissions[0];
        AdmissionRecord admission2 = admissions[1];

        TreatmentPlan plan1 = new TreatmentPlan(Arrays.asList("ECG", "Blood Test", "Medication"), "Rest and monitor vitals for 48 hours");
        TreatmentPlan plan2 = new TreatmentPlan(Arrays.asList("MRI", "Physical Therapy"), "Daily therapy sessions and monitoring");

        List<Medication> meds1 = Arrays.asList(
                new Medication("CardiacMed", "10mg", "Twice daily", 5),
                new Medication("Painkiller", "500mg", "As needed", 3)
        );

        List<Medication> meds2 = Arrays.asList(
                new Medication("NeuroMed", "5mg", "Once daily", 7),
                new Medication("MuscleRelaxant", "10mg", "Twice daily", 5)
        );

        TreatedRecord treated1 = this.hospital.Treat(admission1, plan1, meds1);
        TreatedRecord treated2 = this.hospital.Treat(admission2, plan2, meds2);

        Assertions.assertNotNull(treated1, "First treatment should succeed");
        Assertions.assertNotNull(treated2, "Second treatment should succeed");
        Assertions.assertEquals(meds1.size(), treated1.getMedications().size(), "All medications should be included");
        Assertions.assertEquals(meds2.size(), treated2.getMedications().size(), "All medications should be included");

        treated1.addCompletedProcedure("ECG");
        treated1.addCompletedProcedure("Blood Test");
        treated1.setPatientResponse("Patient responding well to treatment");

        treated2.addCompletedProcedure("MRI");
        treated2.setPatientResponse("Steady improvement observed");

        this.treatedRecords.add(treated1);
        this.treatedRecords.add(treated2);

        // Print treatment details
        System.out.println("=== Treatment Details ===");

        for (TreatedRecord tr : treatedRecords) {
            System.out.println("Doctor: " + tr.getDoctor().getName());
            System.out.println("Patient: " + tr.getPatient().getName());
            System.out.println("Procedures: " + tr.getCompletedProcedures());
            System.out.println("Patient Response: " + tr.getPatientResponse());
            System.out.println("Medications:");
            for (Medication m : tr.getMedications()) {
                System.out.printf(" - %s, %s, %s, Duration: %d days%n", m.getName(), m.getDosage(), m.frequency(), m.getDurationInDays());
            }

            System.out.println();
        }
        System.out.println("=== Treatment Score ===");
        double doctor1Score = treated1.calculateOutcomeScore();
        double doctor2Score = treated2.calculateOutcomeScore();

        System.out.printf("Dr. %s - Score: %.1f%n", doctor1.getName(), doctor1Score);
        System.out.printf("Dr. %s - Score: %.1f%n", doctor2.getName(), doctor2Score);
    }


    @Test
    public void testOutcomeEvaluationAndDischarge() {

        AdmissionRecord[] admissions = admitPatientToMultipleDoctors();
        AdmissionRecord admission1 = admissions[0];
        AdmissionRecord admission2 = admissions[1];


        TreatmentPlan plan1 = new TreatmentPlan(Arrays.asList("ECG", "Blood Test", "Medication"), "Rest and monitor vitals for 48 hours");
        TreatmentPlan plan2 = new TreatmentPlan(Arrays.asList("MRI", "Physical Therapy"), "Daily therapy sessions and monitoring");

        List<Medication> meds1 = Arrays.asList(
                new Medication("CardiacMed", "10mg", "Twice daily", 5),
                new Medication("Painkiller", "500mg", "As needed", 3)
        );

        List<Medication> meds2 = Arrays.asList(
                new Medication("NeuroMed", "5mg", "Once daily", 7),
                new Medication("MuscleRelaxant", "10mg", "Twice daily", 5)
        );

        TreatedRecord treated1 = this.hospital.Treat(admission1, plan1, meds1);
        TreatedRecord treated2 = this.hospital.Treat(admission2, plan2, meds2);

        treated1.addCompletedProcedure("ECG");
        treated1.addCompletedProcedure("Blood Test");
        treated1.setPatientResponse("Patient responding well to treatment");

        treated2.addCompletedProcedure("MRI");
        treated2.setPatientResponse("Steady improvement observed");

        double doctor1Score = treated1.calculateOutcomeScore();
        double doctor2Score = treated2.calculateOutcomeScore();

        System.out.printf("Dr. %s - Score: %.1f%n", this.doctor1.getName(), doctor1Score);
        System.out.printf("Dr. %s - Score: %.1f%n", this.doctor2.getName(), doctor2Score);

        TreatedRecord betterTreatment = doctor1Score > doctor2Score ? treated1 : treated2;
        Doctor betterDoctor = betterTreatment.getDoctor();

        // Print the winning doctor
        System.out.printf("\n>>> Winning Doctor: Dr. %s (Score: %.1f)\n",
                betterDoctor.getName(),
                Math.max(doctor1Score, doctor2Score));

        DischargeSummary summary = new DischargeSummary("Treatment completed successfully", "Follow-up in 2 weeks");
        BillingDetails billing = new BillingDetails(1500.0, this.patient.getInsuranceDetails());

        DischargedRecord discharged = this.hospital.Discharge(betterTreatment, summary, billing);

        Assertions.assertNotNull(discharged, "Discharge should be successful");
        Assertions.assertEquals(betterDoctor, discharged.getDoctor(), "Discharge should be for the doctor with better treatment outcome");
        Assertions.assertEquals(this.patient, discharged.getPatient(), "Discharged patient should match original patient");
        Assertions.assertTrue(this.ward.getBeds().stream().anyMatch(Bed::isAvailable), "At least one bed should be available after discharge");

        System.out.println("\n Discharged patient summary: \n" + discharged.summarizeDischarge());
    }

    private AdmissionRecord[] admitPatientToMultipleDoctors() {
        AdmissionRecord admission1 = this.hospital.Admit(this.patient, this.doctor1, this.ward);
        AdmissionRecord admission2 = this.hospital.Admit(this.patient, this.doctor2, this.ward);

        Assertions.assertNotNull(admission1, "Admission to doctor1 should succeed");
        Assertions.assertNotNull(admission2, "Admission to doctor2 should succeed");

        return new AdmissionRecord[]{admission1, admission2};
    }
}