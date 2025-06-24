package com.consoleViewStrategy.commands.helpers;

import com.consoleView.ConsoleNotification;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleUserAction;

public class Exit implements ICommand {
    @Override
    public void start(Commands commands) {
        commands.setExit(false);
        System.out.println(ConsoleNotification.EXIT);
    }
}