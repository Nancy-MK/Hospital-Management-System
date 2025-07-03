 

public class Bed {
    private int bedNumber;
    private boolean available;
    private Patient patient;

    // Constructor
    public Bed(int bedNumber, boolean available) {
        this.bedNumber = bedNumber;
        this.available = available;
        this.patient = null;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "Bed:\n" +
                "{ bedNumber = " + bedNumber +
                ", available = " + available +
                " }";
    }
}
