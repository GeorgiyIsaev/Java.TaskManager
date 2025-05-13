package com.controller;

import com.dateTask.Task;
import com.dateTask.TaskStatus;

import java.util.List;
import java.util.Map;

public interface IManagerTask {

    public Task addTask(String name, String description);
    public Task addEpic(String name, String description);
    public Task addSubTaskToEpicID(int idEpic, String name, String description);

    public void deleteALL();
    public Task deleteIDTask(int idTask);

    public Task reNameToIDTask (int idTask, String newName);
    public Task reDescToIDTask (int idTask, String newDescription);
    public boolean reStatus(int idTask, TaskStatus taskStatus);

    public Task getTask(int idTask);
    public boolean isEpic(int idTask);

    public Map<Integer, Task> getTaskMap();
    public void setTaskMap(Map<Integer, Task> taskMap);
    public List<Task> getHistory();



}
