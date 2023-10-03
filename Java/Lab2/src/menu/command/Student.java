package menu.command;

import menu.Menu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Student{

    public Student(Scanner scanner) {

        String nextCommand = null;

        while (!Objects.equals(nextCommand, "b")) {

            Menu.printStudentCommand();
            nextCommand = scanner.nextLine();
            ArrayList<String> parsedCommand = Menu.parseCommand(nextCommand);

            if (parsedCommand.get(0).equals("q")) {
                System.exit(0);
            } else {
                System.out.println("Unknown command!");
            }
        }
    }
}
