package com.consoleViewStrategy.commands.deletes;

import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;

public class DeleteAll implements ICommand {
    TaskManager taskManager;

    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        deleteAll();
    }
    public void deleteAll(){
        taskManager.deleteALL();
        System.out.println(Notification.DELETE_ALL);
    }
}
