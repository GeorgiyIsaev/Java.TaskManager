package com.consoleViewStrategy.commands.deletes;

import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;

public class DeleteAll implements ICommand {
    TaskManager taskManager;

    @Override
    public void start(Commands commands) {
        taskManager = commands.getTaskManager();
        deleteAll();
    }
    public void deleteAll(){
        taskManager.deleteALL();
        System.out.println(Notification.DELETE_ALL);
    }
}
