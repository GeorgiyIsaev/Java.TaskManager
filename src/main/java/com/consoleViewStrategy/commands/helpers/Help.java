package com.consoleViewStrategy.commands.helpers;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;

public class Help implements ICommand {


    @Override
    public void start(ConsoleManager consoleManager) {
        help();
    }

    public void help() {
        System.out.println(Notification.HELP);
    }
}