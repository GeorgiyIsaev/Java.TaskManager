package com.Controller;

import com.DateTask.*;

import java.util.HashMap;

public class ManagerTaskMap {
    HashMap<Integer, Task> listTask;
    public HashMap<Integer, Task> getListTask() {
        return listTask;
    }

    public ManagerTaskMap () throws Exception {
       // listTask = new HashMap<>();

      listTask = MemoryTask.ReadTaskList();
    }

    /// /// /// /// ДОБАВЛЕНИЕ
    public Task addTask(String nameTask, String discTask){
        int id = CreateID.getNewID();
        listTask.put(id, new Task(id, nameTask,discTask));
        return listTask.get(id);
    }
    public Task addEpic(String nameEpic, String discEpic){
        int id = CreateID.getNewID();
        listTask.put(id, new EpicTask(id, nameEpic,discEpic));
        return listTask.get(id);
    }
    public Task addSubTaskToEpicID(String nameSubTask, String discSubTask, Integer idEpic) throws Exception {
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
        return listTask.get(id);
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
    ///
    /// @return
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
//    public boolean isExistID(Integer idTask){
//
//    }

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




    public void saveToFileTXT() throws Exception {
        MemoryTask.WriteTaskList(listTask);
    }


}
