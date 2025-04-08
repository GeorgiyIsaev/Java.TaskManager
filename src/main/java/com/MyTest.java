package com;


import com.ConsolView.ConsoleView;
import com.Controller.ManagerTaskMap;
import com.DateTask.Task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyTest {
    public static void main(String[] args) throws Exception {
        ManagerTaskMap managerTaskMap = new ManagerTaskMap();
        managerTaskMap.addTask("Первая задач", "Описание простой задачи");
        managerTaskMap.addTask("Вторая задач", "Другое описание простой задачи");

        System.out.println("Демонстрация");
        printList(managerTaskMap.getListTask());

        try {
            managerTaskMap.addEpic("Эпичная задача", "Очень эпичное описание");
            managerTaskMap.addEpic("Мини Эпик", "Короткое описание");
            managerTaskMap.addSubTaskToEpicID("Подзадача 01", "Для тестирования 01", (Integer) 2);
            managerTaskMap.addSubTaskToEpicID("Подзадача 02", "Для тестирования 02", 2);
            managerTaskMap.addSubTaskToEpicID("Подзадача 03", "Для тестирования 03", 2);
            managerTaskMap.addSubTaskToEpicID("Один пункт", "Ничего важного", 3);

            System.out.println("Демонстрация 2");
            printList(managerTaskMap.getListTask());

            System.out.println("Отдельно");
            System.out.println(managerTaskMap.getListTask().get(2).fullInfo());
            System.out.println(managerTaskMap.getListTask().get(3).fullInfo());

            managerTaskMap.deleteIDTask(6);
            System.out.println("Демонстрация удаления саба");
            printList(managerTaskMap.getListTask());
            System.out.println(managerTaskMap.getListTask().get(2).fullInfo());

            managerTaskMap.reNameToIDTask(5, "Крутое название");
            managerTaskMap.reDescToIDTask(3, "Крутое описание");
            System.out.println("Демонстрация изменений");
            printList(managerTaskMap.getListTask());

           // Task task = managerTaskMap.deleteIDTask(3);
           // System.out.println("удаления епик");
            //System.out.println(task);
            ConsoleView consoleView = new ConsoleView(managerTaskMap);
            System.out.println("ID 2");
            consoleView.printID("2");
            consoleView.printDebug();
            consoleView.printTaskMap();
            consoleView.printTaskMap("EPIC");

           // System.out.println("Демонстрация удаления епика");
           ///printList(managerTaskMap.getListTask());
             managerTaskMap.saveToFileTXT();



        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


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
