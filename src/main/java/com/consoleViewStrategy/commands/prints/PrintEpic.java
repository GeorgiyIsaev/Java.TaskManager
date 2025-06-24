package com.consoleViewStrategy.commands.prints;

import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;
import com.dateTask.TaskType;

import java.util.Map;

public class PrintEpic implements ICommand {
    TaskManager taskManager;

    @Override
    public void start(Commands commands) {
        taskManager = commands.getTaskManager();
        printTask(TaskType.EPIC.name());
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
