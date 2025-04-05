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
}
