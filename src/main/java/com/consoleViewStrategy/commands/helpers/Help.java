package com.consoleViewStrategy.commands.helpers;
import com.consoleView.ConsoleNotification;
import com.consoleViewStrategy.commands.Commands;
import com.consoleViewStrategy.commands.ICommand;

public class Help implements ICommand {


    @Override
    public void start(Commands commands) {
        help();
    }

    public void help() {
        System.out.println(ConsoleNotification.HELP);
    }
}