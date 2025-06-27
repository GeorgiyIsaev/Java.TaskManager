package com.consoleViewStrategy.commands.change;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class ReDescription extends CommandBase {
    public ReDescription(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + " 'NUMBER ID'\" – изменить описание задачи с ID";
    }



    @Override
    public void start() {
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
        System.out.println(TaskToString.CONSOLE_TITLE);
        System.out.println(TaskToString.transform(task));
    }
}