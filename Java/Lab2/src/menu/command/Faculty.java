package menu.command;

import manager.FacultyManager;
import menu.Menu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Faculty {

    public Faculty(Scanner scanner) {

        String nextCommand = null;

        while (!Objects.equals(nextCommand, "b")) {

            Menu.printFacultyCommands();
            nextCommand = scanner.nextLine();
            ArrayList<String> parsedCommand = Menu.parseCommand(nextCommand);

            switch (parsedCommand.get(0)) {
                case "ns":
//                    try {
                        FacultyManager.createStudent(parsedCommand);
//                    } catch (Exception e) {
//                        System.out.println("Incorrect command, try one more time");
//                    }
                    break;
                case "gs":
                    FacultyManager.graduateStudent(parsedCommand);
                    break;
                case "ds":
                    FacultyManager.displayAllStudentsByFaculty(parsedCommand);
                    break;
                case "dg":
                    FacultyManager.displayGraduatedStudentsByFaculty(parsedCommand);
                    break;
                case "bf":
                    FacultyManager.checkIfStudentIsPresentInFaculty(parsedCommand);
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
}
