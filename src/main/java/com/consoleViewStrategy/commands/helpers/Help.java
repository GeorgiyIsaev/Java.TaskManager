package com.consoleViewStrategy.commands.helpers;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleService;
import com.consoleViewStrategy.utils.Notification;

public class Help extends CommandBase {

    public Help(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + "\" – показать список команда";
    }


    @Override
    public void start() {
        help();
    }
    public void help() {
        consoleManager.getNotificationService().sendTo(Notification.HELP);
        for(ICommand iCommand : consoleManager.getCommands().values()){
            System.out.println(iCommand.description());
        }
        System.out.println();
    }
}