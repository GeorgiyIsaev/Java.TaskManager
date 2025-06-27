package com.consoleViewStrategy.commands.prints;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;
import com.dateTask.TaskType;

import java.util.Map;

public class PrintEpic extends CommandBase {

    public PrintEpic(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }
    @Override
    public String description(){
        return  "\"" + name + "\" – показать только ЭПИКИ";
    }
    @Override
    public void start( ) {
        printTask(TaskType.EPIC.name());
    }
    public void printTask(String typeFilter) {
        System.out.println(TaskToString.CONSOLE_TITLE);
        for (Map.Entry<Integer, Task> entry : taskManager.getTasks().entrySet()) {
            Task value = entry.getValue();
            if (typeFilter == null || typeFilter.equalsIgnoreCase(value.getTypeTask())) {
                System.out.println(TaskToString.transform(value));
            }
        }
    }
}
