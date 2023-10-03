package enumerations;

public enum StudyField {
    DEFAULT_UNASSIGNED("Unassigned", "None"),
    MECHANICAL_ENGINEERING("Mechanical Engineering", "ME"),
    SOFTWARE_ENGINEERING("Software Engineering", "FAF"),
    FOOD_TECHNOLOGY("Food Technology", "FT"),
    URBANISM_ARCHITECTURE("Urbanism and Architecture", "UA"),
    VETERINARY_MEDICINE("Veterinary Medicine", "VE");

    private final String name;
    private final String abbreviation;

    StudyField(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }


    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static StudyField  isValidAbbreviation(String abbreviationToCheck) {
        for (StudyField field : values()) {
            if (field.abbreviation.equals(abbreviationToCheck)) {
                return field;
            }
        }
        return null;
    }
}
