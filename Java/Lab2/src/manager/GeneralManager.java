package manager;

import models.Faculty;
import models.Student;
import enumerations.StudyField;

import java.util.ArrayList;



public class GeneralManager {
    private static final FacultyManager facultyManager = new FacultyManager();
    private static final ArrayList<Faculty> faculties = facultyManager.getFaculties();

    public static void createFaculty(ArrayList<String> command){
        if (command.size() != 4){
            System.out.println("Wrong numbers of parameters");
            return;
        }

        StudyField studyField = StudyField.isValidAbbreviation(command.get(3));
        if (studyField == null){
            System.out.println("No such field: " +  command.get(3) + " try one more time\n");
            return;
        }

        Faculty newFaculty = new Faculty( command.get(1), command.get(2), studyField, new ArrayList<>());
        System.out.println("New faculty created: " + newFaculty);
        facultyManager.addFaculty(newFaculty);
    }

    public static void displayFaculties(ArrayList<String> parsedCommand) {

        StringBuilder facultiesString = new StringBuilder();
        if (parsedCommand.size() == 2) {
            StudyField studyField = StudyField.isValidAbbreviation(parsedCommand.get(1));

            if (studyField == null){
                System.out.println("No such study field: " + parsedCommand.get(1));
                return;
            }

            boolean facultiesByFieldPresent = false;

            for (Faculty f : faculties) {
                if (f.getStudyField().equals(studyField)) {
                    facultiesString.append(f);
                    facultiesString.append('\n');
                    facultiesByFieldPresent = true;
                }
            }

            if (!facultiesByFieldPresent)
                System.out.println("Faculty list is empty");
            else {
                System.out.println("======Faculties======");
                System.out.print(facultiesString);
                System.out.println("======Faculties======");

            }

        } else if (parsedCommand.size() == 1) {

            if (faculties.isEmpty()) {
                System.out.println("Faculty list is empty");
            } else {
                for (Faculty f : faculties) {
                    facultiesString.append(f);
                    facultiesString.append('\n');
                }

                System.out.println("======Faculties======");
                System.out.print(facultiesString);
                System.out.println("======Faculties======");
            }
        }
    }

    public static void searchStudent(ArrayList<String> command) {

        if (command.size() != 2) {
            System.out.println("Incorrect number of parameters");
            return;
        }
        try {
            String email = command.get(1);
            boolean found = false;
            for (Faculty faculty : faculties) {
                for (Student st : faculty.getStudents()) {
                    if (st.getEmail().equals(email)) {
                        found = true;
                        System.out.println("Student with email " + email + " is present in the faculty:" + faculty);
                    }
                    if (found)
                        break;
                }
                if (found)
                    break;
            }
            if (!found)
                System.out.println("Student with email " + email + " doesn't exist");

        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong command, try one more time");
        }

    }

}
