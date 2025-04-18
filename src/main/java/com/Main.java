package com;
import com.ConsolView.ConsoleView;
import com.Controller.ManagerTaskMap;



public class Main {
    public static void main(String[] args) {
        ManagerTaskMap managerTaskMap = new ManagerTaskMap();

        ConsoleView consoleView = new ConsoleView(managerTaskMap);
        consoleView.run();

    }
}