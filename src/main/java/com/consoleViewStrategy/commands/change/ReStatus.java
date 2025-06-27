package com.consoleViewStrategy.commands.change;

import com.consoleViewStrategy.utils.Notification;
import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;
import com.dateTask.TaskStatus;

public class ReStatus implements ICommand {
    TaskManager taskManager;
    ConsoleUserAction consoleUserAction;

    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        consoleUserAction = consoleManager.getConsoleUserAction();
        reStatus();
    }

    public void reStatus() {
        Integer idTask = consoleUserAction.getID();
        if (idTask == null) {
            System.out.println(Notification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println(Notification.ID_NOT_EXIST);
            return;
        }
        Task task = taskManager.getTasks().get(idTask);
        if (taskManager.isEpic(idTask)) {
            System.out.println(Notification.NOT_CHANGE_STATUS);
            return;
        }

        String status = consoleUserAction.getCommand().toLowerCase();
        switch (status) {
            case "new" -> {
                taskManager.reStatus(idTask, TaskStatus.NEW);
                System.out.println(Notification.RESTATUS);
            }
            case "prog" -> {
                taskManager.reStatus(idTask, TaskStatus.IN_PROGRESS);
                System.out.println(Notification.RESTATUS);
            }
            case "done" -> {
                taskManager.reStatus(idTask, TaskStatus.DONE);
                System.out.println(Notification.RESTATUS);
            }
            default -> {
                System.out.println("[" + status + "] " + Notification.STATUS_INCORRECTLY);
            }
        }
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }
}