package com;


import com.Controller.ManagerTaskMap;
import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.Task;
import com.Controller.MemoryTask;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyTest {
    public static void main(String[] args) {
        ManagerTaskMap managerTaskMap = new ManagerTaskMap();
        managerTaskMap.addTask("Первая задач", "Описание простой задачи");
        managerTaskMap.addTask("Вторая задач", "Другое описание простой задачи");

        System.out.println("Демонстрация");
        printList(managerTaskMap.getListTask());




    }



    public static void printList(HashMap<Integer, Task> listTask){
        String myFormat = "%-4s %-4s %-8s %-12s %-12s %-25s";
        System.out.println(String.format(myFormat,"KEY","ID","TYPE","STATUS","LINK","INFORMATION"));

        Iterator<Map.Entry<Integer, Task>> iterator = listTask.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();
            //System.out.println("Key: " + key + ", Value: " + value);
            System.out.println(String.format(myFormat,
                    key,value.getID(),value.getTypeTask(),value.getTaskStatus(),value.getLinkStr(),value));

        }
    }
}
