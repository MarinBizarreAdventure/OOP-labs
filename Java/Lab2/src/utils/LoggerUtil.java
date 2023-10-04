package utils;

import java.util.logging.*;
import java.io.IOException;

public class LoggerUtil {
    private static Logger logger  = Logger.getLogger("StudentManagementSystemLogger");
    static{
        ConfigureLogger();
    }
    private static void ConfigureLogger() {
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("Java/Lab2/src/utils/project.log", true);
            fileHandler.setLevel(Level.ALL);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Error initializing the log file: " + e.getMessage(), e);
            System.out.println("catch");
        }
        logger.log(Level.SEVERE, "Error initializing the log file: ");

    }

    public static Logger getLogger() {
        return logger;
    }
    public static void resetLogFile() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("project.log", false);
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
