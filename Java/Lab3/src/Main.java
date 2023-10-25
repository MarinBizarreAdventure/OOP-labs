import java.io.IOException;
import java.nio.file.*;



import java.nio.file.*;

class FileMonitor {
    private WatchService watchService;

    public FileMonitor(String directoryPath) throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        Path directory = Path.of(directoryPath);
        directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
    }

    public void startMonitoring() {
        try {
            while (true) {
                WatchKey watchKey = watchService.take();
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                    Path fileName = pathEvent.context();
                    WatchEvent.Kind<?> kind = event.kind();

                    handleEvent(kind, fileName);
                }
                watchKey.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void handleEvent(WatchEvent.Kind<?> kind, Path fileName) {
        // Subclasses will implement this method to handle specific events.
    }
}

class FileChangeMonitor extends FileMonitor {
    public FileChangeMonitor(String directoryPath) throws IOException {
        super(directoryPath);
    }

    @Override
    protected void handleEvent(WatchEvent.Kind<?> kind, Path fileName) {
        if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
            System.out.println("A file has been modified: " + fileName);
        }
    }
}

class FileCreateMonitor extends FileMonitor {
    public FileCreateMonitor(String directoryPath) throws IOException {
        super(directoryPath);
    }

    @Override
    protected void handleEvent(WatchEvent.Kind<?> kind, Path fileName) {
        if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
            System.out.println("A new file is created: " + fileName);
        }
    }
}

public class Main {
    public static void main(String[] args) {
//        try {
//            String directoryPath = "D:\\secrete\\univer\\anul2\\labs\\Java\\Lab3\\test";
//            FileChangeMonitor changeMonitor = new FileChangeMonitor(directoryPath);
//            FileCreateMonitor createMonitor = new FileCreateMonitor(directoryPath);
//            Thread changeThread = new Thread(changeMonitor::startMonitoring);
//            Thread createThread = new Thread(createMonitor::startMonitoring);
//            changeThread.start();
//            createThread.start();
//            changeThread.join();
//            createThread.join();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Create a Path for a sample file
        Path filePath = Path.of("D:\\secrete\\univer\\anul2\\labs\\Java\\Lab3\\test\\gay.txt");

        // Create a Record object
        models.Record record = new models.Record(filePath);
        // Verify if the getExtension method works
        System.out.println("File Extension: " + record.getExtension());

        // Verify if the getCreationTime method works
        System.out.println("Creation Time: " + record.getCreationTime());

        // Verify if the getModificationTime method works
        System.out.println("Modification Time: " + record.getModificationTime());
    }
}
