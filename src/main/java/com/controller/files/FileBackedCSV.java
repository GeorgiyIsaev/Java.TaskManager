package com.controller.files;

import com.controller.controlException.ManagerFileException;
import com.controller.taskManager.TaskManager;
import com.dateTask.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class FileBackedCSV {
    Path pathFile;
    public FileBackedCSV(Path pathFile) {
        this.pathFile = pathFile;
    }

    private void createFileIfNotExists() {
        Path directory = pathFile.getParent();
        //System.out.println("Путь проверки " + directory);
        if(directory != null && !Files.isDirectory(directory)){
            //System.out.println("Требуется создание: " + directory);
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new ManagerFileException(e);
            }
        }
//        else{
//            System.out.println("Создание: " + directory + " не требуется!");
//        }
    }

    /// Блок Записи в файл
    private String wrapperSpecialCharacters(String line){
        line = line.replaceAll(",", "(,)");
        line = line.replace("\"", "\"\"");
        return line;
    }
    public String createLineTaskForCSV(Task task){
        return task.getID() + ", " +
                task.getTypeTask() + ", " +
                task.getStatus() + ", " +
                wrapperSpecialCharacters(task.getName()) + ", " +
                wrapperSpecialCharacters(task.getDescription()) +
                ", " +
                task.getLinkStr();
    }
    public String createLineHistory(TaskManager taskManager){
        StringBuilder line = new StringBuilder("\nHISTORY");
        for (Task historyTask :  taskManager.getHistory()) {
            line.append(", ").append(historyTask.getID());
        }
        return line.toString();
    }

    public String createTable(TaskManager taskManager){
        StringBuilder tableCSV = new StringBuilder();
        for (Map.Entry<Integer, Task> entry : taskManager.getTasks().entrySet()) {
            Task task = entry.getValue();
            tableCSV.append("\n");
            tableCSV.append(createLineTaskForCSV(task));
        }
        tableCSV.append(createLineHistory(taskManager));
        return tableCSV.toString();
    }
    public void save(TaskManager taskManager) {
        //File file = pathFile.toFile();
        createFileIfNotExists();

        try (PrintWriter pw = new PrintWriter(pathFile.toFile())) {
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
    private boolean createNewHistoryReturnTrueIfRecording(TaskManager taskManager, String[] elementsLineCSV){
        if(!elementsLineCSV[0].equals("HISTORY") ) { return false; }
        for (int i= 1; i<elementsLineCSV.length; i++){
            taskManager.getTask(toInt(elementsLineCSV[i]));
        }
        return true;
    }

    private void parseLineAndAddTaskToManager(TaskManager taskManager, String lineSCV){
        String[] elements = lineSCV.split(", ");
        if(createNewHistoryReturnTrueIfRecording(taskManager, elements)){return;}
        if(elements.length <5 ) {return;}

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
        boolean isNotFileExist = !pathFile.toFile().exists();
        if(isNotFileExist) {
            return;
        }
        try {
            String fileContent = Files.readString(pathFile);
            String[] lines = fileContent.split("\n");
        } catch (IOException e) {
            throw new ManagerFileException(e);
        }
    }
}
