package com;


import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.Task;
import com.Controller.MemoryTask;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyTest {
    public static void main(String[] args) {
        HashMap<Integer, Task> listTask = new HashMap<>();
        Task task1 =  new Task("Простая задач 1", "Описание 1");
        Task task2 =  new Task("Простая задач 2", "Описание 2");

        listTask.put(task1.getID(),task1);
        listTask.put(task2.getID(),task2);

        EpicTask epic =  new EpicTask("Сложная задач 2", "Сложное описание");
        SubTask sub1 = new SubTask("СабЗадача 1", "дискрипт1", epic);
        SubTask sub2 = new SubTask("СабЗадача 2", "дискрипт2", epic);

        listTask.put(sub1.getID(),sub1);
        listTask.put(sub2.getID(),sub2);
        epic.addSubTask(sub1);
        epic.addSubTask(sub2);

        listTask.put(epic.getID(),epic);
        listTask.get(3).statusUp();

        System.out.println("Старый список");
        printList(listTask);
        listTask.get(3).statusUp();
        ((SubTask) listTask.get(listTask.size()-1)).setName("Изменено");
        System.out.println("Новый список");
        printList(listTask);

        MemoryTask.WriteTaskList(listTask);

        listTask =  MemoryTask.ReadTaskList();

        System.out.println("Загруженный список");
        printList(listTask);



        ((SubTask) listTask.get(listTask.size()-1)).setName("Изменено после загрузки");
        printList(listTask);



        System.out.println("ЕПИК");
        System.out.println( ((EpicTask)listTask.get(2)).fullInfo());



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
                    key,value.getID(),value.getTypeTask(),value.getTaskStatus(),value.getLinkStr(),value.getName()));

        }
    }
}
