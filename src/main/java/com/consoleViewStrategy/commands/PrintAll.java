package com.consoleViewStrategy.commands;

import com.consoleView.ConsoleUtils;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;
import com.dateTask.TaskType;

import java.util.Map;

public class PrintAll implements ICommand {
    TaskManager taskManager;
    Integer id;

    @Override
    public void start(Commands commands) {
        taskManager = commands.getTaskManager();
        printAll();
    }

    public void printAll() {
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        for (Map.Entry<Integer, Task> entry : taskManager.getTasks().entrySet()) {
            Task value = entry.getValue();
            System.out.println(ConsoleUtils.getTaskString(value));
        }
    }
}