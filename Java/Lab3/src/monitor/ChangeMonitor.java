package monitor;

import models.Record;
import models.*;
import enums.FileType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ChangeMonitor {
    private Path directoryPath;
    private long lastSnapshotTime;
    private List<Record> records;

    public ChangeMonitor(Path directoryPath) {
        this.directoryPath = directoryPath;
        this.lastSnapshotTime = System.currentTimeMillis();
        this.records = new ArrayList<>(); // Initialize the records list in the constructor
        populateRecords(); // Call a method to populate the records list
    }

    // Method to populate the records list
    private void populateRecords() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path filePath : stream) {
                FileType fileType = FileType.determineFileType(filePath);
                // Create new instances of Record based on file type and add them to the list
                switch (fileType) {
                    case IMAGE:
                        records.add(new Image(filePath));
                        break;
                    case TEXT:
                        records.add(new Text(filePath));
                        break;
                    case SCRIPT:
                        records.add(new Script(filePath));
                        break;
                    default:
                        records.add(new Record(filePath));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startMonitoring() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command;
            while (true) {
                System.out.print("Enter a command (commit, info <filename>, status, or exit): ");
                command = reader.readLine();

                switch (command) {
                    case "commit":
                        lastSnapshotTime = System.currentTimeMillis();
                        System.out.println("Snapshot time updated " + formatTime(lastSnapshotTime));
                        break;
                    case "status":
                        checkStatus();
                        break;
                    case "exit":
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        if (command.startsWith("info ")) {
                            // Parse the filename from the 'info' command
                            String[] parts = command.split(" ");
                            if (parts.length == 2) {
                                String fileName = parts[1];
                                printInfo(fileName);
                            } else {
                                System.out.println("Invalid 'info' command format.");
                            }
                        } else {
                            System.out.println("Invalid command. Available commands: commit, info <filename>, status, exit.");
                        }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkStatus() {
        // Specify the snapshot time
        System.out.println("Created Snapshot at: " + formatTime(lastSnapshotTime));

        List<Record> recordsToRemove = new ArrayList<>();

        // Implement the logic to check the status of files since the last snapshot time
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path filePath : stream) {
                FileType fileType = FileType.determineFileType(filePath);

                boolean found = false;

                for (Record record : records) {
                    if (record.getName().equals(filePath.getFileName().toString())) {
                        found = true;
                        long lastModifiedTime = record.lastModified();
                        // Check if the file was modified since the last snapshot time
                        if (lastModifiedTime > lastSnapshotTime) {
                            System.out.println(record.getName() + " - Change at " + formatTime(lastModifiedTime));
                        } else {
                            System.out.println(record.getName() + " - No change since " + formatTime(lastModifiedTime));
                        }
                        break;
                    }
                }

                if (!found) {
                    // New file
                    records.add(createRecord(fileType, filePath));
                    Record lastAddedRecord = records.get(records.size() - 1);

                    System.out.println(lastAddedRecord.getName() + " - New file created at" + lastAddedRecord.getCreationTime());

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove deleted files
        for (Record record : records) {
            boolean found = false;
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
                for (Path filePath : stream) {
                    if (record.getName().equals(filePath.getFileName().toString())) {
                        found = true;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!found) {
                System.out.println(record.getName() + " - Deleted");
                recordsToRemove.add(record);
            }
        }
        records.removeAll(recordsToRemove);
    }

    private Record createRecord(FileType fileType, Path filePath) {
        switch (fileType) {
            case IMAGE:
                return new Image(filePath);
            case TEXT:
                return new Text(filePath);
            case SCRIPT:
                return new Script(filePath);
            default:
                return new Record(filePath);
        }
    }

    private void printInfo(String fileName) {
        for (Record record : records) {
            if (record.getName().equals(fileName)) {
                System.out.println(record.getInfo());
                return;
            }
        }
        System.out.println("File not found: " + fileName);
    }

    private String formatTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeInMillis);
        return sdf.format(date);
    }

    public void detectFileChanges() {
        // This method detects file changes and updates the records list

        // Create a lock to synchronize access to the 'records' list
        Object lock = new Object();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            List<Record> newRecords = new ArrayList<>();

            for (Path filePath : stream) {
                FileType fileType = FileType.determineFileType(filePath);

                boolean found = false;

                // Use a lock to protect access to the shared 'records' list
                synchronized (lock) {
                    for (Record record : records) {
                        if (record.getName().equals(filePath.getFileName().toString())) {
                            found = true;
                            long lastModifiedTime = record.lastModified();
                            // Check if the file was modified since the last snapshot time
                            if (lastModifiedTime > lastSnapshotTime) {
                                System.out.println(record.getName() + " - Change at " + formatTime(lastModifiedTime));
                                this.lastSnapshotTime = lastModifiedTime;
                            }

                            break;
                        }
                    }

                    if (!found) {
                        // New file
                        newRecords.add(createRecord(fileType, filePath));
                    }
                }
            }

            // Use a lock to add new records and update the 'records' list
            synchronized (lock) {
                records.addAll(newRecords);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check for deleted files
        List<Record> recordsToRemove = new ArrayList<>();

        // Use a lock to protect access to the shared 'records' list
        synchronized (lock) {
            for (Record record : records) {
                boolean found = false;
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
                    for (Path filePath : stream) {
                        if (record.getName().equals(filePath.getFileName().toString())) {
                            found = true;
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!found) {
                    System.out.println(record.getName() + " - Deleted");
                    recordsToRemove.add(record);
                }
            }

            // Remove deleted records
            records.removeAll(recordsToRemove);
        }
    }

}
