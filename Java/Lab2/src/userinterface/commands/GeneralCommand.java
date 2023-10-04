package userinterface.commands;

import services.GeneralService;
import userinterface.Menu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GeneralCommand {

    public GeneralCommand(Scanner scanner) {
        String nextCommand = null;
        while (!Objects.equals(nextCommand, "b")) {
            Menu.printGeneralCommands();
            nextCommand = scanner.nextLine();
            ArrayList<String> params = Menu.divideCommand(nextCommand);
            switch (params.get(0)) {
                case "nf":
                        GeneralService.createFaculty(params);
                    break;
                case "ss":
                        GeneralService.searchStudent(params);
                    break;
                case "df":
                        if(params.size() == 1)
                            GeneralService.displayFaculties(params);
                        else
                            GeneralService.displayFacultiesOfAField(params);
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
