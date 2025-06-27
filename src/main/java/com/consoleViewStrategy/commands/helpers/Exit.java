package com.consoleViewStrategy.commands.helpers;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;

public class Exit extends CommandBase {
    public Exit(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + "\" – завершить программу";
    }

    @Override
    public void start( ) {
        consoleManager.setExit(false);
        System.out.println(Notification.EXIT);
    }
}