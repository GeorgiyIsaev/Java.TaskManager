package com.consoleViewStrategy.commands.prints;

import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;


public class PrintHistory implements ICommand {
    TaskManager taskManager;;

    @Override
    public void start(Commands commands) {
        taskManager = commands.getTaskManager();
        printHistory();
    }

    public void printHistory() {
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        for (Task task : taskManager.getHistory()) {
            System.out.println(ConsoleUtils.getTaskString(task));
        }
    }
}