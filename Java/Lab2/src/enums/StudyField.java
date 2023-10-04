package enums;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger; // Import Logger
import utils.LoggerUtil; // Import your LoggerUtil class

public enum StudyField {
    DEFAULT_UNASSIGNED("Unassigned", "None"),
    MECHANICAL_ENGINEERING("Mechanical Engineering", "ME"),
    SOFTWARE_ENGINEERING("Software Engineering", "FAF"),
    FOOD_TECHNOLOGY("Food Technology", "FT"),
    URBANISM_ARCHITECTURE("Urbanism and Architecture", "UA"),
    VETERINARY_MEDICINE("Veterinary Medicine", "VE");

    private final String name;
    private final String abbreviation;
    private static final Map<String, StudyField> abbreviationToStudyFieldMap = new HashMap<>();
    private static final Logger logger = LoggerUtil.getLogger(); // Get the logger instance

    static {
        for (StudyField field : values())
            abbreviationToStudyFieldMap.put(field.abbreviation, field);
    }

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

    public static StudyField isValidAbbreviation(String abbreviationToCheck) {
        StudyField field = abbreviationToStudyFieldMap.get(abbreviationToCheck);
        if (field != null) {
            logger.info("Abbreviation '" + abbreviationToCheck + "' is valid.");
        } else {
            logger.warning("Abbreviation '" + abbreviationToCheck + "' is not valid.");
        }
        return field;
    }
}
