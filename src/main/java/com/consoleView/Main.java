package com.consoleView;
import com.controller.Managers;
import com.controller.taskManager.TaskManager;
import com.controller.files.FileManager;


public final class Main {
    private Main(){}
    public static void main(String[] args) {
        TaskManager managerTaskInMemory = Managers.getDefault();
        NotificationService notificationService = new NotificationService();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory, notificationService);
        //FileManager.load(managerTaskInMemory);
        consoleView.run();
    }
}