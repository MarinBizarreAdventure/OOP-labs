package monitor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//public class UserInterface {
//    private FileProcessor fileProcessor;
//
//    public UserInterface(FileProcessor fileProcessor) {
//        this.fileProcessor = fileProcessor;
//    }
//
//    public void startMonitoring() {
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)) {
//            String command;
//            while (true) {
//                printMessage("Enter a command (commit, info <filename>, status, or exit): ");
//                command = reader.readLine();
//
//                switch (command) {
//                    case "commit":
//                        fileProcessor.updateSnapshotTime();
//                        printMessage("Snapshot time updated to " + formatTime(fileProcessor.getLastSnapshotTime()));
//                        break;
//                    case "status":
//                        fileProcessor.checkStatus();
//                        break;
//                    case "exit":
//                        printMessage("Exiting the program.");
//                        return;
//                    default:
//                        if (command.startsWith("info ")) {
//                            String[] parts = command.split(" ");
//                            if (parts.length == 2) {
//                                String fileName = parts[1];
//                                printInfo(fileName);
//                            } else {
//                                printMessage("Invalid 'info' command format.");
//                            }
//                        } else {
//                            printMessage("Invalid command. Available commands: commit, info <filename>, status, exit.");
//                        }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void printInfo(String fileName) {
//        String info = fileProcessor.getFileInformation(fileName);
//        if (info != null) {
//            printMessage(info);
//        } else {
//            printMessage("File not found: " + fileName);
//        }
//    }
//
//    public void printMessage(String message) {
//        System.out.println(message);
//    }
//
//    private String formatTime(long timeInMillis) {
//        // Your code for formatting time
//        return null; // Replace with the actual formatting code
//    }
//}
