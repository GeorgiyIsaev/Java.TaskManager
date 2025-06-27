package com.consoleViewStrategy.commands.deletes;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;

public class DeleteAll extends CommandBase {

    public DeleteAll(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + "\" – удалить все задачи";
    }

    @Override
    public void start() {
        deleteAll();
    }
    public void deleteAll(){
        taskManager.deleteALL();
        System.out.println(Notification.DELETE_ALL);
    }
}
