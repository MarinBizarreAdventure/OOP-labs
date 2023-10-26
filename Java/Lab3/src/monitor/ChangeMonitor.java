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
import java.util.List;
import java.nio.file.*;


public class ChangeMonitor {
    private final Path directoryPath;
    private long lastSnapshotTime;

    private final List<Record> records;
    private WatchService watchService;


    public ChangeMonitor(Path directoryPath) {
        this.directoryPath = directoryPath;
        this.lastSnapshotTime = System.currentTimeMillis();
        this.records = new ArrayList<>();
        try {
            this.watchService = FileSystems.getDefault().newWatchService();
            directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        populateRecords();
    }

    // Method to populate the records list
    private void populateRecords() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path filePath : stream) {
                FileType fileType = FileType.determineFileType(filePath);
                // Create new instances of Record based on file type and add them to the list
                records.add(createRecord(fileType, filePath));
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
                    case "commit" -> {
                        lastSnapshotTime = System.currentTimeMillis();
                        System.out.println("Snapshot time updated " + formatTime(lastSnapshotTime));
                    }
                    case "status" -> checkStatus();
                    case "exit" -> {
                        System.out.println("Exiting the program.");
                        return;
                    }
                    default -> {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void checkStatus() {
        System.out.println("Created Snapshot at: " + formatTime(lastSnapshotTime));
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path filePath : stream) {
                FileType fileType = FileType.determineFileType(filePath);

                Record existingRecord = findRecordByName(filePath.getFileName().toString());
                if (existingRecord != null) {
                    long lastModifiedTime = existingRecord.lastModified();
                    handleExistingRecord(existingRecord, lastModifiedTime);
                } else {
                    handleNewFile(fileType, filePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        removeDeletedFiles();
    }

    private Record findRecordByName(String fileName) {
        return records.stream()
                .filter(record -> record.getName().equals(fileName))
                .findFirst()
                .orElse(null);
    }

    private void handleExistingRecord(Record record, long lastModifiedTime) {
        if (lastModifiedTime > lastSnapshotTime) {
            System.out.println(record.getName() + " - Change at " + formatTime(lastModifiedTime));
        } else {
            System.out.println(record.getName() + " - No change since " + formatTime(lastModifiedTime));
        }
    }

    private void handleNewFile(FileType fileType, Path filePath) {
        records.add(createRecord(fileType, filePath));
        Record lastAddedRecord = records.get(records.size() - 1);
        System.out.println(lastAddedRecord.getName() + " - New file created at " + lastAddedRecord.getCreationTime());
    }

    private void removeDeletedFiles() {
        List<Record> recordsToRemove = new ArrayList<>();
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
        return switch (fileType) {
            case IMAGE -> new Image(filePath);
            case TEXT -> new Text(filePath);
            case SCRIPT -> new Script(filePath);
            default -> new Record(filePath);
        };
    }

    private void printInfo(String fileName) {
        Record record = records.stream()
                .filter(r -> r.getName().equals(fileName))
                .findFirst()
                .orElse(null);

        if (record != null) {
            System.out.println(record.getInfo());
            return;
        }
        System.out.println("File not found: " + fileName);
    }


    private String formatTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeInMillis);
        return sdf.format(date);
    }


    // Monitoring part
    public void changeMonitoring() {
        try {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                handleFileEvent(event);
            }
            key.reset();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleFileEvent(WatchEvent<?> event) {
        Path filePath = (Path) event.context();
//        FileType fileType = FileType.determineFileType(directoryPath.resolve(filePath));
        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
            handleFileCreation(filePath);
        } else if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
            handleFileModification(filePath);
        } else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
            handleFileDeletion(filePath);
        }
    }

    private void handleFileCreation(Path filePath) {
        System.out.println(filePath + " - New file created at " + formatTime(System.currentTimeMillis()));
    }

    private void handleFileModification(Path filePath) {
        for (Record record : records) {
            if (record.getName().equals(filePath.getFileName().toString())) {
                System.out.println(filePath + " - Change at " + formatTime(System.currentTimeMillis()));
                break;
            }
        }
    }

    private void handleFileDeletion(Path filePath) {
        System.out.println(filePath + " - Deleted");
    }

}
