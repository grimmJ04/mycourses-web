package hu.szte.inf.models;

public enum Semester {
    ALL("All"),
    FALL("Fall"),
    SPRING("Spring");

    private final String displayValue;

    Semester(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
