package com.Controller;

import com.Controller.ControlException.ManagerFileException;
import com.DateTask.CreateID;
import com.DateTask.Task;

import java.io.*;
import java.util.HashMap;
import java.util.List;
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

    public static void save(ManagerTask managerTask)  {
        createFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getNameFile()));) {
            oos.writeObject(managerTask.getListTask());
        } catch (IOException e) {
            throw new ManagerFileException(e);
        }
    }
    public static void load(ManagerTask managerTask) {
        Map<Integer, Task> tasksMap;
        if (!(new File(getNameFile()).exists())) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getNameFile()))) {
            tasksMap = (TreeMap<Integer, Task>) ois.readObject();
            for (Map.Entry<Integer, Task> entry : tasksMap.entrySet()) {
                CreateID.INSTANCE.setId(entry.getKey());
            }
            managerTask.setListTask(tasksMap);

        } catch (IOException | ClassNotFoundException e) {
            throw new ManagerFileException(e);
        }
    }
}
