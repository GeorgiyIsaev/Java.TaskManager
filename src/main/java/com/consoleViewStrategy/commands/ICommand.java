package com.consoleViewStrategy.commands;

import com.consoleViewStrategy.ConsoleManager;

public interface ICommand {

    public String getName();
    public String description();
    public void start(ConsoleManager consoleManager);
}
