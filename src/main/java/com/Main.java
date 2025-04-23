package com;
import com.ConsolView.ConsoleView;
import com.Controller.ManagerFile;
import com.Controller.ManagerTaskInMemory;



public class Main {
    public static void main(String[] args) {
        ManagerTaskInMemory managerTaskInMemory = new ManagerTaskInMemory();
        ManagerFile.load(managerTaskInMemory);

        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        consoleView.run();
    }
}