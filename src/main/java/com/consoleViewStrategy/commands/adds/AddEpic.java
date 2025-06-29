package com.consoleViewStrategy.commands.adds;

import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.consoleViewStrategy.utils.Notification;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class AddEpic extends CommandBase {

    public AddEpic(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + " 'имя задачи'\" – добавить ЭПИК";
    }

    @Override
    public void start() {
        addEpicTask();
    }
    public void addEpicTask() {
        String textName = consoleUserAction.getCommand();
        consoleUserAction.input("Введите описание Epic: ");
        String textDescription= consoleUserAction.getCommand();
        Task task = taskManager.addEpic(textName,textDescription);
        consoleManager.getNotificationService().addTo(Notification.ADD_EPIC, task);
    }
}
