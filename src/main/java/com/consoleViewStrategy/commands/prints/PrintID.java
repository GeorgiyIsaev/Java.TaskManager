package com.consoleViewStrategy.commands.prints;

import com.consoleViewStrategy.utils.Notification;
import com.consoleView.ConsoleUtils;
import com.consoleViewStrategy.ConsoleManager;
import com.consoleViewStrategy.commands.ICommand;
import com.controller.taskManager.TaskManager;
import com.dateTask.*;

public class PrintID implements ICommand {
    TaskManager taskManager;
    Integer id;
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
        consoleTable.append(ConsoleUtils.CONSOLE_TITLE);
        consoleTable.append("\n");
        consoleTable.append(ConsoleUtils.getTaskString(task));

        if (task.getTypeTask().equalsIgnoreCase(TaskType.EPIC.name())) {
            for (SubTask subTask : ((EpicTask) task).getSubTasks()) {
                consoleTable.append("\n");
                consoleTable.append(ConsoleUtils.getTaskString(subTask));
            }
        }
        System.out.println(consoleTable);
    }
}