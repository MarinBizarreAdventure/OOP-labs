package enums;

import java.nio.file.Path;
public enum FileType {
    IMAGE(".png", ".jpg"),
    TEXT(".txt"),
    SCRIPT(".py", ".java"),
    OTHER;

    private final String[] extensions;

    FileType(String... extensions) {
        this.extensions = extensions;
    }


    public static FileType determineFileType(Path filePath) {
        String fileName = filePath.getFileName().toString();
        for (FileType fileType : values()) {
            for (String extension : fileType.extensions) {
                if (fileName.endsWith(extension)) {
                    return fileType;
                }
            }
        }
        return OTHER;
    }
}
