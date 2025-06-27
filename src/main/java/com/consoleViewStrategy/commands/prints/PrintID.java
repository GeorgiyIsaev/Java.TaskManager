package com.consoleViewStrategy.commands.prints;

import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.*;

public class PrintID implements ICommand {
   private TaskManager taskManager;
    private Integer id;


    private final String name;
   public PrintID(String name){
        this.name = name;
    }
    @Override
    public String description(){
        return  "\"" + name + " 'NUMBER ID'\" - показать задачу по id";
    }
    public String getName() {
        return name;
    }

    @Override
    public void start(ConsoleManager consoleManager) {
        taskManager = consoleManager.getTaskManager();
        id = consoleManager.getConsoleUserAction().getID();
        printID();
    }

    private void printID(){
        if(id == null){
            System.out.println(Notification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(id)) {
            System.out.println(Notification.ID_NOT_EXIST);
            return;
        }
        Task task = taskManager.getTask(id);
        StringBuilder consoleTable = new StringBuilder();
        consoleTable.append(TaskToString.CONSOLE_TITLE);
        consoleTable.append("\n");
        consoleTable.append(TaskToString.transform(task));

        if (task.getTypeTask().equalsIgnoreCase(TaskType.EPIC.name())) {
            for (SubTask subTask : ((EpicTask) task).getSubTasks()) {
                consoleTable.append("\n");
                consoleTable.append(TaskToString.transform(subTask));
            }
        }
        System.out.println(consoleTable);
    }
}