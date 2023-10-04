package userinterface;

import userinterface.commands.FacultyCommand;
import userinterface.commands.FileCommand;
import userinterface.commands.GeneralCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    public void StartMenu(){
        Scanner scanner = new Scanner(System.in);

        String nextCommand = null;
        while (!Objects.equals(nextCommand, "q")){
            Menu.printMainCommands();
            nextCommand = scanner.nextLine();
            ArrayList<String> parsedCommand = Menu.divideCommand(nextCommand);
            switch (parsedCommand.get(0)){
                case "g":
                    new GeneralCommand(scanner);
                    break;
                case "f":
                    new FacultyCommand(scanner);
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

    public static void printMainCommands() {
        System.out.println("""
            Welcome to TUM's student management system!
            What do you want to do?
            g - General operations
            f - Faculty operations
            d - Data operations

            q - Quit program
            your input>\s""");
    }

    public static void printFacultyCommands() {
        System.out.println("""
                Faculty operations
                What do you want to do?

                ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create student
                gs/<email> - graduate student
                ds/<faculty abbreviation> - display enrolled students
                dg/<faculty abbreviation> - display graduated students
                bf/<faculty abbreviation>/<email> - check if student belongs to faculty

                b - Back
                q - Quit Program
                your input>\s""");
    }
    public static void printGeneralCommands() {
        System.out.println("""
                General operations
                What do you want to do?

                nf/<faculty name>/<faculty abbreviation>/<field> - create faculty
                ss/<student email> - search student and show faculty
                df - display faculties
                df/field - display all faculties of a field

                b - Back
                q - Quit Program
                your input>\s""");
    }

    public static void printFileCommands() {
        System.out.println("""
            File operations
            What do you want to do?

            s   - Save data to a file
            l   - Load data from a file
            r   - Reset data to default
            res - Read students to enroll
            rgs - Read students to graduate

            b - Back
            q - Quit Program
            your input>\s""");
    }

    public static ArrayList<String> divideCommand(String input) {
        return new ArrayList<>(List.of(input.split("/")));
    }
}
