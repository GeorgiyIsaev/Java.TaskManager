package com.consoleViewStrategy.commands.helpers;

import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;

public class Exit implements ICommand {
    @Override
    public void start(Commands commands) {
        commands.setExit(false);
        System.out.println(Notification.EXIT);
    }
}