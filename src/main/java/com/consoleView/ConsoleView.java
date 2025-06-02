package com.consoleView;

import com.controller.controlException.ControlException;
import com.controller.IManagerTask;
import com.controller.ManagerFile;


import com.dateTask.*;

import java.util.Map;

public class ConsoleView {
  //  private Scanner in;
    private IManagerTask taskManager;
    MyCommand myCommand;
    private boolean isExit;

    public MyCommand getMyCommand() {
        return myCommand;
    }

    public ConsoleView(IManagerTask taskManager) {
        this.taskManager = taskManager;
        this.myCommand = new MyCommand();;
    }

    public void run(){
        System.out.println("Добро пожаловать в TaskManager!");
        System.out.println("У вас в работе " + taskManager.getTasks().size() + " задач.");
        System.out.println("Введите help что бы отобразить доступные команды.");

        this.myCommand = new MyCommand();
        this.isExit = true;
        while (this.isExit){
            this.myCommand.input("Введите команду: ");
            commandsSelection();
        }
        this.myCommand.close();
    }


    public void commandsSelection() {
        try {
            String command = this.getMyCommand().baseCommand().toLowerCase();
            switch (command) {
/// //// //// /// /// ОБЩЕЕ
                case ("exit") -> {
                    exit();
                }
                case ("help") -> {
                    help();
                }
                case ("save") -> {
                    save();
                }

/// //// //// /// /// ПРИНТ
                case ("printall") -> {
                    printTask();
                }
                case ("printtask") -> {
                    printTask(TypeTask.TASK_NAME);

                }
                case ("printepic") -> {
                    printTask(TypeTask.EPIC_NAME);
                }
                case ("printsubtask") -> {
                    printTask(TypeTask.SUB_NAME);
                }
                case ("printid") -> {
                    printID(myCommand.getID());
                }
                case ("printhistory") -> {
                    printHistory();
                }

/// //// //// /// /// ДОБАВЛЕНИЕ
                case ("add") -> {
                    addTask();
                }
                case ("addepic") -> {
                    addEpicTask();
                }
                case ("addsubtasktoid") -> {
                    addSubTask();
                }

/// //// //// /// /// ИЗМЕНЕНИЕ и УДАЛЕНИЕ
                case ("deleteall") -> {
                    deleteAll();
                }
                case ("deleteid") -> {
                    deleteID();
                }
                case ("renameid") -> {
                    reNameID();
                }
                case ("redescid") -> {
                    reDescID();
                }
                case ("newstatusid") -> {
                    newStatus();
                }
                default -> {
                    System.out.println(ConsoleNotification.NOT_COMMAND + " [" + command + "]");
                }
            }
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }


/// /// /// ХЕЛП СОХРАНЕНИЕ ОЧИСТКА
    public void help() {
        System.out.println(ConsoleNotification.HELP);
    }
    public void deleteAll(){
        taskManager.deleteALL();
        System.out.println(ConsoleNotification.DELETE_ALL);
    }
    public void save(){
        ManagerFile.save(taskManager);
        System.out.println(ConsoleNotification.SAVE);
    }
    public void exit(){
        this.isExit = false;
        System.out.println(ConsoleNotification.EXIT);
    }





/// /// /// ВЫВОД - ПРИНТ
    public void printTask() {
        printTask(null);
    }
    public void printTask(String typeFilter) {
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        for (Map.Entry<Integer, Task> entry : taskManager.getTasks().entrySet()) {
            Task value = entry.getValue();
            if (typeFilter == null || typeFilter.equalsIgnoreCase(value.getTypeTask())) {
                System.out.println(ConsoleUtils.getTaskString(value));
            }
        }
    }

    public void printID(Integer id){
        if(id == null){
            System.out.println(ConsoleNotification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(id)) {
            System.out.println(ConsoleNotification.ID_NOT_EXIST);
            return;
        }
        Task task = taskManager.getTask(id);
        StringBuilder consoleTable = new StringBuilder();
        consoleTable.append(ConsoleUtils.CONSOLE_TITLE);
        consoleTable.append("\n");
        consoleTable.append(ConsoleUtils.getTaskString(task));

        if (task.getTypeTask().equalsIgnoreCase(TypeTask.EPIC_NAME)) {
            for (SubTask subTask : ((EpicTask) task).getSubTasks()) {
                consoleTable.append("\n");
                consoleTable.append(ConsoleUtils.getTaskString(subTask));
            }
        }
        System.out.println(consoleTable);
    }


    public void printHistory() {
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        for (Task task : taskManager.getHistory()) {
            System.out.println(ConsoleUtils.getTaskString(task));
        }
    }

/// /// /// Добавление
    public void addTask(){
        String textName = myCommand.getCommand();
        myCommand.input("Введите описание Task: ");
        Task task = taskManager.addTask(textName, myCommand.getCommand());
        System.out.println("Add Task (id = " + task.getID() + "): " + task);
    }

    public void addEpicTask() {
        String textName = myCommand.getCommand();
        myCommand.input("Введите описание Epic: ");
        Task task = taskManager.addEpic(textName, myCommand.getCommand());
        System.out.println("Add EpicTask (id = " + task.getID() + "): " + task);
    }

    public void addSubTask() {
        Integer idEpicTask = myCommand.getID();
        if(idEpicTask == null){
            System.out.println(ConsoleNotification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idEpicTask)){
            System.out.println(ConsoleNotification.ID_NOT_EXIST);
            return;
        }
        if (!taskManager.isEpic(idEpicTask)) {
            System.out.println(ConsoleNotification.NOT_EPIC);
            return;
        }
        myCommand.input("Введите Название SubTask: ");
        String textName = myCommand.getCommand();
        myCommand.input("Введите описание SubTask: ");
        String textDescription = myCommand.getCommand();
        Task task = taskManager.addSubTaskToEpicID(idEpicTask, textName, textDescription);
        System.out.println("Add SubTask (id = " + task.getID() + "): " + task);
    }

/// /// /// ИЗМЕНЕНИЯ
    public void deleteID() {
        Integer id = myCommand.getID();
        if(id == null){
            System.out.println(ConsoleNotification.ID_NOT_INPUT);
            return;
        }
        Task task = taskManager.deleteIDTask(id);
        System.out.println(ConsoleNotification.DELETE_TASK);
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }

    public void reNameID() {
        Integer idTask = myCommand.getID();if(idTask == null){
            System.out.println(ConsoleNotification.ID_NOT_INPUT);
        return;
       }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println(ConsoleNotification.ID_NOT_EXIST);
       return;
        }
        myCommand.input("Введите новое имя Задачи: ");
        String textName = myCommand.getCommand();
        Task task = taskManager.reNameToIDTask(idTask, textName);

        System.out.println(ConsoleNotification.RENAME);
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }

    public void  reDescID() {
        Integer idTask = myCommand.getID();
        if(idTask == null){
            System.out.println(ConsoleNotification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println(ConsoleNotification.ID_NOT_EXIST);
            return;
        }
        myCommand.input("Введите новое описание Задачи: ");
        String textDescription= myCommand.getCommand();
        Task task = taskManager.reDescToIDTask(idTask, textDescription);
        System.out.println(ConsoleNotification.REDESC);
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }
    public void newStatus() {
        Integer idTask = myCommand.getID();
        if(idTask == null){
            System.out.println(ConsoleNotification.ID_NOT_INPUT);
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println(ConsoleNotification.ID_NOT_EXIST);
            return;
        }
        Task task = taskManager.getTasks().get(idTask);
        if (taskManager.isEpic(idTask)) {
            System.out.println(ConsoleNotification.NOT_CHANGE_STATUS);
            return;
        }

        String status = myCommand.getCommand().toLowerCase();
        switch (status) {
            case "new" -> {
                taskManager.reStatus(idTask, TaskStatus.NEW);
                System.out.println(ConsoleNotification.RESTATUS);
            }
            case "prog" -> {
                taskManager.reStatus(idTask, TaskStatus.IN_PROGRESS);
                System.out.println(ConsoleNotification.RESTATUS);
            }
            case "done" -> {
                taskManager.reStatus(idTask, TaskStatus.DONE);
                System.out.println(ConsoleNotification.RESTATUS);
            }
            default -> {
                System.out.println("[" +status +"] " + ConsoleNotification.STATUS_INCORRECTLY);
            }
        }
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(task));
    }
}
