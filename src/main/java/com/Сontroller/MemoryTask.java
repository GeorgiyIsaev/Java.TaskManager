package com.Сontroller;

import com.DateTask.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MemoryTask {

    static String getNameFile(){
      // String sep = File.separator;
        String sep = "//";

        return "Date" + sep + "MyTask.bin";
    }


    static void createFile(){
        if(!(new File(getNameFile()).exists())){
            new File("Date").mkdirs();
            File f = new File(getNameFile());
        }
    }

    public static void WriteTaskList(HashMap<Integer,Task> listTask){
        createFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getNameFile()));) {
            oos.writeObject(listTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer,Task> ReadTaskList(){
        HashMap<Integer,Task> taskList = new HashMap<>();
        if(!(new File(getNameFile()).exists())){
            return taskList;
        }

       try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getNameFile()))){
           taskList = (HashMap<Integer,Task>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
       return taskList;
    }


}
