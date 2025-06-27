package com.consoleViewStrategy.commands.helpers;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.commands.ICommand;

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
        System.out.println("ДОСТУПНЫЕ КОМАНДЫ:");
        for(ICommand iCommand : consoleManager.getCommands().values()){
            System.out.println(iCommand.description());
        }
        System.out.println();
    }
}