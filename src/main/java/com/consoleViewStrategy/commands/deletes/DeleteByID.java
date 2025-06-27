package com.consoleViewStrategy.commands.deletes;

import com.consoleViewStrategy.utils.Notification;
import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class DeleteByID implements ICommand {
    TaskManager taskManager;
    ConsoleUserAction consoleUserAction;

    @Override
    public void start(Commands commands) {
        taskManager = commands.getTaskManager();
        consoleUserAction = commands.getConsoleUserAction();
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
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }
}