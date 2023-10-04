import userinterface.Menu;
import userinterface.commands.FileCommand;
import userinterface.commands.GeneralCommand;
import userinterface.commands.FacultyCommand;
import userinterface.commands.StudentCommand;
import java.util.ArrayList;
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
                    new GeneralCommand(scanner);
                    break;
                case "f":
                    new FacultyCommand(scanner);

                    break;
                case "s":
                    new StudentCommand(scanner);
                    break;
                case "d":
                    new FileCommand(scanner);
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