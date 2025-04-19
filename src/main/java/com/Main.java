package com;
import com.ConsolView.ConsoleView;
import com.Controller.ManagerFile;
import com.Controller.ManagerTask;



public class Main {
    public static void main(String[] args) {
        ManagerTask managerTask = new ManagerTask();
        ManagerFile.load(managerTask);

        ConsoleView consoleView = new ConsoleView(managerTask);
        consoleView.run();
    }
}