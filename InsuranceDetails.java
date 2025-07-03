 

public class InsuranceDetails {
    private String provider;
    private String policyNumber;
    private String coverageType;  // "Full", "Half", "Partial", or custom coverage
    private String expiryDate;
    private double coverageAmount;  // Additional field for manual coverage amount

    public InsuranceDetails(String provider, String policyNumber, String coverageType, String expiryDate) {
        this.provider = provider;
        this.policyNumber = policyNumber;
        this.coverageType = coverageType;
        this.expiryDate = expiryDate;
        this.coverageAmount = 0.0;  // Default coverage amount is 0.0
    }
    public InsuranceDetails() {
        this.provider = "N/A";
        this.policyNumber = "N/A";
        this.coverageType = "N/A";
        this.expiryDate = "N/A";
        this.coverageAmount = 0.0;
    }

    public String getProvider() {
        return provider;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    // Method to calculate coverage amount based on insurance type
    public double calculateCoverageAmount(double totalCost) {
        switch (coverageType.toLowerCase()) {
            case "full": return totalCost;
            case "half": return totalCost / 2;
            case "partial": return totalCost * 0.25;  // Partial coverage, e.g., 25% of the total cost
            default: return 0.0;  // For custom or unspecified type
        }
    }

    // Set a specific coverage amount manually
    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    // Get the coverage amount, which could be manually set or calculated
    public double getCoverageAmount(double totalCost) {
        if (this.coverageAmount == 0.0) {
            return calculateCoverageAmount(totalCost);  // Return calculated amount if no manual setting
        }
        return this.coverageAmount;  // Otherwise, return the manually set amount
    }

    @Override
    public String toString() {
        return  "\n      . Provider: " + provider +
                "\n      . Policy Number: " + policyNumber +
                "\n      . Coverage Type: " + coverageType +
                "\n      . Expiry Date: " + expiryDate;
    }
}
