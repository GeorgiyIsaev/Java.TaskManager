package com.consoleViewStrategy.commands.prints;

import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;
import com.dateTask.TaskType;

import java.util.Map;

public class PrintTask implements ICommand {
    TaskManager taskManager;

    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        printTask(TaskType.TASK.name());
    }

    public void printTask(String typeFilter) {
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        for (Map.Entry<Integer, Task> entry : taskManager.getTasks().entrySet()) {
            Task value = entry.getValue();
            if (typeFilter == null || typeFilter.equalsIgnoreCase(value.getTypeTask())) {
                System.out.println(ConsoleUtils.getTaskString(value));
            }
        }
    }
}