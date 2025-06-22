package com.controller.taskManager;

import com.controller.controlException.NotChangedEpicStatusException;
import com.controller.controlException.NotEpicException;
import com.controller.controlException.NotExistIdException;
import com.controller.history.HistoryManager;
import com.controller.history.InMemoryHistoryManager;
import com.dateTask.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InMemoryTaskManager implements Serializable, TaskManager {
    private Map<Integer, Task> tasks;
    private HistoryManager history;
    public InMemoryTaskManager() {
        history = new InMemoryHistoryManager();
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
        Task task = new Task(name, description);
        tasks.put(task.getID(), task);
        return tasks.get(task.getID());
    }
    @Override
    public Task addEpic(String name, String description){
        Task task = new EpicTask(name, description);
        tasks.put(task.getID(), task);
        return tasks.get(task.getID());
    }
    @Override
    public Task addSubTaskToEpicID(int idEpic, String name, String description) {
        if (!tasks.containsKey(idEpic)){
            throw new NotExistIdException(idEpic);
        }
        if(!isEpic(idEpic)){
            throw new NotEpicException(idEpic);
        }
        EpicTask epic = (EpicTask)tasks.get(idEpic);
        SubTask subTask = new SubTask(name, description, epic);
        epic.addSubTask(subTask);
        tasks.put(subTask.getID(), subTask);
        return subTask;
    }

    /// /// /// /// ДОБАВЛЕНИЕ с ID
    @Override
    public Task addTaskByID(int id, String name, String description){
        Task task = new Task(id, name, description);
        tasks.put(task.getID(), task);
        return tasks.get(task.getID());
    }
    @Override
    public Task addEpicByID(int id, String name, String description){
        Task task = new EpicTask(id, name, description);
        tasks.put(task.getID(), task);
        return tasks.get(task.getID());
    }
    @Override
    public Task addSubTaskToEpicIDByID(int idSub, int idEpicAdded, String name, String description) {
        if (!tasks.containsKey(idEpicAdded)){
            throw new NotExistIdException(idEpicAdded);
        }
        if(!isEpic(idEpicAdded)){
            throw new NotEpicException(idEpicAdded);
        }
        EpicTask epic = (EpicTask)tasks.get(idEpicAdded);
        SubTask subTask = new SubTask(idSub, name, description, epic);
        epic.addSubTask(subTask);
        tasks.put(subTask.getID(), subTask);
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

        if(task.getTypeTask().equals(TaskType.EPIC.name())){
            EpicTask epic = (EpicTask)task;
            for(SubTask subTask :  epic.getSubTasks()){
                tasks.remove(subTask.getID());
                history.remove(subTask.getID());
            }
        }
        else if(task.getTypeTask().equals(TaskType.SUBTASK.name())){
            SubTask sub = (SubTask)task;
            sub.getRefrains().deleteSubTask(sub);
        }

        history.remove(idTask);
        tasks.remove(idTask);
        return task;

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
        return  tasks.get(idTask).getTypeTask().equalsIgnoreCase(TaskType.EPIC.name());
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


