package com.consoleViewStrategy.commands.adds;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class AddSubTask extends CommandBase {
    public AddSubTask(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + " 'NUMBER ID'\" – добавить подзадачу к Эпику с указанным ID";
    }

    @Override
    public void start() {
        addSubTask();
    }
    public void addSubTask() {
        Integer idEpicTask = consoleUserAction.getID();
        if(idEpicTask == null){
            System.out.println(Notification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idEpicTask)){
            System.out.println(Notification.ID_NOT_EXIST);
            return;
        }
        if (!taskManager.isEpic(idEpicTask)) {
            System.out.println(Notification.NOT_EPIC);
            return;
        }
        consoleUserAction.input("Введите Название SubTask: ");
        String textName = consoleUserAction.getCommand();
        consoleUserAction.input("Введите описание SubTask: ");
        String textDescription = consoleUserAction.getCommand();
        Task task = taskManager.addSubTaskToEpicID(idEpicTask, textName, textDescription);
        consoleManager.getNotificationService().addTo(Notification.ADD_SUBTASK, task);
    }
}
