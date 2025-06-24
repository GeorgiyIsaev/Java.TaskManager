package com.consoleViewStrategy.commands;

import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;

import java.util.HashMap;
import java.util.Map;

public class Commands {
    private Map<String,ICommand> commands;
    private final ConsoleUserAction consoleUserAction;
    private final TaskManager taskManager;
    private boolean isExit = true;

    public ConsoleUserAction getConsoleUserAction() {
        return consoleUserAction;
    }
    public Map<String, ICommand> getCommands() {
        return commands;
    }

    public Commands(TaskManager taskManager){
        this.taskManager = taskManager;
        consoleUserAction = new ConsoleUserAction();
        commands = new HashMap<>();
        commands.put("1", new Command1());
        commands.put("2", new Command2());
    }
    public void commandCall(String command){
        ICommand iCommand =  commands.get(command);
        if(iCommand ==null){
            System.out.println("НЕТ");
            return;
        }
        iCommand.start(this);
    }
    public void run(){
        while(isExit) {
            consoleUserAction.input("Введите команду: ");
            if(consoleUserAction.getCommand().equalsIgnoreCase("Exit")){
                isExit = false;
                break;
            }
            commandCall(consoleUserAction.getCommand());
        }
    }
}
