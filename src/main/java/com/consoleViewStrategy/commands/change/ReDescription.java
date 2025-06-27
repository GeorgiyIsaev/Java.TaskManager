package com.consoleViewStrategy.commands.change;

import com.consoleViewStrategy.utils.Notification;
import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class ReDescription implements ICommand {
    TaskManager taskManager;
    ConsoleUserAction consoleUserAction;

    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        consoleUserAction = consoleManager.getConsoleUserAction();
        reDescID();
    }

    public void reDescID() {
        Integer idTask = consoleUserAction.getID();
        if (idTask == null) {
            System.out.println(Notification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println(Notification.ID_NOT_EXIST);
            return;
        }
        consoleUserAction.input("Введите новое описание Задачи: ");
        String textDescription = consoleUserAction.getCommand();
        Task task = taskManager.reDescToIDTask(idTask, textDescription);
        System.out.println(Notification.REDESC);
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }
}