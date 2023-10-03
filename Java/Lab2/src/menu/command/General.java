package menu.command;

import manager.GeneralManager;
import menu.Menu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class General {

    public General(Scanner scanner) {

        String nextCommand = null;

        while (!Objects.equals(nextCommand, "b")) {
            Menu.printGeneralCommands();
            nextCommand = scanner.nextLine();
            ArrayList<String> parsedCommand = Menu.parseCommand(nextCommand);
            switch (parsedCommand.get(0)) {
                case "nf":
                    try {
                        GeneralManager.createFaculty(parsedCommand);
                    } catch (Exception e) {
                        System.out.println("Incorrect command, try one more time");
                    }
                    break;
                case "ss":
                        GeneralManager.searchStudent(parsedCommand);
                    break;
                case "df":
                        GeneralManager.displayFaculties(parsedCommand);
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
