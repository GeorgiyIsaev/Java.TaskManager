package com.consoleViewStrategy;

import com.consoleViewStrategy.commands.Commands;
import com.controller.Managers;
import com.controller.taskManager.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getFileBacked("TasksCSV.csv");
        Commands commands = new Commands(taskManager);
        commands.run();
    }
}
