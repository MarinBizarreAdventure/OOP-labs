import monitor.ChangeMonitor;
import monitor.ChangeMonitorApp;

import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {

        Path directoryPath = Path.of("D:\\secrete\\univer\\anul2\\labs\\Java\\Lab3\\test");
        ChangeMonitor changeMonitor = new ChangeMonitor(directoryPath);

        // Start the console input processing thread
        Thread consoleThread = new Thread(() -> {
            changeMonitor.startMonitoring();
        });
        consoleThread.start();
        // Create a scheduled executor to run detectFileChanges every 5 seconds
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> changeMonitor.changeMonitoring(), 0, 5, TimeUnit.SECONDS);
    }
}
