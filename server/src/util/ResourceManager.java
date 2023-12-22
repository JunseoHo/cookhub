package main.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ResourceManager {

    public static final String DEFAULT_TARGET = "welcome.html";

    private static String readFile(String path) {
        if (path.isEmpty()) path = DEFAULT_TARGET;
        File file = new File(path);
        if (!file.exists()) return null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String content = "";
            String line;
            while ((line = reader.readLine()) != null) content += line + "\n";
            return content;
        } catch (IOException e) {
            return null;
        }
    }

}
