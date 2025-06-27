package com.consoleViewStrategy.commands.deletes;

import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class DeleteByID implements ICommand {
    private TaskManager taskManager;
    private ConsoleUserAction consoleUserAction;

    private final String name;
    public DeleteByID(String name){
        this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + " 'NUMBER ID'\" - удалить задачу с ID";
    }
    public String getName() {
        return name;
    }


    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        consoleUserAction = consoleManager.getConsoleUserAction();
        deleteID();
    }
    public void deleteID() {
        Integer id = consoleUserAction.getID();
        if(id == null){
            System.out.println(Notification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(id)) {
            System.out.println(Notification.ID_NOT_EXIST);
            return;
        }
        Task task = taskManager.deleteIDTask(id);
        System.out.println(Notification.DELETE_TASK);
        System.out.println(TaskToString.CONSOLE_TITLE);
        System.out.println(TaskToString.transform(task));
    }
}