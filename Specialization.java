 
class Specialization {
    private String field;

    public Specialization(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    @Override
    public String toString() {
        return "Specialization:" + field;
    }
}
