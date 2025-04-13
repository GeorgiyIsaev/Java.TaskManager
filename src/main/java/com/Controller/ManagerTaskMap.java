package com.Controller;

import com.Controller.Memorys.Memory;
import com.DateTask.*;

import java.util.HashMap;

public class ManagerTaskMap {
    private static Integer currentMaxID = 0;
    HashMap<Integer, Task> listTask;
    Memory memory;


    public HashMap<Integer, Task> getListTask() {
        return listTask;
    }

    public ManagerTaskMap () {
        listTask = new HashMap<>();
        memory = null;
    }
    public ManagerTaskMap (Memory memory) {
        listTask = new HashMap<>();
        this.memory = memory;
        memory.setManagerTaskMap(this);
        load();
    }
    public ManagerTaskMap (int i) throws Exception {
        listTask = MemoryTask.ReadTaskList();
    }

/// /// /// /// ДОБАВЛЕНИЕ
    public Task addTask(String nameTask, String discTask){
        currentMaxID = CreateID.getNewID();
        listTask.put(currentMaxID, new Task(currentMaxID, nameTask,discTask));
        return listTask.get(currentMaxID);
    }
    public Task addEpic(String nameEpic, String discEpic){
        currentMaxID = CreateID.getNewID();
        listTask.put(currentMaxID, new EpicTask(currentMaxID, nameEpic,discEpic));

        return listTask.get(currentMaxID);
    }
    public Task addSubTaskToEpicID(String nameSubTask, String discSubTask, Integer idEpic) throws Exception {
        if (!listTask.containsKey(idEpic)){
            throw new Exception("ERROR: Задачи с индексом " + idEpic + " не существует!" );
        }
        if(!listTask.get(idEpic).getTypeTask().equalsIgnoreCase("EPIC")){
            throw new Exception("ERROR: Задача с ID: " + idEpic + " не является ЭПИКОМ, добавление подзадачи не возможно!;" );
        }
        currentMaxID = CreateID.getNewID();
        SubTask subTask = new SubTask(currentMaxID, nameSubTask,discSubTask, (EpicTask) listTask.get(idEpic));
        ((EpicTask)listTask.get(idEpic)).addSubTask(subTask);
        listTask.put(currentMaxID, subTask);
        return listTask.get(currentMaxID);
    }

/// /// /// /// УДАЛЕНИЕ
    public void deleteALL() {
        this.listTask = new HashMap<>();
    }
    public Task deleteIDTask(Integer idTask) throws Exception {
        if (!listTask.containsKey(idTask)){
            throw new Exception("ERROR: Задачи с индексом " + idTask + " не существует!" );
        }

        Task task = listTask.get(idTask);
        if(listTask.get(idTask).getTypeTask().equalsIgnoreCase("EPIC")){
            for(SubTask subTask :  ((EpicTask)listTask.get(idTask)).getSubTasks()){
                listTask.remove(subTask.getID());
            }
            listTask.remove(idTask);
            return task;
        }
        else if(listTask.get(idTask).getTypeTask().equalsIgnoreCase("SubTASK")){
            ((SubTask)listTask.get(idTask)).getRefrains().deleteSubTask((SubTask)listTask.get(idTask));
            listTask.remove(idTask);
            return task;
        }
        else if(listTask.get(idTask).getTypeTask().equalsIgnoreCase("TASK")){
            listTask.remove(idTask);
            return task;
        }
        return null;
    }

/// /// /// /// Изменение
    public Task reNameToIDTask (Integer idTask, String newName) throws Exception {
        if (!listTask.containsKey(idTask)){
            throw new Exception("ERROR: Задачи с индексом " + idTask + " не существует!" );
        }
        listTask.get(idTask).setName(newName);
        return listTask.get(idTask);
    }
    public Task reDescToIDTask (Integer idTask, String newDescription) throws Exception {
        if (!listTask.containsKey(idTask)){
            throw new Exception("ERROR: Задачи с индексом " + idTask + " не существует!" );
        }
        listTask.get(idTask).setDescription(newDescription);
        return listTask.get(idTask);
    }


    public boolean reStatus(Integer idTask, TaskStatus taskStatus) throws Exception {
        if (!listTask.containsKey(idTask)){
            throw new Exception("ERROR: Задачи с индексом " + idTask + " не существует!" );
        }
        if(isEpic(idTask)){
            throw new Exception("ERROR: Задачи с индексом " + idTask + " EPIC! Ручное изменение статуса не возможно!" );
        }
        if (listTask.get(idTask).getTaskStatus() == taskStatus){
            return false;
        }
        listTask.get(idTask).setTaskStatus(taskStatus);
        return true;
    }

    public boolean isEpic(Integer idTask){
       return  listTask.get(idTask).getTypeTask().equalsIgnoreCase("EPIC");
    }
/// /// /// /// Сохранение и загрузка
    public void load(){
        if(memory == null) return;
        else memory.load();

    }
    public void save(){
        if(memory == null) return;
        else memory.save();
    }
}


