 
class ContactDetails {
    private String address;
    private String phoneNumber;
    private String emergencyContact;

    public ContactDetails(String address, String phoneNumber, String emergencyContact) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emergencyContact = emergencyContact;
    }

    public ContactDetails() {
        this.address = "N/A";
        this.phoneNumber = "N/A";
        this.emergencyContact = "N/A";
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    @Override
    public String toString() {
        return "\n  Address: " + address +
               "\n  Phone: " + phoneNumber +
               "\n  Emergency Contact: " + emergencyContact;
    }
}
