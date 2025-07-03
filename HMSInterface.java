 
import java.util.List;

public interface HMSInterface {
    void InitializeHospitalSystem(int patients, int doctors, int wards);
    
    AdmissionRecord Admit(Patient patient, Doctor doctor, Ward ward);
    
    TreatedRecord Treat(AdmissionRecord admissionRecord, TreatmentPlan treatmentPlan, List<Medication> medications);
    
    DischargedRecord Discharge(TreatedRecord treatedRecord, DischargeSummary dischargeSummary, BillingDetails billingDetails);
}
