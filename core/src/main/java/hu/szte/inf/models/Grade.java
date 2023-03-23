package hu.szte.inf.models;

public enum Grade {
    INSUFFICIENT("1"),
    SUFFICIENT("2"),
    SATISFACTORY("3"),
    GOOD("4"),
    EXCELLENT("5");

    private final String displayValue;

    Grade(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
