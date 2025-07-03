 
import java.util.ArrayList;
import java.util.List;

class MedicalHistory {
    private List<String> conditions;
    private List<String> surgeries;
    private List<String> treatments;
    private List<String> allergies;

    public MedicalHistory(List<String> conditions, List<String> surgeries, List<String> treatments, List<String> allergies) {
        this.conditions = conditions;
        this.surgeries = surgeries;
        this.treatments = treatments;
        this.allergies = allergies;
    
    }

    public MedicalHistory() {
        this.conditions =  new ArrayList<>();
        this.surgeries =  new ArrayList<>();
        this.treatments =  new ArrayList<>();
        this.allergies =  new ArrayList<>();

    }

    public List<String> getConditions() {
        return conditions;
    }

    public List<String> getSurgeries() {
        return surgeries;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    @Override
    public String toString() {
        return  "\n  Conditions: " + String.join(", ", conditions) +
                "\n  Surgeries: " + String.join(", ", surgeries) +
                "\n  Treatments: " + String.join(", ", treatments) +
                "\n  Allergies: " + String.join(", ", allergies);
    }
}
