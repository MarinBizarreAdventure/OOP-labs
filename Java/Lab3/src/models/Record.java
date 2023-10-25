package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record extends File {
    private final String extension;
    public Record(Path path) {
        super(path.toString());
        int Lastindex = getName().lastIndexOf(".");
        extension = (Lastindex > 0) ? getName().substring(Lastindex +1) : "";
    }

    public String getExtension(){
        return extension;
    }

    public String getCreationTime(){
        try {
            FileTime fileTime = java.nio.file.Files.readAttributes(toPath(), BasicFileAttributes.class).creationTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(fileTime.toMillis());
            return sdf.format(date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getModificationTime(){
        try {
            FileTime fileTime = java.nio.file.Files.readAttributes(toPath(), BasicFileAttributes.class).lastModifiedTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(fileTime.toMillis());
            return sdf.format(date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getInfo(){
        String prettyExtension = extension.isEmpty() ? "No extension" : extension;
        return "Name:" + getName() + "\nextension:" + prettyExtension
                + "\nCreated time:" + getCreationTime() + "\nModification time:" + getModificationTime();
    }
}


