package com.Controller.Memorys;

import com.Controller.ManagerTaskMap;
import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.Task;
import com.DateTask.TaskStatus;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MemoryTXT extends Memory{
    static String getNameFile() {
        String sep = File.separator;
        return "Date" + sep + "MyTaskPlus.txt";
    }
    static void createFile() {
        if (!(new File(getNameFile()).exists())) {
            new File("Date").mkdirs();
            File f = new File(getNameFile());
        }
    }
    @Override
    public void load() throws IOException {
        if (!(new File(getNameFile()).exists())) return;

        try (Scanner scanner = new Scanner(new File(getNameFile()))) {

            ArrayList<String> lineFile = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lineFile.add(scanner.nextLine());
            }
            Task task = null;
            for (int i = 0; i < lineFile.size(); ) {
                String type = lineFile.get(i++);
                String status = lineFile.get(i++);
                String name = lineFile.get(i++);
                String description = lineFile.get(i++);

                if (type.equalsIgnoreCase("TASK")) {
                    task = managerTaskMap.addTask(name, description);
                    task.setTaskStatus(TaskStatus.toTaskStatus(status));
                }
                if (type.equalsIgnoreCase("EPIC")) {
                    task = managerTaskMap.addEpic(name, description);
                    task.setTaskStatus(TaskStatus.toTaskStatus(status));
                }
                if (type.equalsIgnoreCase("SubTASK")) {
                    assert task != null;
                    SubTask subTask = (SubTask) managerTaskMap.addSubTaskToEpicID(name, description, task.getID());
                    subTask.setTaskStatus(TaskStatus.toTaskStatus(status));
                }
            }
        } catch (Exception e) {
            throw new IOException("Error MemoryTXT.load(): " + e.getMessage());
        }
    }

    @Override
    public void save() throws IOException {
        createFile();
        try (PrintWriter pw = new PrintWriter(new File(getNameFile()))) {
            for (Map.Entry<Integer, Task> entry : managerTaskMap.getListTask().entrySet()) {
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
            throw new IOException("Error MemoryTXT.save(): " + e.getMessage());
        }
    }
    static void write( Task task, PrintWriter pw){
        pw.println(task.getTypeTask());
        pw.println(task.getTaskStatus());
        pw.println(task.getName());
        pw.println(task.getDescription());
    }
}
