import java.util.ArrayList;
import java.util.List;

class Doctor {
    private String id;
    private String name;
    private Specialization specialization;
    private Schedule schedule;
    private List<Ward> assignedWards;

    public Doctor(String id, String name, Specialization specialization, Schedule schedule) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.schedule = schedule;
        this.assignedWards = new ArrayList<>();
    }

    public Doctor() {
        this.id = "N/A";
        this.name ="N/A";
        this.specialization = new Specialization("N/A");
        this.schedule = new Schedule(null, null);
        this.assignedWards = new ArrayList<>();
    }

    // Assign doctor to a ward
    public void assignToWard(Ward ward) {
        if (!assignedWards.contains(ward)) {
            assignedWards.add(ward);
        }
    }

    public List<Ward> getAssignedWards() {
        return assignedWards;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Doctor: " +
                "\n id='" + id + 
                "\n name='" + name + 
                "\n specialization=" + specialization +
                "\n Ward=" +assignedWards+
                "\n schedule=" + schedule;

    }
}
