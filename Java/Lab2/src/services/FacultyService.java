package services;

import models.Faculty;
import models.Student;
import utils.LoggerUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacultyService {
    private static ArrayList<Faculty> faculties = new ArrayList<>();
    private static Logger logger = LoggerUtil.getLogger();

    public void setFaculties(ArrayList<Faculty> faculties) {
        this.faculties = faculties;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

//    public void deleteFaculty(Faculty faculty) {
//        faculties.remove(faculty);
//    }

    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }
    public static void createStudent(ArrayList<String> params) {
        if (params.size() != 8) {
            logger.severe("Wrong number of parameters in createStudent: " + params.size());
            System.out.println("Wrong number of parameters");
            return;
        }
        // Implement email check
        String birthDay = params.get(5) + "/" + params.get(6) + "/" + params.get(7);
        Faculty faculty = getFacultyByAbbreviation(params.get(1));

        // Implement faculty check
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d/MM/yyyy");
        Date dateOfBirth;
        try {
            dateOfBirth = dateFormatter.parse(birthDay);
        } catch (ParseException e) {
            logger.log(Level.WARNING, "Error parsing date of birth: " + e.getMessage(), e);
            dateOfBirth = new Date();
        }

        Student student = new Student(params.get(2), params.get(3), params.get(4), new Date(), dateOfBirth, false);
        faculty.getStudents().add(student);

        logger.info("Student: " + student + " was successfully created");
        System.out.println("Student:" + student + ", was successfully created ");
    }


    public static Faculty getFacultyByAbbreviation(String abbreviation) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(abbreviation)) {
                logger.info("Found faculty with abbreviation: " + abbreviation);
                return faculty;
            }
        }

        logger.warning("Faculty with abbreviation " + abbreviation + " not found");
        return null;
    }
    public static void graduateStudent(ArrayList<String> params) {
        if (params.size() != 2) {
            String errorMessage = "Wrong number of parameters for the command " + params.get(0);
            logger.warning(errorMessage);
            System.out.println(errorMessage);
            return;
        }

        Student student = getStudentByEmail(params.get(1));
        if (student == null) {
            String errorMessage = "Student with email " + params.get(1) + " is not present";
            logger.warning(errorMessage);
            System.out.println(errorMessage);
            return;
        }

        student.setGraduated(true);
        String graduationMessage = "Student " + student + " is now graduated!";
        logger.info(graduationMessage);
        System.out.println(graduationMessage);
    }

    public static Student getStudentByEmail(String email) {
        for (Faculty faculty : faculties) {
            for (Student st : faculty.getStudents()) {
                if (st.getEmail().equals(email)) {
                    logger.info("Found student with email: " + email);
                    return st;
                }
            }
        }
        logger.info("Student with email " + email + " not found.");
        return null;
    }

    public static void displayAllStudentsByFaculty(ArrayList<String> params) {
        if (params.size() != 2) {
            System.out.println("Wrong number of parameters for the command " + params.get(0));
            logger.warning("Incorrect number of parameters for the command " + params.get(0));
            return;
        }

        Faculty faculty = getFacultyByAbbreviation(params.get(1));
        if (faculty == null) {
            System.out.println("Incorrect abbreviation");
            logger.warning("Incorrect abbreviation");
            return;
        }else if(faculty.getStudents().isEmpty()) {
            System.out.println("No students present in the faculty");
            logger.info("No students present in the faculty");
            return;
        }

        StringBuilder stringOutput = new StringBuilder();
        stringOutput.append("STUDENTS from ").append(faculty.getName()).append("\n");
        logger.info("Displaying students for faculty: " + faculty.getName());

        stringOutput.append("STUDENTS from ").append(faculty.getName()).append("\n");
        for (Student st : faculty.getStudents()) {
            stringOutput.append(st);
            stringOutput.append('\n');
        }
        stringOutput.append("STUDENTS from ").append(faculty.getName()).append("");
        System.out.println(stringOutput);
    }

    public static void displayGraduatedStudentsByFaculty(ArrayList<String> params) {
        if (params.size() != 2) {
            logger.warning("Incorrect number of parameters for the command " + params.get(0));
            System.out.println("Incorrect number of parameters for the command " + params.get(0));
            return;
        }

        String abbreviation = params.get(1);
        Faculty faculty = getFacultyByAbbreviation(abbreviation);
        if (faculty == null) {
            String message = "Faculty with the following abbreviation: " + abbreviation + " doesn't exist";
            logger.warning(message);
            System.out.println(message);
            return;
        }

        StringBuilder stringOutput = new StringBuilder();
        stringOutput.append("STUDENTS graduated from ").append(faculty.getName()).append("\n");
        boolean foundStudent = false;
        for (Student st : faculty.getStudents())
            if (st.isGraduated()) {
                stringOutput.append(st);
                stringOutput.append('\n');
                foundStudent = true;
            }

        stringOutput.append("STUDENTS from ").append(faculty.getName()).append("\n");
        if (foundStudent) {
            System.out.println(stringOutput);
        } else {
            String message = "No graduated students for faculty: " + faculty.getName();
            logger.info(message);
            System.out.println(message);
        }
    }

    public static void checkIfStudentIsPresentInFaculty(ArrayList<String> params) {
        if (params.size() != 3) {
            logger.warning("Wrong number of parameters for the command " + params.get(0));
            System.out.println("Wrong number of parameters for the command " + params.get(0));
            return;
        }
        Student student = getStudentByEmail(params.get(2));
        Faculty faculty = getFacultyByAbbreviation(params.get(1));

        if (faculty.getStudents().contains(student)) {
            logger.info("Student " + student.getFirstName() + " " + student.getLastName() + " belongs to faculty " + faculty.getName());
            System.out.println("Student " + student.getFirstName() + " " + student.getLastName() + " belongs to faculty " + faculty.getName());
        } else {
            logger.info("Student " + student.getFirstName() + " " + student.getLastName() + " doesn't belong to faculty " + faculty.getName());
            System.out.println("Student " + student.getFirstName() + " " + student.getLastName() + " belongs to faculty " + faculty.getName());
        }
    }
}
