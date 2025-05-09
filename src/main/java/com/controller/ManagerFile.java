package com.controller;

import com.controller.controlException.ManagerFileException;
import com.dateTask.CreateID;
import com.dateTask.Task;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class ManagerFile {
    private static String getNameFile() {
        String sep = File.separator;
        return "Date" + sep + "MyTask.bin";
    }
    private static void createFile() {
        if (!(new File(getNameFile()).exists())) {
            new File("Date").mkdirs();
            File f = new File(getNameFile());
        }
    }
    public static void save(IManagerTask managerTaskInMemory)  {
        Map<Integer, Task> tasksMap = new TreeMap<>();
        tasksMap.putAll(managerTaskInMemory.getTaskMap());
        createFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getNameFile()));) {
            oos.writeObject(tasksMap);
        } catch (IOException e) {
            throw new ManagerFileException(e);
        }
    }
    public static void load(IManagerTask managerTaskInMemory) {
       Map<Integer, Task> tasksMap;
        if (!(new File(getNameFile()).exists())) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getNameFile()))) {
            tasksMap = (TreeMap<Integer, Task>) ois.readObject();
            for (Map.Entry<Integer, Task> entry : tasksMap.entrySet()) {
                CreateID.INSTANCE.setId(entry.getKey());
            }
            managerTaskInMemory.setTaskMap(tasksMap);

        } catch (IOException | ClassNotFoundException e) {
            throw new ManagerFileException(e);
        }
    }
}
