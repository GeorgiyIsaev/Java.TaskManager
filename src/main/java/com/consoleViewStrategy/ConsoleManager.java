package com.consoleViewStrategy;
import com.consoleViewStrategy.commands.ICommand;
import com.consoleViewStrategy.utils.ConsoleService;
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
import com.consoleViewStrategy.utils.ConsoleUserAction;
import com.consoleViewStrategy.utils.NotificationService;
import com.controller.taskManager.TaskManager;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleManager {
    private final Map<String, ICommand> commands;
    private final ConsoleUserAction consoleUserAction;
    private final TaskManager taskManager;
    private boolean isExit = true;
    NotificationService notificationService;

    public ConsoleManager(TaskManager taskManager, NotificationService notificationService){
        this.taskManager = taskManager;
        this.notificationService = notificationService;
        consoleUserAction = new ConsoleUserAction();
        commands = new LinkedHashMap<>();
        addCommand(new Help("Help", this));
        addCommand(new Exit("Exit", this));

        addCommand(new PrintID("PrintID", this));
        addCommand(new PrintAll("PrintAll", this));
        addCommand(new PrintTask("PrintTask", this));
        addCommand(new PrintEpic("PrintEpic", this));
        addCommand(new PrintSubTask("PrintSubTask", this));
        addCommand(new PrintHistory("PrintHistory", this));

        addCommand(new AddTask("AddTask", this));
        addCommand(new AddEpic("AddEpic", this));
        addCommand(new AddSubTask("AddSubTaskToID", this));

        addCommand(new DeleteAll("DeleteAll", this));
        addCommand(new DeleteByID("DeleteID", this));

        addCommand(new ReName("ReName", this));
        addCommand(new ReDescription("ReDescription", this));
        addCommand(new ReStatus("ReStatus", this));
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
    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void commandCall(String command){
        ICommand iCommand =  commands.get(command.toLowerCase());
        if(iCommand ==null){
            notificationService.sendTo(Notification.NOT_COMMAND);
            return;
        }
        iCommand.start();
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
