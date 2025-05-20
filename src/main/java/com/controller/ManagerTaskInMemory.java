package com.controller;

import com.controller.controlException.NotChangedEpicStatusException;
import com.controller.controlException.NotEpicException;
import com.controller.controlException.NotExistIdException;
import com.dateTask.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ManagerTaskInMemory implements Serializable, IManagerTask {
    private Map<Integer, Task> tasks;
    private IHistoryManager history;
    public ManagerTaskInMemory() {
        history = new ManagerHistoryInMemory();
        tasks = new TreeMap<>();
    }

    @Override
    public Map<Integer, Task> getTasks() {
        return Collections.unmodifiableMap(tasks);
    }
    @Override
    public void replacementTasks(Map<Integer, Task> tasks) {
        this.tasks = tasks;
    }
    @Override
    public List<Task> getHistory() {
        return history.getHistory();
    }

    /// /// /// /// ДОБАВЛЕНИЕ
    @Override
    public Task addTask(String name, String description){
        int currentID = CreateID.INSTANCE.createID();
        tasks.put(currentID, new Task(currentID, name, description));
        return tasks.get(currentID);
    }
    @Override
    public Task addEpic(String name, String description){
        int currentID = CreateID.INSTANCE.createID();
        tasks.put(currentID, new EpicTask(currentID, name, description));
        return tasks.get(currentID);
    }
    @Override
    public Task addSubTaskToEpicID(int idEpic, String name, String description) {
        if (!tasks.containsKey(idEpic)){
            throw new NotExistIdException(idEpic);
        }
        if(!isEpic(idEpic)){
            throw new NotEpicException(idEpic);
        }
        int currentID = CreateID.INSTANCE.createID();
        EpicTask epic = (EpicTask)tasks.get(idEpic);

        SubTask subTask = new SubTask(currentID, name, description, epic);
        epic.addSubTask(subTask);
        tasks.put(currentID, subTask);
        return subTask;
    }

/// /// /// /// УДАЛЕНИЕ
    @Override
    public void deleteALL() {
        this.tasks.clear();
        history.removeAll();

    }
    @Override
    public Task deleteIDTask(int idTask) {
        if (!tasks.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        Task task = tasks.get(idTask);
        history.remove(idTask);
        if(task.getTypeTask().equalsIgnoreCase(CONST.EPIC_NAME)){
            EpicTask epic = (EpicTask)task;
            for(SubTask subTask :  epic.getSubTasks()){
                tasks.remove(subTask.getID());
                history.remove(subTask.getID());
            }
            tasks.remove(idTask);
            return task;
        }
        else if(task.getTypeTask().equalsIgnoreCase(CONST.SUB_NAME)){
            SubTask sub = (SubTask)task;
            sub.getRefrains().deleteSubTask(sub);
            tasks.remove(idTask);
            return task;
        }
        else if(task.getTypeTask().equalsIgnoreCase(CONST.TASK_NAME)){
            tasks.remove(idTask);
            return task;
        }
        return null;
    }


    /// /// /// /// Изменение
    @Override
    public Task reNameToIDTask (int idTask, String newName) {
        if (!tasks.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        tasks.get(idTask).setName(newName);
        return tasks.get(idTask);
    }
    @Override
    public Task reDescToIDTask (int idTask, String newDescription) {
        if (!tasks.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        tasks.get(idTask).setDescription(newDescription);
        return tasks.get(idTask);
    }

    @Override
    public boolean reStatus(int idTask, TaskStatus taskStatus) {
        if (!tasks.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        if(isEpic(idTask)){
            throw new NotChangedEpicStatusException(idTask);
        }
        Task task = tasks.get(idTask);
        if (task.getStatus() == taskStatus){
            return false;
        }
        task.setStatus(taskStatus);
        return true;
    }
    @Override
    public boolean isEpic(int idTask){
        if (!tasks.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        return  tasks.get(idTask).getTypeTask().equalsIgnoreCase(CONST.EPIC_NAME);
    }

    @Override
    public Task getTask(int idTask){
        if (!tasks.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        Task task = tasks.get(idTask);
        if(task != null) {
            history.add(task);
        }
        return task;
    }







}


