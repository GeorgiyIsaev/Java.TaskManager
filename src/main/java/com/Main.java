package com;
import com.ConsolView.ConsoleView;
import com.Controller.ManagerTaskMap;
import com.Controller.MemoryTaskMapToTXT;


public class Main {
    public static void main(String[] args) {
        ManagerTaskMap managerTaskMap = new ManagerTaskMap();

        try {
            managerTaskMap = MemoryTaskMapToTXT.readTasks();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ConsoleView consoleView = new ConsoleView(managerTaskMap);
        consoleView.run();

    }
}