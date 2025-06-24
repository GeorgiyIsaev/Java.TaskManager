package com.consoleViewStrategy.commands.change;

import com.consoleView.ConsoleNotification;
import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class ReName implements ICommand {
    TaskManager taskManager;
    ConsoleUserAction consoleUserAction;

    @Override
    public void start(Commands commands) {
        taskManager = commands.getTaskManager();
        consoleUserAction = commands.getConsoleUserAction();
        reNameID();
    }

    public void reNameID() {
        Integer idTask = consoleUserAction.getID();
        if (idTask == null) {
            System.out.println(ConsoleNotification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println(ConsoleNotification.ID_NOT_EXIST);
            return;
        }
        consoleUserAction.input("Введите новое имя Задачи: ");
        String textName = consoleUserAction.getCommand();
        Task task = taskManager.reNameToIDTask(idTask, textName);

        System.out.println(ConsoleNotification.RENAME);
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }
}