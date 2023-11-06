package monitor;

import models.*;
import models.Record;
import enums.FileType;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private Path directoryPath;
    private List<Record> records;

    public FileProcessor(Path directoryPath) {
        this.directoryPath = directoryPath;
        this.records = new ArrayList<>();
    }

    public void populateRecords() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path filePath : stream) {
                FileType fileType = FileType.determineFileType(filePath);
                Record record = createRecord(fileType, filePath);
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkStatus(long lastSnapshotTime) {
        for (Record record : records) {
            long lastModifiedTime = record.lastModified();
            if (lastModifiedTime > lastSnapshotTime) {
                System.out.println(record.getName() + " - Changed since " + formatTime(lastModifiedTime));
            } else {
                System.out.println(record.getName() + " - Unchanged since " + formatTime(lastModifiedTime));
            }
        }
    }

    public Record createRecord(FileType fileType, Path filePath) {
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

    private String formatTime(long timeInMillis) {
        // Format time as required
        return String.valueOf(timeInMillis);
    }
}

