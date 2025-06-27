package com.controller.files;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatePath {
    private CreatePath(){}

    public static CreatePath of(){
        return new CreatePath();
    }

    public Path generatePath(){
        Path filePath = Path.of("MyCSV.csv");
        return filePath;
    }

    public Path generatePath(String fileName){
        Path filePath = Path.of(fileName);
        return filePath;
    }

    public Path generatePathToPakDate(String fileName){
        String sep = File.separator;
        Path filePath = Path.of( "date" + sep + fileName);
        return filePath;
    }
    public Path generatePathToPakDateAndSave(String fileName){
        String sep = File.separator;
        Path filePath = Path.of( "date" + sep + "save" + sep + fileName);
        return filePath;
    }

    public Path generateToPakResources(String fileName){
        String sep = File.separator;
        System.out.println("Создаем патч");
        Path source = Paths.get(this.getClass().getResource(sep).getPath());
        System.out.println("source " + source);
        Path filePath = Paths.get(source.toAbsolutePath()
                + sep + "date" + sep + fileName);
        System.out.println("filePath " + filePath);
        return filePath;
    }
}