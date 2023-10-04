package userinterface.commands;

import services.FacultyService;
import userinterface.Menu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FacultyCommand {

    public FacultyCommand(Scanner scanner) {
        String nextCommand = null;
        while (!Objects.equals(nextCommand, "b")) {
            Menu.printFacultyCommands();
            nextCommand = scanner.nextLine();
            ArrayList<String> params = Menu.divideCommand(nextCommand);
            switch (params.get(0)) {
                case "ns":
                        FacultyService.createStudent(params);
                    break;
                case "gs":
                    FacultyService.graduateStudent(params);
                    break;
                case "ds":
                    FacultyService.displayAllStudentsByFaculty(params);
                    break;
                case "dg":
                    FacultyService.displayGraduatedStudentsByFaculty(params);
                    break;
                case "bf":
                    FacultyService.checkIfStudentIsPresentInFaculty(params);
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
