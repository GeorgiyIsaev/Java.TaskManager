package com.consoleViewStrategy.commands.prints;

import com.consoleViewStrategy.commands.CommandBase;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.utils.TaskToString;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.*;

public class PrintID extends CommandBase {

    public PrintID(String name, ConsoleManager consoleManager) {
        super(name, consoleManager);
    }

    @Override
    public String description(){
        return  "\"" + name + " 'NUMBER ID'\" - показать задачу по id";
    }

    @Override
    public void start( ) {
        Integer id = consoleManager.getConsoleUserAction().getID();
        printID(id);
    }

    private void printID(Integer id){
        if(id == null){
            consoleManager.getNotificationService().sendTo(Notification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(id)) {
            consoleManager.getNotificationService().sendTo(Notification.ID_NOT_EXIST);
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