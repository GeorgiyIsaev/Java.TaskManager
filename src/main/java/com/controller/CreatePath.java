package com.controller;

import com.controller.controlException.ManagerFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatePath {
    private Path path;

    private CreatePath() {
        this("MyTask.bin");
    }

    private CreatePath(String nameFile) {
        Path path = Paths.get(getNameFile(nameFile));
    }

    private static String getNameFile(String nameFile) {
        String sep = File.separator;
        return "Date" + sep + nameFile;
    }

    public Path get() {
        return path;
    }

    public static CreatePath path() {
        return new CreatePath();
    }
    public static CreatePath path(String nameFile) {
        return new CreatePath(nameFile);
    }
}