package com;


import com.ConsolView.ConsoleView;

import com.Controller.ManagerTaskMap;
import com.DateTask.Task;
import com.Controller.MemoryTask;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
     //   HashMap<Integer,Task> listTask = MemoryTask.ReadTaskList();

       // System.out.println("Добро пожаловать в TaskManager!");
       // System.out.println("У вас в работе "+ listTask.size() +" задач.");
       // System.out.println("Введите help что бы отобразить доступные команды.");


        ManagerTaskMap managerTaskMap = new ManagerTaskMap();
        ConsoleView consoleView = new ConsoleView(managerTaskMap);
        consoleView.run();

       // ConsoleViewOld.run(listTask);
    }
}