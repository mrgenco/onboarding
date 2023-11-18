package com.mrg.onboarding.document;

import com.mrg.onboarding.document.dto.DocumentRawDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class FileUtils {

    public static String readMarkdownFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createMarkdownFile(String filePath, DocumentRawDto documentRawDto) {
        try {
            Files.createFile(Paths.get(filePath), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
