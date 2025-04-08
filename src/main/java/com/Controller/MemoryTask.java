package com.Controller;

import com.DateTask.CreateID;
import com.DateTask.Task;

import java.io.*;
import java.util.HashMap;

public class MemoryTask {

    static String getNameFile() {
        String sep = File.separator;
        return "Date" + sep + "MyTask.bin";
    }


    static void createFile() {
        if (!(new File(getNameFile()).exists())) {
            new File("Date").mkdirs();
            File f = new File(getNameFile());
        }
    }

    public static void WriteTaskList(HashMap<Integer, Task> listTask) throws Exception {
        createFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getNameFile()));) {
            oos.writeObject(listTask);
        } catch (IOException e) {
            throw new Exception("Error MemoryTask.ReadTaskList():" + e.getMessage());
        }
    }

    public static HashMap<Integer, Task> ReadTaskList() throws Exception {
        HashMap<Integer, Task> taskList = new HashMap<>();
        if (!(new File(getNameFile()).exists())) {
            return taskList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getNameFile()))) {
            taskList = (HashMap<Integer, Task>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Error MemoryTask.ReadTaskList():" + e.getMessage());
        }
        CreateID.newStartID(taskList.size());
        return taskList;
    }
}
