package com.consoleViewStrategy.commands.helpers;

import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;

public class Exit implements ICommand {
    private final String name;
    public Exit(String name){
        this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + "\" – завершить программу";
    }
    public String getName() {
        return name;
    }

    @Override
    public void start(ConsoleManager consoleManager) {
        consoleManager.setExit(false);
        System.out.println(Notification.EXIT);
    }
}