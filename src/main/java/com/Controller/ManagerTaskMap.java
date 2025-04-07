package com.Controller;

import com.DateTask.CreateID;
import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.Task;

import java.util.HashMap;

public class ManagerTaskMap {
    HashMap<Integer, Task> listTask;
    public HashMap<Integer, Task> getListTask() {
        return listTask;
    }

    public ManagerTaskMap (){
        listTask = new HashMap<>();

       // listTask = MemoryTask.ReadTaskList();
    }
    public void addTask(String nameTask, String discTask){
        int id = CreateID.getNewID();
        listTask.put(id, new Task(id, nameTask,discTask));
    }




    public static String addSubTaskToEpic(HashMap<Integer, Task> listTask, Integer idEPIC,
                                          String nameSubTask, String discSubTask){
        if (!listTask.containsKey(idEPIC))
            return "Этого индекса нет в списке";
        if(!listTask.get(idEPIC).getTypeTask().equalsIgnoreCase("EPIC"))
            return "Задача с ID: " + idEPIC + " не является ЭПИКОМ, добавление подзадачи не возможно!";
        SubTask subTask = new SubTask(0, nameSubTask,discSubTask, (EpicTask) listTask.get(idEPIC));
       // ((EpicTask)listTask.get(idEPIC)).addSubTask(subTask);
        listTask.put(subTask.getID(), subTask);
        return "Подзадача добавлена";
    }




}
