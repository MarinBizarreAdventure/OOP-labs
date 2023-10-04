package userinterface.commands;

import services.FacultyService;
import services.FileService;
import userinterface.Menu;
import models.Faculty;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class FileCommand {

    public FileCommand(Scanner scanner) {
        String nextCommand = null;
        while (!Objects.equals(nextCommand, "b")) {
            Menu.printFileCommands();
            nextCommand = scanner.nextLine();
            ArrayList<String> parsedCommand = Menu.divideCommand(nextCommand);

            switch (parsedCommand.get(0)) {
                case "s":
                    saveData();
                    break;
                case "l":
                    FileService.loadData();
                    break;
                case "r":
                    FileService.resetData();
                    break;
                case "res":
                    FileService.batchReader("ns");
                    break;
                case "rgs":
                    FileService.batchReader("gs");
                    break;
                case "b":
                    break;
                case "q":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command!");
                    break;
            }
        }
    }

    private void saveData() {
        FacultyService facultyService = new FacultyService();
        ArrayList<Faculty> faculties = facultyService.getFaculties();
        FileService.saveData(faculties);
        System.out.println("Data saved successfully.");
    }
}
