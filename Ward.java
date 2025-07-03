 

import java.util.ArrayList;
import java.util.List;

class Ward {
    private String wardNumber;
    private int capacity;
    private List<Bed> beds;  // List of beds in the ward
    private List<Equipment> equipment;  // List of medical equipment in the ward

    public Ward(String wardNumber, int capacity, List<Bed> beds, List<Equipment> equipment){ this.wardNumber = wardNumber;
        this.capacity = capacity;
        this.beds = beds;  // Allow modification of availability
        this.equipment = equipment;
    }

    public Ward() {
        this.wardNumber = "null";
        this.capacity = 5;

        this.beds = new ArrayList<>();  // Allow modification of availability
        this.equipment = new ArrayList<>();
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    // Check if there is an available bed
    public boolean hasAvailableBed() {
        return beds.stream().anyMatch(Bed::isAvailable);
    }

    // Occupy the first available bed
    public boolean occupyFirstAvailableBed() {
        for (Bed bed : beds) {
            if (bed.isAvailable()) {
                bed.setAvailable(false);  // Mark bed as occupied
                return true;
            }
        }
        return false; // No available beds
    }

    // Free a bed (example: the first occupied one)
    public boolean freeAnOccupiedBed() {
        for (Bed bed : beds) {
            if (!bed.isAvailable()) {
                bed.setAvailable(true);  // Mark bed as available
                return true;
            }
        }
        return false; // No beds to free
    }

    // Count occupied beds
    public int getBedsOccupied() {
        return (int) beds.stream().filter(b -> !b.isAvailable()).count();
    }

    @Override
    public String toString() {
        return "Ward:" +
                "\n wardNumber='" + wardNumber + '\'' +
                "\n capacity=" + capacity +
                "\n bedsOccupied=" + getBedsOccupied() +
                "\n equipment=" + equipment;
    }
}
