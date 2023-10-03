import menu.Menu;
import menu.command.General;
import menu.command.Faculty;
import menu.command.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String nextCommand = null;

        while (!Objects.equals(nextCommand, "q")){
            Menu.printMainCommands();
            nextCommand = scanner.nextLine();
            ArrayList<String> parsedCommand = Menu.parseCommand(nextCommand);
            switch (parsedCommand.get(0)){
                case "g":
                    new General(scanner);
                    break;
                case "f":
                    new Faculty(scanner);
                    break;
                case "s":
                    new Student(scanner);
                    break;
                case "q":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unrecognized command!");
                    break;
            }
        }

    }



}