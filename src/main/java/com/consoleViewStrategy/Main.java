package com.consoleViewStrategy;

import com.consoleViewStrategy.utils.ConsoleService;
import com.consoleViewStrategy.utils.NotificationService;
import com.controller.Managers;
import com.controller.files.CreatePath;
import com.controller.taskManager.TaskManager;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path filePath = CreatePath.of().generatePathToPakDateAndSave("csv.csv");
        TaskManager taskManager = Managers.getFileBacked(filePath);

        NotificationService notificationService = new ConsoleService();
        ConsoleManager consoleManager = new ConsoleManager(taskManager, notificationService);

        consoleManager.run();
    }
}
