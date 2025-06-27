package com.consoleViewStrategy;

import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.commands.adds.AddEpic;
import com.consoleViewStrategy.commands.adds.AddSubTask;
import com.consoleViewStrategy.commands.adds.AddTask;
import com.consoleViewStrategy.commands.change.ReDescription;
import com.consoleViewStrategy.commands.change.ReName;
import com.consoleViewStrategy.commands.change.ReStatus;
import com.consoleViewStrategy.commands.deletes.DeleteAll;
import com.consoleViewStrategy.commands.deletes.DeleteByID;
import com.consoleViewStrategy.commands.helpers.Exit;
import com.consoleViewStrategy.commands.helpers.Help;
import com.consoleViewStrategy.commands.prints.*;
import com.consoleViewStrategy.utils.CommandName;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleManager {
    private final Map<String, ICommand> commands;
    private final ConsoleUserAction consoleUserAction;
    private final TaskManager taskManager;
    private boolean isExit = true;

    public ConsoleManager(TaskManager taskManager){
        this.taskManager = taskManager;
        consoleUserAction = new ConsoleUserAction();
        commands = new LinkedHashMap<>();
        addCommand(new Help("Help"));
        addCommand(new Exit("Exit"));

        addCommand(new PrintID("PrintID"));
        addCommand(new PrintAll("PrintAll"));
        addCommand(new PrintTask("PrintTask"));
        addCommand(new PrintEpic("PrintEpic"));
        addCommand(new PrintSubTask("PrintSubTask"));
        addCommand(new PrintHistory("PrintHistory"));

        addCommand(new AddTask("AddTask"));
        addCommand(new AddEpic("AddEpic"));
        addCommand(new AddSubTask("AddSubTaskToID"));

        addCommand(new DeleteAll("DeleteAll"));
        addCommand(new DeleteByID("DeleteID"));

        addCommand(new ReName("ReName"));
        addCommand(new ReDescription("ReDescription"));
        addCommand(new ReStatus("ReStatus"));
    }
    public void addCommand(ICommand iCommand){
        commands.put(iCommand.getName().toLowerCase(), iCommand);
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public ConsoleUserAction getConsoleUserAction() {
        return consoleUserAction;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public Map<String, ICommand> getCommands() {
        return commands;
    }

    public void commandCall(String command){
        ICommand iCommand =  commands.get(command.toLowerCase());
        if(iCommand ==null){
            System.out.println(Notification.NOT_COMMAND);
            return;
        }
        iCommand.start(this);
    }
    public void hello(){
        System.out.println("Добро пожаловать в TaskManager!");
        System.out.println("У вас в работе " + taskManager.getTasks().size() + " задач.");
        System.out.println("Введите help что бы отобразить доступные команды.");
    }

    public void run(){
        hello();
        while(isExit()) {
            consoleUserAction.input("Введите команду: ");
            commandCall(consoleUserAction.getCommand());
        }
        consoleUserAction.close();
    }
}
