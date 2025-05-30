package com.consoleView;

import com.controller.controlException.ControlException;
import com.controller.IManagerTask;
import com.controller.ManagerFile;


import com.dateTask.*;

import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private Scanner in;
    private IManagerTask taskManager;
    MyCommand myCommand;

    public void setMyCommand(String textCommand) {
        this.myCommand = new MyCommand(textCommand);;

    }

    public ConsoleView(IManagerTask taskManager) {
        this.taskManager = taskManager;
        in = new Scanner(System.in);
        this.myCommand = new MyCommand("");;
    }

    public void run(){
        System.out.println("Добро пожаловать в TaskManager!");
        System.out.println("У вас в работе " + taskManager.getTasks().size() + " задач.");
        System.out.println("Введите help что бы отобразить доступные команды.");

        boolean isExit = false;
        do {
            isExit = commandsSelection();
        }while (isExit);
    }


    public boolean commandsSelection() {
        try {
            System.out.print("Input command: ");
           // Scanner in2 = new Scanner(System.in);
            in = new Scanner(System.in);
            String textCommand = in.nextLine();
            setMyCommand(textCommand);
            switch (myCommand.baseCommand()) {
/// //// //// /// /// ОБЩЕЕ
                case ("exit") -> {
                    return true;
                }
                case ("help") -> {
                    help();
                }
                case ("save") -> {
                    ManagerFile.save(taskManager);
                }

/// //// //// /// /// ПРИНТ
                case ("printall") -> {
                    printTaskMap();
                }
                case ("printtask") -> {
                    printTaskMap(TypeTask.TASK_NAME);

                }
                case ("printepic") -> {
                    printTaskMap(TypeTask.EPIC_NAME);
                }
                case ("printsubtask") -> {
                    printTaskMap(TypeTask.SUB_NAME);
                }
                case ("printid") -> {
                    printID(myCommand.getID());
                }
                case ("printdebug") -> {
                    printDebug();
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
                    taskManager.deleteALL();
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
            }
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }


/// /// /// ХЕЛП
    public void help() {
        String textHelp = "ДОСТУПНЫЕ КОМАНДЫ: \n" +
                " \"help\" - показать список команда\n" +
                " \"exit\" - завершить программу\n" +
                " \"save\" - сохранить все записи\n" +
                "КОМАНДЫ ДЛЯ ОТОБРАЖЕНИЯ ЗАДАЧ: \n" +
                " \"printAll\" - показать все задачи\n" +
                " \"printEpic\" - показать только ЭПИКИ\n" +
                " \"printSubTask\" - показать только ПОДЗАДАЧИ \n" +
                " \"printHistory'\" - показать историю вызова задач\n" +
                " \"printTask\" - показать только ОБЫЧНЫЕ ЗАДАЧИ \n" +
                " \"printId 'NUMBER ID'\" - показать задачу по id\n" +
                "КОМАНДЫ ДЛЯ ДОБАВЛЕНИЯ ЗАДАЧ: \n" +
                " \"add 'указать имя задач'\" - добавить обычную задачу\n" +
                " \"addEpic 'указать имя задач'\" - добавить задачу c подзадачами\n" +
                " \"addSubTaskToID 'NUMBER ID'\" - добавить подзадачу к Эпику с указанным ID\n" +
                "УДАЛЕНИЕ И ИЗМЕНЕНИЕ ЗАДАЧ: \n" +
                " \"deleteAll\" - удалить все задачи\n" +
                " \"deleteID 'NUMBER ID'\" - удалить задачу с ID\n" +
                " \"reNameID 'NUMBER ID'\" – изменить имя задачи с ID\n" +
                " \"reDescID 'NUMBER ID'\" – изменить описание задачи с ID\n" +
                " \"newStatusId 'NUMBER ID' ('NEW, 'PROG' or 'DONE')\"  – изменить статус выполнения задачи с ID\n";
        System.out.println(textHelp);
    }

/// /// /// ВЫВОД - ПРИНТ
    //String myFormat = "%-4s %-8s %-12s %-12s %-25s";
    public void printTaskMap() {
        printTaskMap(null);
    }
    public void printTaskMap(String typeFilter) {
        // "EPIC" "TASK" "SubTASK"
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
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        if (!taskManager.getTasks().containsKey(id)) {
            System.out.println("ERROR: Задачи с индексом " + id + " не существует!");
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
    public void printDebug() {
       ///  Только для проверок разработчиком
        String myFormatDebug = "%3s %4s %8s %12s %12s %s";
        System.out.println(String.format(myFormatDebug, "KEY", "ID", "TYPE", "STATUS", "LINK", "INFORMATION"));
        for (Map.Entry<Integer, Task> entry : taskManager.getTasks().entrySet()) {
            Integer key = entry.getKey();
            Task value = entry.getValue();
            System.out.println(String.format(myFormatDebug,
                    key, value.getID(), value.getTypeTask(), value.getStatus(),
                    value.getLinkStr(), value));
        }
    }

    public void printHistory() {
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        for (Task task : taskManager.getHistory()) {
            System.out.println(ConsoleUtils.getTaskString(task));
        }
    }

/// /// /// Добавление
    public void addTask(){
        String textName = myCommand.secondCommand();
        if(textName == null){
            System.out.println("ERROR: Вы не ввели имя задачи");
            return;
        }
        System.out.print("Input description Task: ");
        String textDescription = in.nextLine();
        Task task = taskManager.addTask(textName, textDescription);
        System.out.println("Add Task (id = " + task.getID() + "): " + task);
    }
    public void addEpicTask() {
        String textName = myCommand.secondCommand();
        if(textName == null){
            System.out.println("ERROR: Вы не ввели имя задачи");
            return;
        }
        System.out.print("Input description Epic: ");
        String textDescription = in.nextLine();
        Task task = taskManager.addEpic(textName, textDescription);
        System.out.println("Add EpicTask (id = " + task.getID() + "): " + task);
    }
    public void addSubTask() {
        Integer idEpicTask = myCommand.getID();
        if(idEpicTask == null){
            System.out.println("ERROR: вы не ввели id Эпик задачи");
            return;
        }

        if (!taskManager.getTasks().containsKey(idEpicTask)){
            System.out.println("ERROR: Задачи с ID " + idEpicTask + "не существует!");
        }

        if (!taskManager.isEpic(idEpicTask)) {
            System.out.println("ERROR: Задача с id " + idEpicTask + " не ЭПИК");
            return;
        }
        System.out.print("Input Name Sub Task: ");
        String textName = in.nextLine();
        System.out.print("Input Description Sub Task: ");
        String textDescription = in.nextLine();
        Task task = taskManager.addSubTaskToEpicID(idEpicTask, textName, textDescription);
        System.out.println("Add SubTask (id = " + task.getID() + "): " + task);
    }

/// /// /// ИЗМЕНЕНИЯ
    public void deleteID() {
        Integer id = myCommand.getID();
        if(id == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        Task task = taskManager.deleteIDTask(id);
        System.out.println("ЗАПИСЬ: " + task.getTypeTask() + " " + task + "УДАЛЕНА!");
    }
    public void reNameID() {
        Integer idTask = myCommand.getID();
        if(idTask == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println("ERROR: Задачи с ID: " + idTask + " не существует!");
            return;
        }
        System.out.print("Input NewName Task: ");
        String textName = in.nextLine();
        Task task = taskManager.reNameToIDTask(idTask, textName);

        System.out.println(task.getTypeTask() + " с ID: " + idTask + " переименован! -> " + task);
    }
    public void  reDescID() {
        Integer idTask = myCommand.getID();
        if(idTask == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println("ERROR: Задачи с ID: " + idTask + " не существует!");
            return;
        }
        System.out.print("Input NewDescription Task: ");
        String textName = in.nextLine();
        Task task = taskManager.reDescToIDTask(idTask, textName);
        System.out.println(task.getTypeTask() + " с ID: " + idTask + " изменил описание! -> " + task);
    }
    public void newStatus() {
        Integer idTask = myCommand.getID();
        if(idTask == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        if (!taskManager.getTasks().containsKey(idTask)) {
            System.out.println("ERROR: Задачи с ID: " + idTask + " не существует!");
            return;
        }
        Task task = taskManager.getTasks().get(idTask);
        if (taskManager.isEpic(idTask)) {
            System.out.println("ERROR: Задачи с ID: " + idTask + " EPIC! Расчет статуса осуществляется автоматически!/n"+
                    "Статус задачи: " + task.getStatus());
            return;
        }
        switch (myCommand.thirdCommand().toLowerCase()) {
            case "new" -> {
                taskManager.reStatus(idTask, TaskStatus.NEW);
            }
            case "prog" -> {
                taskManager.reStatus(idTask, TaskStatus.IN_PROGRESS);
            }
            case "done" -> {
                taskManager.reStatus(idTask, TaskStatus.DONE);
            }
            default -> {
                System.out.println("ERROR: Значение '" + myCommand.thirdCommand().toUpperCase() + "' не соответствует значению статуса.\n" +
                        " Допустимые значение 'NEW' 'PROG' 'DONE'");
            }
        }
        System.out.println("Статус задачи: " + task + " -> " + task.getStatus());
    }
}
