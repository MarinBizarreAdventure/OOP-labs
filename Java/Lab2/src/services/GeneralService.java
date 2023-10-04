package services;

import models.Faculty;
import models.Student;
import enums.StudyField;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GeneralService {
    private static final FacultyService facultyManager = new FacultyService();
    private static final ArrayList<Faculty> faculties = facultyManager.getFaculties();
    private static Logger logger = LoggerUtil.getLogger();

    public static void createFaculty(ArrayList<String> params){
        try{
            if (params.size() != 4){
                System.out.println("Wrong numbers of parameters");
                logger.warning("Wrong number of parameters");
                return;
            }

            StudyField studyField = StudyField.isValidAbbreviation(params.get(3));
            if (studyField == null){
                System.out.println("No such field: " +  params.get(3) + " try one more time\n");
                logger.warning("No such field: " + params.get(3) + " try one more time");
                return;
            }
            Faculty newFaculty = new Faculty( params.get(1), params.get(2), studyField, new ArrayList<>());
            faculties.add(newFaculty);
            System.out.println("New faculty created: " + newFaculty);
            logger.info("New faculty created: " + newFaculty);
        }catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating faculty", e);
        }

    }

    public static void displayFacultiesOfAField(ArrayList<String> params) {
        StringBuilder facultiesString = new StringBuilder();

        try {
            if (params.size() != 2) {
                logger.warning("Wrong number of parameters for displayFacultiesOfAField command");
                System.out.println("Wrong number of parameters");
                return;
            }

            StudyField studyField = StudyField.isValidAbbreviation(params.get(1));
            if (studyField == null) {
                logger.warning("No such study field: " + params.get(1));
                System.out.println("No such study field: " + params.get(1));
                return;
            }

            boolean facultiesByFieldPresent = false;
            for (Faculty f : faculties) {
                if (f.getStudyField().equals(studyField)) {
                    facultiesString.append(f).append("\n");
                    facultiesByFieldPresent = true;
                }
            }

            if (!facultiesByFieldPresent) {
                logger.info("Faculty list is empty for field: " + studyField);
                System.out.println("Faculty list is empty");
            } else {
                logger.info("Displaying faculties for field: " + studyField);
                System.out.println("Faculties");
                System.out.print(facultiesString);
                System.out.println("Faculties");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred in displayFacultiesOfAField: " + e.getMessage(), e);
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    public static void displayFaculties(ArrayList<String> params) {
        StringBuilder facultiesString = new StringBuilder();
        try {
            if (params.size() != 1)
                throw new IllegalArgumentException("Wrong number of parameters");

            if (faculties.isEmpty())
                throw new IllegalStateException("Faculty list is empty");

            for (Faculty f : faculties)
                facultiesString.append(f).append("\n");

            logger.info("Displaying faculties:");
            logger.info(facultiesString.toString());

            System.out.println("Faculties");
            System.out.print(facultiesString);
            System.out.println("Faculties");
        } catch (IllegalArgumentException e) {
            logger.warning("Invalid parameters: " + e.getMessage());
        } catch (IllegalStateException e) {
            logger.warning("Faculty list is empty: " + e.getMessage());
        }
    }

    public static void searchStudent(ArrayList<String> params) {
        if (params.size() != 2) {
            logger.warning("searchStudent: Wrong number of parameters");
            System.out.println("Wrong number of parameters");
            return;
        }
        try {
            String email = params.get(1);
            for (Faculty faculty : faculties)
                for (Student st : faculty.getStudents()){
                    if (st.getEmail().equals(email)) {
                        String message = "searchStudent: Student with email " + email + " is present in the faculty: " + faculty;
                        logger.info(message);
                        System.out.println(message);
                        return;
                    }
                    System.out.println(st.getEmail());
                }

            String message = "searchStudent: Student with email " + email + " doesn't exist";
            logger.info(message);
            System.out.println(message);
        } catch (IndexOutOfBoundsException exception) {
            String errorMessage = "searchStudent: Wrong command, try one more time";
            logger.severe(errorMessage);
            System.out.println(errorMessage);
        }
    }
}
