package com.consoleViewStrategy.commands.prints;

import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

import java.util.Map;

public class PrintAll implements ICommand {
    TaskManager taskManager;

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