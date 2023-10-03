package manager;

import models.Faculty;
import models.Student;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FacultyManager {
    private static final ArrayList<Faculty> faculties = new ArrayList<>();


    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

//    public void deleteFaculty(Faculty faculty) {
//        faculties.remove(faculty);
//    }

    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }
    public static void createStudent(ArrayList<String> command){
        if (command.size() != 8){
            System.out.println("Wrong numbers of parameters");
            return;
        }

        //implement email check
        String birthDay = command.get(5) + "/" + command.get(6) + "/" + command.get(7);
        System.out.println(command.get(2));
        Faculty faculty = getFacultyByAbbreviation(command.get(1));

        //implement faculty check
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d/MM/yyyy");
        Date dateOfBirth;
        try{
            dateOfBirth = dateFormatter.parse(birthDay);
        }catch (ParseException e){
            dateOfBirth = new Date();
        }

        Student student = new Student(command.get(2), command.get(3), command.get(4), new Date(), dateOfBirth,false);
        faculty.getStudents().add(student);
        System.out.println("Student:" + student + ", was successfully created " + student.getFirstName());
    }




    public static Faculty getFacultyByAbbreviation(String abbreviation) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(abbreviation))
                return faculty;
        }

        return null;
    }
    public static void graduateStudent(ArrayList<String> parsedCommand) {

        if (parsedCommand.size() != 2) {
            System.out.println("Wrong number of parameters for the command " + parsedCommand.get(0));
            return;
        }
        Student student = getStudentByEmail(parsedCommand.get(1));

        if (student == null) {
            System.out.println("Student with email " + parsedCommand.get(1) + " is not present");
            return;
        }
        student.setGraduated(true);
        System.out.println("Student " + student.getFirstName() + " " + student.getLastName() + " is now graduated!");
    }
    public static Student getStudentByEmail(String email) {
        for (Faculty faculty : faculties) {
            for (Student st : faculty.getStudents()) {
                if (st.getEmail().equals(email))
                    return st;
            }
        }
        return null;
    }

    public static void displayAllStudentsByFaculty(ArrayList<String> params) {

        if (params.size() != 2) {
            System.out.println("Wrong number of parameters for the command " + params.get(0));
            return;
        }

        Faculty faculty = getFacultyByAbbreviation(params.get(1));
        if (faculty == null) {
            System.out.println("Incorrect abbreviation");
            return;
        }

        if (faculty.getStudents().isEmpty()) {
            System.out.println("No students present in the faculty");
            return;
        }

        StringBuilder stringOutput = new StringBuilder();
        stringOutput.append("======STUDENTS from ").append(faculty.getName()).append("======\n");
        System.out.println();
        for (Student st : faculty.getStudents()) {
            stringOutput.append(st);
            stringOutput.append('\n');
        }
        stringOutput.append("======STUDENTS from ").append(faculty.getName()).append("======");
        System.out.println(stringOutput);
    }

    public static void displayGraduatedStudentsByFaculty(ArrayList<String> parsedCommand) {

        if (parsedCommand.size() != 2) {
            System.out.println("Incorrect number of parameters for the command " + parsedCommand.get(0));
            return;
        }

        String abbreviation = parsedCommand.get(1);
        Faculty faculty = getFacultyByAbbreviation(abbreviation);
        if (faculty == null) {
            System.out.println("Faculty with the following abbreviation: " + abbreviation + " doesn't exist");
            return;
        }

        StringBuilder stringOutput = new StringBuilder();
        stringOutput.append("======STUDENTS graduated from ").append(faculty.getName()).append("======\n");
        boolean foundStudent = false;
        for (Student st : faculty.getStudents())
            if ( st.getGraduated()) {
                stringOutput.append(st);
                stringOutput.append('\n');
                foundStudent = true;
            }
        stringOutput.append("======STUDENTS from ").append(faculty.getName()).append("======");

        if (foundStudent)
            System.out.println(stringOutput);
        else
            System.out.println("No graduated students");
    }

    public static void checkIfStudentIsPresentInFaculty(ArrayList<String> parsedCommand) {

        if (parsedCommand.size() != 3) {
            System.out.println("Incorrect number of parameters for the command " + parsedCommand.get(0));
            return;
        }
        Student student = getStudentByEmail(parsedCommand.get(2));
        Faculty faculty = getFacultyByAbbreviation(parsedCommand.get(1));

        if (student != null) {
            if (faculty != null) {
                for (Student st : faculty.getStudents())
                    if (st.equals(student)) {
                        System.out.println("Student belongs to faculty!");
                        return;
                    }
            } else {
                System.out.println("Faculty is not present");
                return;
            }
        } else {
            System.out.println("Student with email " + parsedCommand.get(2) + " doesn't exist");
            return;
        }

        System.out.println("Student doesn't belong to faculty");

    }

}
