 

class BillingDetails {
    private double totalCost;
    private double insuranceCoverage;
    private double remainingAmount;
    private String coverageType;
    private InsuranceDetails insuranceDetails; // Store the InsuranceDetails object

    public BillingDetails(double totalCost, InsuranceDetails insuranceDetails) {
        this.totalCost = totalCost;
        this.insuranceDetails = insuranceDetails; // Store the insuranceDetails object

        // Get coverage amount from InsuranceDetails (either manual or calculated)
        this.insuranceCoverage = insuranceDetails.getCoverageAmount(totalCost);
        this.remainingAmount = totalCost - this.insuranceCoverage;

        // Determine coverage type based on the calculated insurance coverage
        if (this.insuranceCoverage >= totalCost) {
            this.coverageType = "Full";
            this.remainingAmount = 0.0;
        } else if (this.insuranceCoverage > 0) {
            if (this.insuranceCoverage == totalCost / 2) {
                this.coverageType = "Half";
            } else {
                this.coverageType = "Partial";
            }
        } else {
            this.coverageType = "None";
        }
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public String getCoverageType() {
        return coverageType;
    }

    // Getter for the InsuranceDetails object
    public InsuranceDetails getInsuranceDetails() {
        return insuranceDetails;
    }
}
