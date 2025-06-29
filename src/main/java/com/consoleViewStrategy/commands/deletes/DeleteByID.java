package com.consoleViewStrategy.commands.deletes;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.dateTask.Task;

public class DeleteByID extends CommandBase {

    public DeleteByID(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + " 'NUMBER ID'\" - удалить задачу с ID";
    }

    @Override
    public void start() {
        deleteID();
    }
    public void deleteID() {
        Integer id = consoleUserAction.getID();
        if(id == null){
            consoleManager.getNotificationService().sendTo(Notification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(id)) {
            consoleManager.getNotificationService().sendTo(Notification.ID_NOT_EXIST);
            return;
        }
        Task task = taskManager.deleteIDTask(id);
        consoleManager.getNotificationService().sendTo(Notification.DELETE_ByID);
        System.out.println(TaskToString.CONSOLE_TITLE);
        System.out.println(TaskToString.transform(task));
    }
}