import userinterface.Menu;
import userinterface.commands.FileCommand;
import userinterface.commands.GeneralCommand;
import userinterface.commands.FacultyCommand;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;



//nf/facultatea de calculatoare, informatica si microelectronica/fcim/FAF
//        nf/facultatea Tehnologiei alimentatiei/fta/FT
//        nf/facultatea de inginerie mecanica/fme/ME
//        nf/facultatea de arhitectura si urbanism/fau/UA
public class Main {
    public static void main(String[] args) {
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

}