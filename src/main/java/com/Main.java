package com;
import com.ConsolView.ConsoleView;
import com.Controller.ControlException.Managers;
import com.Controller.IManagerTask;
import com.Controller.ManagerFile;
import com.Controller.ManagerTaskInMemory;



public class Main {
    public static void main(String[] args) {
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ManagerFile.load(managerTaskInMemory);

        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        consoleView.run();
    }
}