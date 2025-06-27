package com.consoleViewStrategy.commands.deletes;

import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;

public class DeleteAll implements ICommand {
    private TaskManager taskManager;

    private final String name;
    public DeleteAll(String name){
        this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + "\" – удалить все задачи";
    }
    public String getName() {
        return name;
    }

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
