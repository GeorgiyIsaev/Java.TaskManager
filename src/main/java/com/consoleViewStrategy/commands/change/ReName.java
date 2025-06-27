package com.consoleViewStrategy.commands.change;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class ReName extends CommandBase {

    public ReName(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + " 'NUMBER ID'\" – изменить имя задачи с ID";
    }


    @Override
    public void start() {
        reNameID();
    }

    public void reNameID() {
        Integer idTask = consoleUserAction.getID();
        if (idTask == null) {
            consoleManager.getNotificationService().sendTo(Notification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            consoleManager.getNotificationService().sendTo(Notification.ID_NOT_EXIST);
            return;
        }
        consoleUserAction.input("Введите новое имя Задачи: ");
        String textName = consoleUserAction.getCommand();
        Task task = taskManager.reNameToIDTask(idTask, textName);

        consoleManager.getNotificationService().sendTo(Notification.RENAME);
        System.out.println(TaskToString.CONSOLE_TITLE);
        System.out.println(TaskToString.transform(task));
    }
}