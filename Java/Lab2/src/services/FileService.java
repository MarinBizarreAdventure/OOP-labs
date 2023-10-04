package services;

import models.Faculty;
import utils.LoggerUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileService {
    private static final String FILE_PATH = "Java/Lab2/src/data/student_data.txt";
    private static Logger logger = LoggerUtil.getLogger();

    public static void saveData(List<Faculty> faculties) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(faculties);
            System.out.println("Data saved to " + FILE_PATH);
            logger.info("Data saved to " + FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data: " + e.getMessage());
            logger.severe("Error saving data: " + e.getMessage());
        }
    }

    public static ArrayList<Faculty> loadData() {
        ArrayList<Faculty> faculties = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            faculties = (ArrayList<Faculty>) inputStream.readObject();
            LoggerUtil.getLogger().info("Data loaded from " + FILE_PATH);
        } catch (FileNotFoundException e) {
            LoggerUtil.getLogger().severe("File not found: " + e.getMessage());
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            LoggerUtil.getLogger().severe("Error loading data: " + e.getMessage());
            System.err.println("Error loading data: " + e.getMessage());
        }
        LoggerUtil.getLogger().info(faculties.toString());
        FacultyService facultyService = new FacultyService();
        facultyService.setFaculties(faculties);

        return faculties;
    }


    public static void resetData() {
        try {
            new FileOutputStream(FILE_PATH).close();
            System.out.println("Data reset to default.");
            logger.info("Data reset to default.");
        } catch (IOException e) {
            System.err.println("Error resetting data: " + e.getMessage());
            logger.severe("Error resetting data: " + e.getMessage());
        }
    }

}

