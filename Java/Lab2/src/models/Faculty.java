package models;

import enums.StudyField;
import utils.LoggerUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;


public class Faculty implements Serializable {
    private String name;
    private String abbreviation;
    private StudyField field;
    private ArrayList<Student> students;
    private static final Logger logger = LoggerUtil.getLogger();

    public Faculty (String name, String abbreviation, StudyField field, ArrayList<Student> students) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.field = field;
        this.students = students;

        logger.info("Faculty created - Name: " + name + ", Abbreviation: " + abbreviation + ", Field: " + field);
        for (Student student : students) {
            logger.info("Student added to faculty - Faculty: " + abbreviation + ", Student: " + student.getEmail());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public StudyField getField() {
        return field;
    }

    public void setField(StudyField field) {
        this.field = field;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public StudyField getStudyField() {
        return field;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", field=" + field +
                '}';
    }
}
