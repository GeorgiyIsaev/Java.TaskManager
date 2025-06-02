package com;
import com.consoleView.ConsoleView;
import com.controller.Managers;
import com.controller.IManagerTask;
import com.controller.ManagerFile;


public final class Main {
    private Main(){}
    public static void main(String[] args) {
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ManagerFile.load(managerTaskInMemory);

        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        consoleView.run();
    }
}