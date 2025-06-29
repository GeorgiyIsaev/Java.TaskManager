package com.consoleViewStrategy.commands.adds;

import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.consoleViewStrategy.utils.Notification;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class AddTask extends CommandBase {

    public AddTask(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + " 'имя задачи'\" – добавить обычную задачу";
    }

    @Override
    public void start() {
        addTask();
    }
    private void addTask(){
        String textName = consoleUserAction.getCommand();
        consoleUserAction.input("Введите описание Task: ");
        String textDescription= consoleUserAction.getCommand();
        Task task = taskManager.addTask(textName, textDescription);
        consoleManager.getNotificationService().addTo(Notification.ADD_TASK, task);

    }
}
