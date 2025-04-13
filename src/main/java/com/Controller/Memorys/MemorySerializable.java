package com.Controller.Memorys;

import com.Controller.ManagerTaskMap;
import com.DateTask.CreateID;

import java.io.*;

public class MemorySerializable extends Memory{
    static String getNameFile() {
        String sep = File.separator;
        return "Date" + sep + "MyTaskSer.bin";
    }
    static void createFile() {
        if (!(new File(getNameFile()).exists())) {
            new File("Date").mkdirs();
            File f = new File(getNameFile());
        }
    }

    @Override
    public void load() throws IOException {
       // HashMap<Integer, Task> taskList = new HashMap<>();
        if (!(new File(getNameFile()).exists())) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getNameFile()))) {
            managerTaskMap = (ManagerTaskMap) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Error MemorySerializable.load():" + e.getMessage());
        }
        CreateID.newStartID(managerTaskMap.getCurrentID());
    }

    @Override
    public void save() throws IOException {
        createFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getNameFile()));) {
            oos.writeObject(managerTaskMap);
        } catch (IOException e) {
            throw new IOException("Error MemorySerializable.save():" + e.getMessage());
        }
    }
}
