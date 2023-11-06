package utils;

import models.Record;
import models.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    public static int getLineCount(Record record) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(record))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            // Handle the IOException, e.g., by printing an error message
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return lineCount;
    }


    public static int getWordCount(Record record) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(record));
            int wordCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount++;
                    }
                }
            }
            reader.close();
            return wordCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static int getCharacterCount(Record record) {
        int charCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(record))) {
            int c;
            while ((c = reader.read()) != -1) {
                if (!Character.isWhitespace(c)) {
                    charCount++;
                }
            }
        } catch (IOException e) {
            // Handle the IOException, e.g., by printing an error message
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return charCount;
    }
    public static int getClassCount(Record record) {
        try (BufferedReader reader = new BufferedReader(new FileReader(record))) {
            String line;
            int classCount = 0;
            boolean insideClass = false;

            while ((line = reader.readLine()) != null) {
                if ((line.contains(" class ") ||line.trim().startsWith("class ") )&& !line.trim().startsWith("//")) classCount++;

            }

            return classCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getMethodCount(Record record) {
        try (BufferedReader reader = new BufferedReader(new FileReader(record))) {
            String line;
            int methodCount = 0;
            boolean insideMethod = false;
            boolean insidePythonMethod = false;

            while ((line = reader.readLine()) != null) {
                // Check for Java method declarations
                if (line.matches(".*\\b\\w+\\s+\\w+\\(.*\\)\\s*\\{.*")) {
                    if (!insideMethod && !insidePythonMethod) {
                        methodCount++;
                        insideMethod = true;
                        insidePythonMethod = false;
                    }
                } else if (line.trim().equals("}") && insideMethod) {
                    insideMethod = false;
                }

                // Check for Python function definitions
                if (line.matches("\\s*def\\s+\\w+\\s*\\(.*\\):.*") && !insideMethod) {
                    if (!insidePythonMethod) {
                        methodCount++;
                        insidePythonMethod = true;
                        insideMethod = false;
                    }
                }
            }

            return methodCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
