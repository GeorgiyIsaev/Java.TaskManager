package com.consoleViewStrategy.commands.prints;

import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

import java.util.Map;

public class PrintAll implements ICommand {
    private TaskManager taskManager;

    private final String name;
    public PrintAll(String name){
        this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + "\" – показать ВСЕ задачи";
    }
    public String getName() {
        return name;
    }

    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
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