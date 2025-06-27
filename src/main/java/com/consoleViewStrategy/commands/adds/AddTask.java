package com.consoleViewStrategy.commands.adds;

import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

public class AddTask implements ICommand {
    private TaskManager taskManager;
    private ConsoleUserAction consoleUserAction;

    private final String name;

    public AddTask(String name){
        this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + " 'имя задачи'\" – добавить обычную задачу";
    }

    public String getName() {
        return name;
    }
    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        consoleUserAction = consoleManager.getConsoleUserAction();
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
