 

import java.util.List;

class DischargedRecord extends TreatedRecord {
    private DischargeSummary dischargeSummary;
    private BillingDetails billingDetails;

    public DischargedRecord(
            Patient patient,
            Doctor doctor,
            Ward ward,
            TreatmentPlan treatmentPlan,
            List<Medication> medications,
            DischargeSummary dischargeSummary,
            double totalCost,
            InsuranceDetails insuranceDetails // Pass InsuranceDetails instead of a double for coverage type
    ) {
        super(patient, doctor, ward, treatmentPlan, medications);
        this.dischargeSummary = dischargeSummary;

        // Create a BillingDetails object based on the insurance details
        this.billingDetails = new BillingDetails(totalCost, insuranceDetails);
    }

    public DischargeSummary getDischargeSummary() {
        return dischargeSummary;
    }

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setDischargeSummary(DischargeSummary dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }

    public String summarizeDischarge() {
        StringBuilder summary = new StringBuilder();

        summary.append("       ===>>>    Patient SUMMARY    <<<=== \n");

        // Patient Info
        summary.append(">> Patient Information:\n");
        summary.append("   - Name: ").append(getPatient().getName()).append("\n");
        summary.append("   - Gender: ").append(getPatient().getGender()).append("\n");
        summary.append("   - ID: ").append(getPatient().getId()).append("\n");

        // Contact Details
        ContactDetails contact = getPatient().getContactDetails();
        InsuranceDetails insurance = getPatient().getInsuranceDetails();
        summary.append("   - Contact details:\n");
        summary.append("      . Address: ").append(contact.getAddress()).append("\n");
        summary.append("      . Phone: ").append(contact.getPhoneNumber()).append("\n");
        summary.append("      . Emegency Contact: ").append(contact.getEmergencyContact()).append("\n");

        if (insurance != null) {
            summary.append("   - InsuranceDetails: ").append(insurance.toString()).append("\n\n");
        } else {
            summary.append("   - InsuranceDetails: None\n\n");
        }

        // Doctor & Ward Info
        summary.append(">>> Doctor & Ward:\n");
        summary.append("    - Attending Doctor: ").append(getDoctor().getName()).append(" (")
                .append(getDoctor().getSpecialization().getField()).append(")\n");
        summary.append("    - Ward Number: ").append(getWard().getWardNumber()).append("\n\n");

        // Treatment Plan
        TreatmentPlan plan = getTreatmentPlan();
        summary.append(">>> Treatment Plan:\n");
        summary.append("    - Procedures: ").append(String.join(", ", plan.getProcedures())).append("\n");
        summary.append("    - Notes: ").append(plan.getNotes()).append("\n\n");

        // Medications
        summary.append(">>> Medications:\n");
        for (Medication med : getMedications()) {
            summary.append("    - ").append(med.getName())
                    .append(" (").append(med.getDosage()).append(", ")
                    .append(med.getDurationInDays()).append(" days)\n");
        }
        summary.append("\n");

        // Discharge Summary
        summary.append(">>> Discharge Notes:\n");
        summary.append("    - Discharge Status: ").append(dischargeSummary.getFinalDiagnosis()).append("\n");
        summary.append("    - Follow-Up: ").append(dischargeSummary.getFollowUpInstructions()).append("\n");
        summary.append("    - Discharge Date: ").append(java.time.LocalDate.now()).append("\n\n");

        // Billing
        summary.append(">>> Billing Details:\n");
        summary.append("    - Total Cost: $").append(String.format("%.2f", billingDetails.getTotalCost())).append("\n");
        summary.append("    - Covered by Insurance: $").append(String.format("%.2f", billingDetails.getInsuranceCoverage())).append("\n");
        summary.append("    - Amount to pay: $").append(String.format("%.2f", billingDetails.getRemainingAmount())).append("\n");


        summary.append("====================================\n");
        summary.append("====================================\n");

        return summary.toString();
    }
}
