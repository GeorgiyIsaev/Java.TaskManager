package com.controller;

import com.controller.controlException.ManagerFileException;
import com.controller.taskManager.TaskManager;
import com.dateTask.CreateID;
import com.dateTask.Task;

import javax.sound.midi.Patch;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class FileBackedCSV {
    private String fileName;

    public FileBackedCSV(String fileName){
        this.fileName = fileName;
       // this.fileName = "MyTask.bin";
    }
    private String getFileName() {
        String sep = File.separator;
        return "Date" + sep + fileName;
    }

    private void createFile() {
        if (!(new File(getFileName()).exists())) {
            new File("Date").mkdirs();
            File f = new File(getFileName());
        }
    }


    private String wrapperSpecialCharacters(String line){
        line = line.replaceAll(",", " \\,");
        line = line.replace("\"", "\"\"");
        return line;
    }
    public String createLineTaskForCSV(Task task){
        String line = "";
        line += task.getID();   line += ", ";
        line += task.getTypeTask(); line += ", ";
        line += task.getStatus(); line += ", ";
        line += wrapperSpecialCharacters(task.getName()); line += ", ";
        line += wrapperSpecialCharacters(task.getDescription());line += ", ";
        line += task.getLinkStr();
        return line;
    }

    public String createTable(TaskManager taskManager){
        StringBuilder tableCSV = new StringBuilder();
        for (Map.Entry<Integer, Task> entry : taskManager.getTasks().entrySet()) {
            Task task = entry.getValue();
            tableCSV.append("\n");
            tableCSV.append(createLineTaskForCSV(task));
        }
        return tableCSV.toString();
    }


    public void save(TaskManager taskManager) {
        createFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFileName()))) {
            oos.writeObject(createTable(taskManager));
        } catch (IOException e) {
            throw new ManagerFileException(e);
        }
    }
    public void load(TaskManager taskManager) {
        Map<Integer, Task> tasksMap;
        if (!(new File(getFileName()).exists())) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getFileName()))) {
            tasksMap = (TreeMap<Integer, Task>) ois.readObject();
            for (Map.Entry<Integer, Task> entry : tasksMap.entrySet()) {
                CreateID.INSTANCE.setId(entry.getKey());
            }
            taskManager.replacementTasks(tasksMap);

        } catch (IOException | ClassNotFoundException e) {
            throw new ManagerFileException(e);
        }
    }





}
