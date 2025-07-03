 
class Equipment {
    private String equipmentName;
    private String equipmentType;
    private boolean isOperational;

    public Equipment(String equipmentName, String equipmentType, boolean isOperational) {
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.isOperational = isOperational;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public boolean isOperational() {
        return isOperational;
    }

    @Override
    public String toString() {
        return "Equipment:" +
                "\n equipmentName='" + equipmentName  +
                "\n equipmentType='" + equipmentType  +
                "\n isOperational=" + isOperational;

    }
}
