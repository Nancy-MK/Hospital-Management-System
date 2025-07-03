 
import java.util.ArrayList;
import java.util.List;

class TreatmentPlan {
    private List<String> procedures;
    private String notes;

    public TreatmentPlan(List<String> procedures, String notes) {
        this.procedures = procedures;
        this.notes = notes;
    }

    public TreatmentPlan () {
        this.procedures = new ArrayList<String>();
        this.notes = "N/A";
    }

    public List<String> getProcedures() {
        return procedures;
    }

    public String getNotes() {
        return notes;
    }

    public void setProcedures(List<String> procedures) {
        this.procedures = procedures;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Adding a toString() method for better string representation
    @Override
    public String toString() {
        return "TreatmentPlan: " +
                "\n procedures=" + procedures +
                "\n treated the patient with plan: " + notes ;
    }
}
