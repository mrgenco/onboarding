package com.mrg.onboarding.document;

import com.mrg.onboarding.document.dto.DocumentRawDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class FileUtils {

    public static boolean isFileExist(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    public static String readMarkdownFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createFile(String filePath, String content) throws IOException {
        Files.writeString(Path.of(filePath), content);
    }

    public static void deleteIfExist(String path) throws IOException {
        Files.deleteIfExists(Path.of(path));

    }
}
