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
        //listTask = new HashMap<>();

       listTask = MemoryTask.ReadTaskList();
    }

    /// /// /// /// ДОБАВЛЕНИЕ
    public void addTask(String nameTask, String discTask){
        int id = CreateID.getNewID();
        listTask.put(id, new Task(id, nameTask,discTask));
    }
    public void addEpic(String nameEpic, String discEpic){
        int id = CreateID.getNewID();
        listTask.put(id, new EpicTask(id, nameEpic,discEpic));
    }
    public void addSubTaskToEpicID(String nameSubTask, String discSubTask, Integer idEpic) throws Exception {
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
    public void reNameToIDTask (Integer idTask, String newName) throws Exception {
        if (!listTask.containsKey(idTask)){
            throw new Exception("ERROR: Задачи с индексом " + idTask + " не существует!" );
        }
        listTask.get(idTask).setName(newName);
    }
    public void reDescToIDTask (Integer idTask, String newDescription) throws Exception {
        if (!listTask.containsKey(idTask)){
            throw new Exception("ERROR: Задачи с индексом " + idTask + " не существует!" );
        }
        listTask.get(idTask).setDescription(newDescription);
    }
    public void saveToFileTXT(){
        MemoryTask.WriteTaskList(listTask);
    }


}
