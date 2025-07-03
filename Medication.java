 
class Medication {
    private String name;
    private String dosage;
    private String frequency;
    private int durationInDays;
    
    public Medication(String name, String dosage, String frequency, int durationInDays) {
        this.name = name;
        this.dosage = dosage;
        this.durationInDays = durationInDays;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    // Adding toString() method to represent the Medication object in a readable format
    @Override
    public String toString() {
        return "Medication: " +
                "\n name='" + name +
                "\n dosage='" + dosage + 
                "\n durationInDays=" + durationInDays ;
    }

    public Object frequency() {

        return frequency;
    }
}
