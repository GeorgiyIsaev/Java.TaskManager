package com.consoleViewStrategy.commands.helpers;

import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;

public class Exit implements ICommand {
    @Override
    public void start(ConsoleManager consoleManager) {
        consoleManager.setExit(false);
        System.out.println(Notification.EXIT);
    }
}