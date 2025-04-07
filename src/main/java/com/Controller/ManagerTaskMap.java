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
    public void addEpic(String nameEpic, String discEpic){
        int id = CreateID.getNewID();
        listTask.put(id, new EpicTask(id, nameEpic,discEpic));
    }
    public void addSubTaskToEpic(String nameSubTask, String discSubTask, Integer idEpic) throws Exception {
        if (!listTask.containsKey(idEpic)){
            throw new Exception("ERROR: Задачи с индексом " + idEpic + " не существует!" );
        }
        if(!listTask.get(idEpic).getTypeTask().equalsIgnoreCase("EPIC")){
            throw new Exception("ERROR: Задача с ID: " + idEpic + " не является ЭПИКОМ, добавление подзадачи не возможно!;" );
        }
        int id = CreateID.getNewID();
        SubTask subTask = new SubTask(id, nameSubTask,discSubTask, (EpicTask) listTask.get(idEpic));
        ((EpicTask)listTask.get(idEpic)).addSubTask(subTask);
        listTask.put(id, subTask);
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
