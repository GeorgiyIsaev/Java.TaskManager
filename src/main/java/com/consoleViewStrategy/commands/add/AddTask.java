package com.consoleViewStrategy.commands.add;

import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class AddTask implements ICommand {
    TaskManager taskManager;
    ConsoleUserAction consoleUserAction;

    @Override
    public void start(Commands commands) {
        taskManager = commands.getTaskManager();
        consoleUserAction = commands.getConsoleUserAction();
        addTask();
    }
    private void addTask(){
        String textName = consoleUserAction.getCommand();
        consoleUserAction.input("Введите описание Task: ");
        String textDescription= consoleUserAction.getCommand();
        Task task = taskManager.addTask(textName, textDescription);
        System.out.println("Add Task (id = " + task.getID() + "): " + task);
    }
}
