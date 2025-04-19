package com.Controller;

import com.Controller.ControlException.NotChangedEpicStatusException;
import com.Controller.ControlException.NotEpicException;
import com.Controller.ControlException.NotExistIdException;
import com.DateTask.*;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class ManagerTask implements Serializable {
    private Map<Integer, Task> listTask;

    public Map<Integer, Task> getListTask() {
        return listTask;
    }

    public void setListTask(Map<Integer, Task> listTask) {
        this.listTask = listTask;
    }

    public ManagerTask() {
        listTask = new TreeMap<>();
    }

/// /// /// /// ДОБАВЛЕНИЕ
    public Task addTask(String nameTask, String discTask){
        int currentID = CreateID.INSTANCE.createID();
        listTask.put(currentID, new Task(currentID, nameTask,discTask));
        return listTask.get(currentID);
    }
    public Task addEpic(String nameEpic, String discEpic){
        int currentID = CreateID.INSTANCE.createID();
        listTask.put(currentID, new EpicTask(currentID, nameEpic,discEpic));
        return listTask.get(currentID);
    }
    public Task addSubTaskToEpicID(String nameSubTask, String discSubTask, Integer idEpic) {
        if (!listTask.containsKey(idEpic)){
            throw new NotExistIdException(idEpic);
        }
        if(!isEpic(idEpic)){
            throw new NotEpicException(idEpic);
        }
        int currentID = CreateID.INSTANCE.createID();
        SubTask subTask = new SubTask(currentID, nameSubTask,discSubTask, (EpicTask) listTask.get(idEpic));
        ((EpicTask)listTask.get(idEpic)).addSubTask(subTask);
        listTask.put(currentID, subTask);
        return listTask.get(currentID);
    }

/// /// /// /// УДАЛЕНИЕ
    public void deleteALL() {
        this.listTask.clear();
    }

    public Task deleteIDTask(Integer idTask) {
        if (!listTask.containsKey(idTask)){
            throw new NotExistIdException(idTask);
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
    public Task reNameToIDTask (Integer idTask, String newName) {
        if (!listTask.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        listTask.get(idTask).setName(newName);
        return listTask.get(idTask);
    }
    public Task reDescToIDTask (Integer idTask, String newDescription) {
        if (!listTask.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        listTask.get(idTask).setDescription(newDescription);
        return listTask.get(idTask);
    }


    public boolean reStatus(Integer idTask, TaskStatus taskStatus) {
        if (!listTask.containsKey(idTask)){
            throw new NotExistIdException(idTask);
        }
        if(isEpic(idTask)){
            throw new NotChangedEpicStatusException(idTask);
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
}


