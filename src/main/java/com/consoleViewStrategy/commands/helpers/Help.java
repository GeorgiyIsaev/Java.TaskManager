package com.consoleViewStrategy.commands.helpers;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;

public class Help implements ICommand {
    private final String name;
    public Help(String name){
        this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + "\" – показать список команда";
    }
    public String getName() {
        return name;
    }

    @Override
    public void start(ConsoleManager consoleManager) {
        help(consoleManager);
    }
    public void help(ConsoleManager consoleManager) {
        System.out.println("ДОСТУПНЫЕ КОМАНДЫ:");
        for(ICommand iCommand : consoleManager.getCommands().values()){
            System.out.println(iCommand.description());
        }
        System.out.println();
    }
}