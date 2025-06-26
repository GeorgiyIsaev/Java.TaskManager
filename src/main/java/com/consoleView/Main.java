package com.consoleView;
import com.controller.Managers;
import com.controller.taskManager.TaskManager;
import com.controller.files.FileManager;


public final class Main {
    private Main(){}
    public static void main(String[] args) {
        TaskManager managerTaskInMemory = Managers.getDefault();
        FileManager.load(managerTaskInMemory);

        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        consoleView.run();
    }
}