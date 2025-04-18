package com;
import com.ConsolView.ConsoleView;
import com.Controller.ManagerTask;



public class Main {
    public static void main(String[] args) {
        ManagerTask managerTask = new ManagerTask();

        ConsoleView consoleView = new ConsoleView(managerTask);
        consoleView.run();

    }
}