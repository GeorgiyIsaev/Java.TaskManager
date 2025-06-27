package com.consoleViewStrategy;

import com.controller.Managers;
import com.controller.files.CreatePath;
import com.controller.taskManager.TaskManager;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path filePath = CreatePath.of().generateToPakResources("csv.csv");
        TaskManager taskManager = Managers.getFileBacked(filePath);
        ConsoleManager consoleManager = new ConsoleManager(taskManager);
        consoleManager.run();
    }
}
