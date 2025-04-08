package com.Controller;

import com.DateTask.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

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

    public static void writeTasks(HashMap<Integer, Task> listTask) throws Exception {
        createFile();
        try (PrintWriter pw = new PrintWriter(new File(getNameFile()))) {
            for (Map.Entry<Integer, Task> entry : listTask.entrySet()) {
                Task value = entry.getValue();
                if ("TASK".equalsIgnoreCase(value.getTypeTask())) {
                    write(value, pw);
                }
                else if ("EPIC".equalsIgnoreCase(value.getTypeTask())) {
                    write(value, pw);
                    for (SubTask subTask : ((EpicTask)value).getSubTasks()){
                        write(subTask, pw);
                    }
                }
            }
        } catch (IOException e) {
           throw new Exception("Error MemoryTaskMapToTXT.WriteTasks(): " + e.getMessage());
        }
    }
    static void write( Task task, PrintWriter pw){
        pw.println(task.getTypeTask());
        pw.println(task.getTaskStatus());
        pw.println(task.getName());
        pw.println(task.getDescription());
    }



    public static ManagerTaskMap readTasks() throws Exception {
       ManagerTaskMap taskMap = new ManagerTaskMap();

       try (Scanner scanner = new Scanner(new File(getNameFile()))) {


            ArrayList<String> lineFile = new ArrayList<>();
           while (scanner.hasNextLine()) {
               lineFile.add(scanner.nextLine());
           }
          Task task=null;
          for(int i = 0; i< lineFile.size();){
              String type = lineFile.get(i++);
              String status = lineFile.get(i++);
              String name = lineFile.get(i++);
              String description = lineFile.get(i++);

              if (type.equalsIgnoreCase("TASK")){
                  task =  taskMap.addTask(name,description);
                  task.setTaskStatus(TaskStatus.toTaskStatus(status));
              }
              if (type.equalsIgnoreCase("EPIC")){
                  task =  taskMap.addEpic(name,description);
                  task.setTaskStatus(TaskStatus.toTaskStatus(status));
              }
              if (type.equalsIgnoreCase("SubTASK")){
                  assert task != null;
                  SubTask subTask = (SubTask)taskMap.addSubTaskToEpicID(name,description, task.getID());
                  subTask.setTaskStatus(TaskStatus.toTaskStatus(status));
              }
          }
       } catch (IOException | ClassNotFoundException e) {
           throw new Exception("Error ReadTasks: " + e.getMessage());
       }
       return taskMap;
   }
}

