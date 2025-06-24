package com.consoleViewStrategy.commands.change;

import com.consoleView.ConsoleNotification;
import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class ReDescription implements ICommand {
    TaskManager taskManager;
    ConsoleUserAction consoleUserAction;

    @Override
    public void start(Commands commands) {
        taskManager = commands.getTaskManager();
        consoleUserAction = commands.getConsoleUserAction();
        reDescID();
    }

    public void reDescID() {
        Integer idTask = consoleUserAction.getID();
        if (idTask == null) {
            System.out.println(ConsoleNotification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println(ConsoleNotification.ID_NOT_EXIST);
            return;
        }
        consoleUserAction.input("Введите новое описание Задачи: ");
        String textDescription = consoleUserAction.getCommand();
        Task task = taskManager.reDescToIDTask(idTask, textDescription);
        System.out.println(ConsoleNotification.REDESC);
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }
}