package com.consoleViewStrategy.commands.prints;

import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;


public class PrintHistory implements ICommand {
    private TaskManager taskManager;;


    private final String name;
    public PrintHistory(String name){
        this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + "\" – показать историю вызовов задач";
    }
    public String getName() {
        return name;
    }

    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        printHistory();
    }

    public void printHistory() {
        System.out.println(TaskToString.CONSOLE_TITLE);
        for (Task task : taskManager.getHistory()) {
            System.out.println(TaskToString.transform(task));
        }
    }
}