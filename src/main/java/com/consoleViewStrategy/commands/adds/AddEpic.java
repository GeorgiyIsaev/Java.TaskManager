package com.consoleViewStrategy.commands.adds;

import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class AddEpic implements ICommand {
    private TaskManager taskManager;
    private ConsoleUserAction consoleUserAction;

    private final String name;
    public AddEpic(String name){
       this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + " 'имя задачи'\" – добавить ЭПИК";
    }
    public String getName() {
        return name;
    }

    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        consoleUserAction = consoleManager.getConsoleUserAction();
        addEpicTask();
    }
    public void addEpicTask() {
        String textName = consoleUserAction.getCommand();
        consoleUserAction.input("Введите описание Epic: ");
        String textDescription= consoleUserAction.getCommand();
        Task task = taskManager.addEpic(textName,textDescription);
        System.out.println("Add EpicTask (id = " + task.getID() + "): " + task);
    }
}
