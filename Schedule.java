import java.time.LocalDate;
import java.time.LocalTime; // Use LocalTime instead of java.sql.Time
import java.util.List;

class Schedule {
    private List<LocalDate> availableDays;  // List of available days
    private List<LocalTime> availableTimes;  // Available time for the doctor (changed to LocalTime)

    public Schedule(List<LocalDate> availableDays, List<LocalTime> availableTimes) {
        this.availableDays = availableDays;
        this.availableTimes = availableTimes;
    }

    public List<LocalDate> getAvailableDays() {
        return availableDays;
    }

    public List<LocalTime> getAvailableTimes() {  // Updated to LocalTime
        return availableTimes;
    }

    public void setAvailableDays(List<LocalDate> availableDays) {
        this.availableDays = availableDays;
    }

    public void setAvailableTimes(List<LocalTime> availableTimes) {  // Updated to LocalTime
        this.availableTimes = availableTimes;
    }

    @Override
    public String toString() {
        return "Schedule:" +
                "\n availableDays=" + availableDays +
                "\n availableTimes=" + availableTimes;
    }
}
