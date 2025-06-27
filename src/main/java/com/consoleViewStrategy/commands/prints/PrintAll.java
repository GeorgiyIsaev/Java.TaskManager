package com.consoleViewStrategy.commands.prints;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

import java.util.Map;

public class PrintAll extends CommandBase {

    public PrintAll(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + "\" – показать ВСЕ задачи";
    }


    @Override
    public void start( ) {
        printAll();
    }

    public void printAll() {
        System.out.println(TaskToString.CONSOLE_TITLE);
        for (Map.Entry<Integer, Task> entry : taskManager.getTasks().entrySet()) {
            Task value = entry.getValue();
            System.out.println(TaskToString.transform(value));
        }
    }
}