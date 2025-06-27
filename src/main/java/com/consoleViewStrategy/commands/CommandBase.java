package com.consoleViewStrategy.commands;

import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;

public abstract class CommandBase implements ICommand{
    protected final String name;
    protected final TaskManager taskManager;
    protected final ConsoleUserAction consoleUserAction;
    protected final ConsoleManager consoleManager;

    public CommandBase(String name, ConsoleManager consoleManager){
        this.name = name;
        this.consoleManager = consoleManager;
        this.taskManager = consoleManager.getTaskManager();
        this.consoleUserAction = consoleManager.getConsoleUserAction();
    }
    @Override
    public String getName() {
        return name;
    }
}
