package com.Controller;

import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.Task;

import java.util.HashMap;

public class ChangeTaskMap {
    public static String deleteID(HashMap<Integer, Task> listTask, Integer idTask){
        String textInfo = "Этого индекса нет в списке";
        if (!listTask.containsKey(idTask))
            return "Этого индекса нет в списке";

        if(listTask.get(idTask).getTypeTask().equalsIgnoreCase("TASK")){
            textInfo = "ЗАДАЧА удалена: " + listTask.get(idTask);
            listTask.remove(idTask);
            return textInfo;
        }
        if(listTask.get(idTask).getTypeTask().equalsIgnoreCase("SubTASK")){
            textInfo = "ПОДЗАДАЧА удалена: " + listTask.get(idTask);
            ((SubTask)listTask.get(idTask)).getRefrains().deleteSubTask((SubTask)listTask.get(idTask));
            listTask.remove(idTask);
            return textInfo;
        }
        if(listTask.get(idTask).getTypeTask().equalsIgnoreCase("EPIC")){
            textInfo = "ЭПИК и его подзадачи удалены: " + listTask.get(idTask);
          for(SubTask subTask :  ((EpicTask)listTask.get(idTask)).getSubTasks()){
              listTask.remove(subTask.getID());
          }
            listTask.remove(idTask);
            return textInfo;
        }

        return "Ошибка!";

    }
    public static String addSubTaskToEpic(HashMap<Integer, Task> listTask, Integer idEPIC,
                                          String nameSubTask, String discSubTask){
        //EpicTask epicTask = (EpicTask) listTask.get(idEPIC);
        if (!listTask.containsKey(idEPIC))
            return "Этого индекса нет в списке";
        if(!listTask.get(idEPIC).getTypeTask().equalsIgnoreCase("EPIC"))
            return "Задача с ID: " + idEPIC + " не является ЭПИКОМ, добавление подзадачи не возможно!";
        SubTask subTask = new SubTask(nameSubTask,discSubTask, (EpicTask) listTask.get(idEPIC));
        ((EpicTask)listTask.get(idEPIC)).addSubTask(subTask);
        listTask.put(subTask.getID(), subTask);
        return "Подзадача добавлена";
    }

}
