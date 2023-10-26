package monitor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeMonitorApp {
    public static void main(String[] args) {
        Path directoryPath = Paths.get("D:\\secrete\\univer\\anul2\\labs\\Java\\Lab3\\test");

        // Create a ChangeMonitor instance
        ChangeMonitor changeMonitor = new ChangeMonitor(directoryPath);

        // Start the console input processing thread
        Thread consoleThread = new Thread(() -> {
            changeMonitor.startMonitoring();
        });
        consoleThread.start();

        // Create a scheduled executor to run detectFileChanges every 5 seconds
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(() -> changeMonitor.changeMonitoring(), 0, 5, TimeUnit.SECONDS);
    }
}

