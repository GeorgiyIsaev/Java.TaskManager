package com;


import com.ConsolView.ConsoleView;

import com.Controller.ManagerTaskMap;
import com.DateTask.Task;
import com.Controller.MemoryTask;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        ManagerTaskMap managerTaskMap = null;
        try {
            managerTaskMap = new ManagerTaskMap();
            ConsoleView consoleView = new ConsoleView(managerTaskMap);
            consoleView.run();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }



       // ConsoleViewOld.run(listTask);
    }
}