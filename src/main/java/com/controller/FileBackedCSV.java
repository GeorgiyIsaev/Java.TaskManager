package com.controller;

import com.controller.controlException.ManagerFileException;
import com.controller.taskManager.TaskManager;
import com.dateTask.*;

import javax.sound.midi.Patch;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    /// Блок Записи в файл
    private String wrapperSpecialCharacters(String line){
        line = line.replaceAll(",", "(,)");
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
        try (PrintWriter pw = new PrintWriter(new File(getFileName()))) {
            pw.write(createTable(taskManager));
        } catch (IOException e) {
            throw new ManagerFileException(e);
        }
    }



    /// Блок чтения из файла
    private int toInt(String intLine){
        int i;
        try {
            i = Integer.parseInt(intLine);
        } catch (NumberFormatException e) {
            i = -1;
        }
        return i;
    }
    private String clearWrapper(String line){
        line = line.replaceAll("\\(,\\)", ",");
        line = line.replace("\"\"" , "\"");
        return line;
    }
    private void parseLineAndAddTaskToManager(TaskManager taskManager, String lineSCV){
        String[] elements = lineSCV.split(", ");
        if(elements.length <5 ) return;
        System.out.println(Arrays.toString(elements));

        if(elements[1].equals(TaskType.TASK.name())){
            int id = toInt(elements[0]);
            String name = clearWrapper(elements[3]);
            String description = clearWrapper(elements[4]);
            Task task = taskManager.addTaskByID(id, name,description);
            task.setStatus(TaskStatus.toTaskStatus(elements[1]));
        }
        if(elements[1].equals(TaskType.SUBTASK.name())){
            int id = toInt(elements[0]);
            String name = clearWrapper(elements[3]);
            String description = clearWrapper(elements[4]);
            int idEpicAdded = toInt(elements[5]);
            Task task = taskManager.addSubTaskToEpicIDByID(id,idEpicAdded, name,description);
            task.setStatus(TaskStatus.toTaskStatus(elements[1]));
        }
        if(elements[1].equals(TaskType.EPIC.name())){
            int id = toInt(elements[0]);
            String name = clearWrapper(elements[3]);
            String description = clearWrapper(elements[4]);
            Task task = taskManager.addEpicByID(id,name,description);
            task.setStatus(TaskStatus.toTaskStatus(elements[1]));
        }
    }
    public void load(TaskManager taskManager) {
        if (!(new File(getFileName()).exists())) {
            return;
        }
        try (Scanner scanner = new Scanner(new File(getFileName()))){
            String lineSCV;
            while (scanner.hasNextLine()) {
                lineSCV = scanner.nextLine();
                System.out.println(lineSCV);
                parseLineAndAddTaskToManager(taskManager, lineSCV);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





//        //Map<Integer, Task> tasksMap;
//        try (BufferedReader br = new BufferedReader  (new FileReader(getFileName()))) {
//           while (br.n)
//            String tableSCV = br.readLine();
//            System.out.println(tableSCV);
//            while (tableSCV != null) {
//                System.out.println(tableSCV);
//                // read next line
//                tableSCV = tableSCV.readLine();
//            }
//
//            //tasksMap = (TreeMap<Integer, Task>) ois.readObject();
//            //for (Map.Entry<Integer, Task> entry : tasksMap.entrySet()) {
//            //    CreateID.INSTANCE.setId(entry.getKey());
//            //}
//           // taskManager.replacementTasks(tasksMap);
//
//        } catch (IOException e) {
//            throw new ManagerFileException(e);
//        }
    }







}
