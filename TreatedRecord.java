import java.util.ArrayList;
import java.util.List;

public class TreatedRecord extends AdmissionRecord {
    private TreatmentPlan treatmentPlan;
    private List<Medication> medications;
    private double outcomeScore;
    private String patientResponse;    // Added to track patient's response to treatment
    private List<String> completedProcedures; // Added to track completed procedures

    public TreatedRecord(Patient patient, Doctor doctor, Ward ward,
                         TreatmentPlan plan, List<Medication> meds) {
        super(patient, doctor, ward);
        this.treatmentPlan = plan;
        this.medications = meds;
        this.completedProcedures = new ArrayList<>();
        this.outcomeScore = calculateOutcomeScore();
    }

     double calculateOutcomeScore() {
        double score = 0.0; // Start from 0 instead of arbitrary 5.0

        // Calculate procedure completion rate (40% of total score)
        double procedureScore = (completedProcedures.size() / (double) treatmentPlan.getProcedures().size()) * 4.0;
        score += procedureScore;

        // Calculate medication adherence (30% of total score)
        double medicationScore = calculateMedicationScore();
        score += medicationScore;

        // Calculate treatment plan effectiveness (30% of total score)
        double treatmentScore = calculateTreatmentPlanScore();
        score += treatmentScore;

        // Round to one decimal place
        return Math.round(score * 10.0) / 10.0;
    }

    private double calculateMedicationScore() {
        if (medications.isEmpty()) return 0.0;

        double medicationScore = 0.0;
        for (Medication med : medications) {
            // Consider both duration and dosage compliance
            medicationScore += (med.getDurationInDays() * 0.1);
            // Add more medication-specific scoring criteria here
        }
        return Math.min(medicationScore, 3.0); // Cap at 30% of total score
    }

    private double calculateTreatmentPlanScore() {
        double treatmentScore = 0.0;

        // Evaluate treatment plan notes and effectiveness
        String notes = treatmentPlan.getNotes().toLowerCase();
        if (notes.contains("rest") || notes.contains("monitor")) {
            treatmentScore += 1.0;
        }
        if (notes.contains("therapy")) {
            treatmentScore += 1.0;
        }

        // Consider patient response if available
        if (patientResponse != null) {
            switch (patientResponse.toLowerCase()) {
                case "excellent":
                    treatmentScore += 1.0;
                    break;
                case "good":
                    treatmentScore += 0.7;
                    break;
                case "fair":
                    treatmentScore += 0.4;
                    break;
                // poor response adds nothing
            }
        }

        return Math.min(treatmentScore, 3.0); // Cap at 30% of total score
    }

    // Method to record completed procedures
    public void addCompletedProcedure(String procedure) {
        if (treatmentPlan.getProcedures().contains(procedure)
                && !completedProcedures.contains(procedure)) {
            completedProcedures.add(procedure);
            // Recalculate score when a procedure is completed
            this.outcomeScore = calculateOutcomeScore();
        }
    }

    // Method to set patient's response to treatment
    public void setPatientResponse(String response) {
        this.patientResponse = response;
        // Recalculate score when patient response is updated
        this.outcomeScore = calculateOutcomeScore();
    }

    // Getters
    public double getOutcomeScore() {
        return outcomeScore;
    }

    public TreatmentPlan getTreatmentPlan() {
        return treatmentPlan;
    }

    public List<Medication> getMedications() {
        return new ArrayList<>(medications); // Return defensive copy
    }

    public List<String> getCompletedProcedures() {
        return new ArrayList<>(completedProcedures); // Return defensive copy
    }

    public String getPatientResponse() {
        return patientResponse;
    }
}