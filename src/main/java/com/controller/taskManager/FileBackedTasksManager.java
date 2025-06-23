package com.controller.taskManager;

import com.controller.FileBackedCSV;
import com.dateTask.Task;
import com.dateTask.TaskStatus;

import java.util.List;
import java.util.Map;

public class FileBackedTasksManager extends InMemoryTaskManager{
   private final FileBackedCSV fileBackedCSV;
   public FileBackedTasksManager(String fileName){
       this.fileBackedCSV = new FileBackedCSV(fileName);
       this.fileBackedCSV.load(this);
   }

   private void save(){
       this.fileBackedCSV.save(this);
   }


    @Override
    public Task addTask(String name, String description){
       Task task = super.addTask(name, description);
       save();
       return task;
    }

    @Override
    public Task addEpic(String name, String description){
        Task task = super.addEpic(name, description);
        save();
        return task;
    }

    @Override
    public Task addSubTaskToEpicID(int idEpic, String name, String description){
        Task task = super.addSubTaskToEpicID(idEpic, name, description);
        save();
        return task;
    }

    @Override
    public Task addTaskByID(int id, String name, String description){
        Task task = super.addTaskByID(id, name, description);
        save();
        return task;
    }

    @Override
    public Task addEpicByID(int id,String name, String description){
        Task task = super.addEpicByID(id, name, description);
        save();
        return task;
    }

    @Override
    public Task addSubTaskToEpicIDByID(int idSub, int idEpicAdded, String name, String description){
        Task task = super.addSubTaskToEpicIDByID(idSub,idEpicAdded,name, description);
        save();
        return task;
    }

    @Override
    public void deleteALL(){
        super.deleteALL();
        save();
    }

    @Override
    public Task deleteIDTask(int idTask){
        Task task = super.deleteIDTask(idTask);
        save();
        return task;
    }

    @Override
    public Task reNameToIDTask(int idTask, String newName){
        Task task = super.reNameToIDTask(idTask,newName);
        save();
        return task;
    }

    @Override
    public Task reDescToIDTask(int idTask, String newDescription){
        Task task = super.reDescToIDTask(idTask, newDescription);
        save();
        return task;
    }

    @Override
    public boolean reStatus(int idTask, TaskStatus taskStatus){
        boolean result =  super.reStatus(idTask,taskStatus);
        save();
        return result;
    }

    @Override
    public void replacementTasks(Map<Integer, Task> tasks) {
        super.replacementTasks(tasks);
        save();
    }

    @Override
    public Task getTask(int idTask){
        Task task = super.getTask(idTask);
        save();
        return task;
    }
}
