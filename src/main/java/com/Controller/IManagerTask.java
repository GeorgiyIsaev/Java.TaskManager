package com.Controller;

import com.DateTask.Task;
import com.DateTask.TaskStatus;

import java.util.Map;

public interface IManagerTask {

    public Task addTask(String nameTask, String discTask);
    public Task addEpic(String nameEpic, String discEpic);
    public Task addSubTaskToEpicID(String nameSubTask, String discSubTask, Integer idEpic);

    public void deleteALL();
    public Task deleteIDTask(Integer idTask);

    public Task reNameToIDTask (Integer idTask, String newName);
    public Task reDescToIDTask (Integer idTask, String newDescription);
    public boolean reStatus(Integer idTask, TaskStatus taskStatus);

    public Task getTask(Integer idTask);
    public boolean isEpic(Integer idTask);

    public Map<Integer, Task> getListTask();
    public void setListTask(Map<Integer, Task> listTask);
}
