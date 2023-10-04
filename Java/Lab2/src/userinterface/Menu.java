package userinterface;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static void printMainCommands() {
        System.out.println("""
            Welcome to TUM's student management system!
            What do you want to do?
            g - General operations
            f - Faculty operations
            s - Student operations
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

            s - Save data to a file
            l - Load data from a file
            r - Reset data to default

            b - Back
            q - Quit Program
            your input>\s""");
    }

    public static ArrayList<String> parseCommand(String input) {
        return new ArrayList<>(List.of(input.split("/")));
    }
}
