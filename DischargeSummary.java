 
import java.time.LocalDate;

class DischargeSummary {
    private String finalDiagnosis;
    private String followUpInstructions;
    private LocalDate dischargeDate;

    public DischargeSummary(String finalDiagnosis, String followUpInstructions) {
        if (finalDiagnosis == null || finalDiagnosis.trim().isEmpty()) {
            throw new IllegalArgumentException("Diagnosis cannot be empty.");
        }
        this.finalDiagnosis = finalDiagnosis.trim();
        this.followUpInstructions = followUpInstructions != null ? followUpInstructions.trim() : "None";
        this.dischargeDate = LocalDate.now(); // Default to today
    }

    public String getFinalDiagnosis() {
        return finalDiagnosis;
    }

    public String getFollowUpInstructions() {
        return followUpInstructions;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    @Override
    public String toString() {
        return "Diagnosis: " + finalDiagnosis +
               "\nFollow-Up: " + followUpInstructions +
               "\nDischarge Date: " + dischargeDate;

    }
}
