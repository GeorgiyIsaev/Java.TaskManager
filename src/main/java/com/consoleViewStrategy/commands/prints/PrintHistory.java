package com.consoleViewStrategy.commands.prints;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;


public class PrintHistory extends CommandBase {

    public PrintHistory(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + "\" – показать историю вызовов задач";
    }


    @Override
    public void start( ) {
        printHistory();
    }

    public void printHistory() {
        consoleManager.getNotificationService().sendTo(Notification.PRINT_HISTORY);
        System.out.println(TaskToString.CONSOLE_TITLE);
        for (Task task : taskManager.getHistory()) {
            System.out.println(TaskToString.transform(task));
        }
    }
}