package services;

import models.Faculty;
import userinterface.Menu;
import utils.LoggerUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileService {
    private static final String FILE_PATH = "Java/Lab2/src/datastorage/student_data.txt";
    private static final String ENROLLMENT_FILE_PATH = "Java/Lab2/src/datastorage/enrollment.txt";
    private static final String GRADUATION_FILE_PATH = "Java/Lab2/src/datastorage/graduation.txt";
    private static final Logger logger = LoggerUtil.getLogger();

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

    public static void batchReader(String batch) {
        String FILE_BATCH = batch.equals("ns") ? ENROLLMENT_FILE_PATH : GRADUATION_FILE_PATH;
        String logMessage = batch.equals("ns") ? "enrollment": "graduation";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_BATCH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> params = Menu.divideCommand(line);
                if (batch.equals("ns")){
                    if (params.size() == 8)
                        FacultyService.createStudent(params);
                    else
                        logger.warning("Invalid " + logMessage + " data: " + line);
                }else{
                    if (params.size() == 2)
                        FacultyService.graduateStudent(params);
                    else
                        logger.warning("Invalid " + logMessage + " data: " + line);
                }
            }
            logger.info("Batch "+ logMessage +" completed.");
        } catch (IOException e) {
            logger.severe("Error reading " +logMessage+" file: " + e.getMessage());
        }
    }


}

