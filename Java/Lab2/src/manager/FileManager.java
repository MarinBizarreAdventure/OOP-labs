package manager;

import models.Faculty;
import models.Student;
import enumerations.StudyField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "student_data.txt"; // Change this path to your desired file location

    public static void saveData(List<Faculty> faculties) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(faculties);
            System.out.println("Data saved to " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static List<Faculty> loadData() {
        List<Faculty> faculties = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            faculties = (List<Faculty>) inputStream.readObject();
            System.out.println("Data loaded from " + FILE_PATH);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }

        return faculties;
    }

    public static void resetData() {
        try {
            new FileOutputStream(FILE_PATH).close(); // Create an empty file to reset the data
            System.out.println("Data reset to default.");
        } catch (IOException e) {
            System.err.println("Error resetting data: " + e.getMessage());
        }
    }
}

