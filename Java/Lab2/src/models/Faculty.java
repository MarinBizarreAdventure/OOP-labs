package models;

import enumerations.StudyField;

import java.util.List;

public class Faculty {
    private String name;
    private String abbreviation;
    private StudyField field;
    private List<Student> students;


    public Faculty(String name, String abbreviation, StudyField field, List<Student> students) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.field = field;
        this.students = students;
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
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
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
