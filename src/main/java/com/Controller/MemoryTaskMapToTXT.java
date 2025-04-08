/*
package com.Controller;

import com.DateTask.CreateID;
import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.Task;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MemoryTaskMapToTXT {
    static String getNameFile() {
        String sep = File.separator;
        return "Date" + sep + "MyTask.txt";
    }
    static void createFile() {
        if (!(new File(getNameFile()).exists())) {
            new File("Date").mkdirs();
            File f = new File(getNameFile());
        }
    }

    public static void WriteTasks(HashMap<Integer, Task> listTask) throws Exception {
        createFile();
        try (PrintWriter pw = new PrintWriter(new File(getNameFile()))) {
            for (Map.Entry<Integer, Task> entry : listTask.entrySet()) {
                Task value = entry.getValue();
                if ("TASK".equalsIgnoreCase(value.getTypeTask())) {
                    pw.println("Type: " + value.getTypeTask());
                    pw.println("Name: " + value.getName());
                    pw.println("Description: " + value.getDescription());
                    pw.println("Status: " + value.getTaskStatus());
                }
                if ("EPIC".equalsIgnoreCase(value.getTypeTask())) {
                    pw.println("Type: " + value.getTypeTask());
                    pw.println("Name: " + value.getName());
                    pw.println("Description: " + value.getDescription());
                    pw.println("Status: " + value.getTaskStatus());

                    for (SubTask subTask : ((EpicTask)value).getSubTasks()){
                        pw.println("Type: " + subTask.getTypeTask());
                        pw.println("Name: " + subTask.getName());
                        pw.println("Description: " + subTask.getDescription());
                        pw.println("Status: " + subTask.getTaskStatus());
                    }
                }
            }
        } catch (IOException e) {
           throw new Exception("Error WriteTasks: " + e.getMessage());
        }
    }

    public static HashMap<Integer, Task> ReadTasks() throws Exception {
        HashMap<Integer, Task> taskMap = new HashMap<>();
        if (!(new File(getNameFile()).exists())) {
            return taskMap;
        }

        try (Scanner scanner = new Scanner(new File(getNameFile()))) {
            String fullTXT = scanner.nextLine();
            String[] words = fullTXT.split("\n");


          for (String line : words){
              if(line.equalsIgnoreCase("Type: TASK")){
                  Integer id = CreateID.getNewID();

                  Task task = new Task();
              }


          }



            int count =0;
            do{
                System.out.println(count++);
                Task person1 = (Task)ois .readObject();
                taskMap.put(person1.getID(),person1);
                if (person1.getTypeTask().equalsIgnoreCase("EPIC")){
                    for(SubTask subTask : ((EpicTask)person1).getSubTasks()) {
                        taskMap.put(subTask.getID(),subTask);
                    }
                }
            }while(true);


            ois.read




            taskList = (HashMap<Integer, Task>)ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Error ReadTasks: " + e.getMessage());
        }
        return taskList;
    }

}
*/
