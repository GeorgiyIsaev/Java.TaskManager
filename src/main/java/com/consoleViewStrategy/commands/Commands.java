package com.consoleViewStrategy.commands;

import com.consoleView.ConsoleNotification;
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
import com.consoleViewStrategy.utils.CommandNotification;
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.controller.taskManager.TaskManager;

import java.util.HashMap;
import java.util.Map;

public class Commands {
    private Map<String,ICommand> commands;
    private final ConsoleUserAction consoleUserAction;
    private final TaskManager taskManager;
    private boolean isExit = true;

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

    public Commands(TaskManager taskManager){
        this.taskManager = taskManager;
        consoleUserAction = new ConsoleUserAction();
        commands = new HashMap<>();
        CommandNotification com =new CommandNotification();

        commands.put(com.HELP.toLowerCase(), new Help());
        commands.put(com.EXIT.toLowerCase(), new Exit());

        commands.put(com.PRINT_ID.toLowerCase(), new PrintID());
        commands.put(com.PRINT_ALL.toLowerCase(), new PrintAll());
        commands.put(com.PRINT_TASK.toLowerCase(), new PrintTask());
        commands.put(com.PRINT_EPIC.toLowerCase(), new PrintEpic());
        commands.put(com.PRINT_SUBTASK.toLowerCase(), new PrintSubTask());
        commands.put(com.PRINT_HISTORY.toLowerCase(), new PrintHistory());

        commands.put(com.ADD_TASK.toLowerCase(), new AddTask());
        commands.put(com.ADD_EPIC.toLowerCase(), new AddEpic());
        commands.put(com.ADD_SUBTASK.toLowerCase(), new AddSubTask());

        commands.put(com.DELETE_ALL.toLowerCase(), new DeleteAll());
        commands.put(com.DELETE_ByID.toLowerCase(), new DeleteByID());

        commands.put(com.RE_NAME.toLowerCase(), new ReName());
        commands.put(com.RE_DESCRIPTION.toLowerCase(), new ReDescription());
        commands.put(com.RE_STATUS.toLowerCase(), new ReStatus());



    }
    public void commandCall(String command){
        ICommand iCommand =  commands.get(command.toLowerCase());
        if(iCommand ==null){
            System.out.println(ConsoleNotification.NOT_COMMAND);
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
