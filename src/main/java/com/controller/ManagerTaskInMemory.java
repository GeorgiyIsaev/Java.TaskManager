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
    private Map<Integer, Task> taskMap;
    private IHistoryManager historyManager;
    public ManagerTaskInMemory() {
        historyManager = new ManagerHistoryInMemory();
        taskMap = new TreeMap<>();
    }

    @Override
    public Map<Integer, Task> getTaskMap() {
        Map<Integer, Task> noChangeTaskMap =   Collections.unmodifiableMap(           taskMap);

        return noChangeTaskMap;
    }
    @Override
    public void setTaskMap(Map<Integer, Task> taskMap) {
        this.taskMap = taskMap;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    /// /// /// /// ДОБАВЛЕНИЕ
    @Override
    public Task addTask(String name, String description){
        int currentID = CreateID.INSTANCE.createID();
        taskMap.put(currentID, new Task(currentID, name, description));
        return taskMap.get(currentID);
    }
    @Override
    public Task addEpic(String name, String description){
        int currentID = CreateID.INSTANCE.createID();
        taskMap.put(currentID, new EpicTask(currentID, name, description));
        return taskMap.get(currentID);
    }
    @Override
    public Task addSubTaskToEpicID(int idEpic, String name, String description) {
        if (!taskMap.containsKey(idEpic)){
            throw new NotExistIdException(idEpic);
        }
        if(!isEpic(idEpic)){
            throw new NotEpicException(idEpic);
        }
        int currentID = CreateID.INSTANCE.createID();
        SubTask subTask = new SubTask(currentID, name, description, (EpicTask) taskMap.get(idEpic));
        ((EpicTask) taskMap.get(idEpic)).addSubTask(subTask);
        taskMap.put(currentID, subTask);
        return taskMap.get(currentID);
    }

/// /// /// /// УДАЛЕНИЕ
    @Override
    public void deleteALL() {
        this.taskMap.clear();
    }
    @Override
    public Task deleteIDTask(Integer idTask) {
        if (!taskMap.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        Task task = taskMap.get(idTask);
        if(taskMap.get(idTask).getTypeTask().equalsIgnoreCase(CONST.EPIC_NAME)){
            for(SubTask subTask :  ((EpicTask) taskMap.get(idTask)).getSubTasks()){
                taskMap.remove(subTask.getID());
            }
            taskMap.remove(idTask);
            return task;
        }
        else if(taskMap.get(idTask).getTypeTask().equalsIgnoreCase(CONST.SUB_NAME)){
            ((SubTask) taskMap.get(idTask)).getRefrains().deleteSubTask((SubTask) taskMap.get(idTask));
            taskMap.remove(idTask);
            return task;
        }
        else if(taskMap.get(idTask).getTypeTask().equalsIgnoreCase(CONST.TASK_NAME)){
            taskMap.remove(idTask);
            return task;
        }
        return null;
    }

/// /// /// /// Изменение
    @Override
    public Task reNameToIDTask (Integer idTask, String newName) {
        if (!taskMap.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        taskMap.get(idTask).setName(newName);
        return taskMap.get(idTask);
    }
    @Override
    public Task reDescToIDTask (Integer idTask, String newDescription) {
        if (!taskMap.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        taskMap.get(idTask).setDescription(newDescription);
        return taskMap.get(idTask);
    }

    @Override
    public boolean reStatus(Integer idTask, TaskStatus taskStatus) {
        if (!taskMap.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        if(isEpic(idTask)){
            throw new NotChangedEpicStatusException(idTask);
        }
        if (taskMap.get(idTask).getTaskStatus() == taskStatus){
            return false;
        }
        taskMap.get(idTask).setTaskStatus(taskStatus);
        return true;
    }
    @Override
    public boolean isEpic(Integer idTask){
        return  taskMap.get(idTask).getTypeTask().equalsIgnoreCase(CONST.EPIC_NAME);
    }

    @Override
    public Task getTask(Integer idTask){
        if (!taskMap.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        Task task = taskMap.get(idTask);
        if(task != null) {
            historyManager.add(task);
        }
        return task;
    }







}


