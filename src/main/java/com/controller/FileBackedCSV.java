package com.controller;

import com.controller.controlException.ManagerFileException;
import com.controller.controlException.NotExistIdException;
import com.controller.taskManager.TaskManager;
import com.dateTask.*;

import java.io.*;
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
        //System.out.println(Arrays.toString(elements));

        final int ID = 0 ;
        final int TYPE = 1 ;
        final int STATUS = 2 ;
        final int NAME = 3 ;
        final int DESCRIPTION = 4 ;
        final int LINK = 5 ;

        int id = toInt(elements[ID]);
        String name = clearWrapper(elements[NAME]);
        String description = clearWrapper(elements[DESCRIPTION]);
        String status = elements[STATUS];

        if(elements[TYPE].equals(TaskType.TASK.name())){
            Task task = taskManager.addTaskByID(id, name,description);
            task.setStatus(TaskStatus.toTaskStatus(status));
        }
        if(elements[TYPE].equals(TaskType.SUBTASK.name())){
            int idEpicAdded = toInt(elements[LINK]);
            Task task = taskManager.addSubTaskToEpicIDByID(id,idEpicAdded, name,description);
            task.setStatus(TaskStatus.toTaskStatus(status));

        }
        if(elements[TYPE].equals(TaskType.EPIC.name())){
            Task task = taskManager.addEpicByID(id,name,description);
            task.setStatus(TaskStatus.toTaskStatus(status));
        }
        CreateID.INSTANCE.setId(id);
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
            throw new ManagerFileException(e);
        }
    }
}
