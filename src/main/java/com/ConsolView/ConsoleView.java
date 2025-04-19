package com.ConsolView;

import com.Controller.ManagerTask;


import com.DateTask.Task;
import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.TaskStatus;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private Scanner in;
    private ManagerTask managerTask;
    MyCommand myCommand;

    public ConsoleView(ManagerTask managerTask) {
        this.managerTask = managerTask;
        in = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Добро пожаловать в TaskManager!");
        System.out.println("У вас в работе " + managerTask.getListTask().size() + " задач.");
        System.out.println("Введите help что бы отобразить доступные команды.");

        boolean isExit = false;
        do {
            try {

                System.out.print("Input command: ");
                String textCommand = in.nextLine();
                myCommand = new MyCommand(textCommand);
                switch (myCommand.baseCommand()) {
/// //// //// /// /// ОБЩЕЕ
                    case ("exit") -> {
                        isExit = true;
                    }
                    case ("help") -> {
                        help();
                    }
                    case ("save") -> {
                        if (false) {
                            System.out.println("Данные сохранены в файл!");
                        } else {
                            System.out.println("Сохранение не реализованно");
                        }
                    }

/// //// //// /// /// ПРИНТ
                    case ("printall") -> {
                        printTaskMap();
                    }
                    case ("printtask") -> {
                        printTaskMap("TASK");

                    }
                    case ("printepic") -> {
                        printTaskMap("EPIC");
                    }
                    case ("printsubtask") -> {
                        printTaskMap("SubTASK");
                    }
                    case ("printid") -> {
                        printID(myCommand.getID());
                    }
                    case ("printdebug") -> {
                        printDebug();
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
                        managerTask.deleteALL();
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
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        while (!isExit);
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
    String myFormat = "%-4s %-8s %-12s %-12s %-25s";
    public void printTaskMap() {
        printTaskMap(null);
    }
    public void printTaskMap(String typeFilter) {
        // "EPIC" "TASK" "SubTASK"
        System.out.println(String.format(myFormat, "ID", "TYPE", "STATUS", "LINK", "INFORMATION"));
        for (Map.Entry<Integer, Task> entry : managerTask.getListTask().entrySet()) {
            Task value = entry.getValue();
            if (typeFilter == null || typeFilter.equalsIgnoreCase(value.getTypeTask())) {
                System.out.println(String.format(myFormat,
                        value.getID(), value.getTypeTask(), value.getTaskStatus(),
                        value.getLinkStr(), value));
            }
        }
    }
    public void printID(Integer id){
        if(id == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        if (!managerTask.getListTask().containsKey(id)) {
            System.out.println("ERROR: Задачи с индексом " + id + " не существует!");
        }
        Task task = managerTask.getListTask().get(id);
        StringBuilder consoleTable = new StringBuilder();
        consoleTable.append(String.format(myFormat, "ID", "TYPE", "STATUS", "LINK", "INFORMATION"));
        consoleTable.append("\n");
        consoleTable.append(String.format(myFormat, task.getID(), task.getTypeTask(), task.getTaskStatus(),
                task.getLinkStr(), task));

        if (task.getTypeTask().equalsIgnoreCase("EPIC")) {
            for (SubTask subTask : ((EpicTask) task).getSubTasks()) {
                consoleTable.append("\n");
                consoleTable.append(String.format(myFormat, subTask.getID(), subTask.getTypeTask(), subTask.getTaskStatus(),
                        subTask.getLinkStr(), subTask));
            }
        }
        System.out.println(consoleTable);
    }
    public void printDebug() {
       ///  Только для проверок разработчиком
        String myFormatDebug = "%3s %4s %8s %12s %12s %s";
        System.out.println(String.format(myFormatDebug, "KEY", "ID", "TYPE", "STATUS", "LINK", "INFORMATION"));
        Iterator<Map.Entry<Integer, Task>> iterator = managerTask.getListTask().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();
            System.out.println(String.format(myFormatDebug,
                    key, value.getID(), value.getTypeTask(), value.getTaskStatus(),
                    value.getLinkStr(), value));
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
        Task task = managerTask.addTask(textName, textDescription);
        System.out.println("Add Task (id = " + task.getID() + "): " + task);
    }
    public void addEpicTask() throws Exception {
        String textName = myCommand.secondCommand();
        if(textName == null){
            System.out.println("ERROR: Вы не ввели имя задачи");
            return;
        }
        System.out.print("Input description Epic: ");
        String textDescription = in.nextLine();
        Task task = managerTask.addEpic(textName, textDescription);
        System.out.println("Add EpicTask (id = " + task.getID() + "): " + task);
    }
    public void addSubTask(String command) throws Exception {
        Integer idEpicTask = myCommand.getID();
        if(idEpicTask == null){
            System.out.println("ERROR: вы не ввели id Эпик задачи");
            return;
        }

        if (!managerTask.getListTask().get(idEpicTask).getTypeTask().equalsIgnoreCase("EPIC")) {
            System.out.println("ERROR: Задача с id " + idEpicTask + " не ЭПИК");
            return;
        }
        System.out.print("Input Name Sub Task: ");
        String textName = in.nextLine();
        System.out.print("Input Description Sub Task: ");
        String textDescription = in.nextLine();
        Task task = managerTask.addSubTaskToEpicID(textName, textDescription, idEpicTask);
        System.out.println("Add SubTask (id = " + task.getID() + "): " + task);
    }


/// /// /// ИЗМЕНЕНИЯ
    public void deleteID() throws Exception {
        Integer i = myCommand.getID();
        if(i == null){
            System.out.println("ERROR: вы не ввели id Эпик задачи");
            return;
        }


        int i =  command.indexOf(' ');
        if (command.length() <= i++) {
            throw new Exception("ERROR: вы не ввели id задачи");
        }
        int idEpicTask = Integer.parseInt(command.substring(i));
        Task task = managerTask.deleteIDTask(idEpicTask);
        System.out.println("ЗАПИСЬ: " + task.getTypeTask() + " " + task + "УДАЛЕНА!");
    }
    public void reNameID(String command) throws Exception {
        int i =  command.indexOf(' ');
        if (command.length() <= i++) {
            throw new Exception("ERROR: вы не ввели id задачи");
        }
        int idTask = Integer.parseInt(command.substring(i));
        if (!managerTask.getListTask().containsKey(idTask)) {
            throw new Exception("ERROR: Задачи с ID: " + idTask + " не существует!");
        }
        System.out.print("Input NewName Task: ");
        String textName = in.nextLine();
        Task task = managerTask.reNameToIDTask(idTask, textName);

        System.out.println(task.getTypeTask() + " с ID: " + idTask + " переименован! -> " + task);
    }
    public void  reDescID(String command) throws Exception {
        int i =  command.indexOf(' ');
        if (command.length() <= i++) {
            throw new Exception("ERROR: вы не ввели id задачи");
        }
        int idTask = Integer.parseInt(command.substring(i));
        if (!managerTask.getListTask().containsKey(idTask)) {
            throw new Exception("ERROR: Задачи с ID: " + idTask + " не существует!");
        }
        System.out.print("Input NewDescription Task: ");
        String textName = in.nextLine();
        Task task = managerTask.reDescToIDTask(idTask, textName);
        System.out.println(task.getTypeTask() + " с ID: " + idTask + " изменил описание! -> " + task);
    }
    public void newStatus(String command) throws Exception {
        int i =  command.indexOf(' ');
        if (command.length() <= i++) {
            throw new Exception("ERROR: вы не ввели id задачи");
        }
        String[] elemetsString = command.split("\\s+");
        int idTask = Integer.parseInt(elemetsString[1]);
        if (!managerTask.getListTask().containsKey(idTask)) {
            throw new Exception("ERROR: Задачи с ID: " + idTask + " не существует!");
        }
        if (managerTask.isEpic(idTask)) {
            throw new Exception("ERROR: Задачи с ID: " + idTask + " EPIC! Расчет статуса осуществляется автоматически!/n"+
                    "Статус задачи: " + managerTask.getListTask().get(idTask).getTaskStatus());
        }
        TaskStatus newStatus = null;
        switch (elemetsString[2].toLowerCase()) {
            case "new" -> {
                newStatus = TaskStatus.NEW;
            }
            case "prog" -> {
                newStatus = TaskStatus.IN_PROGRESS;
            }
            case "done" -> {
                newStatus = TaskStatus.DONE;
            }
        }
        if (newStatus == null) {
            throw new Exception("ERROR: Значение '" + elemetsString[2].toUpperCase() + "' не соответствует команде изменения статус.\n" +
                    " Допустимые значение 'NEW' 'PROG' 'DONE'");
        }

        if (managerTask.reStatus(idTask, newStatus)) {
            System.out.println("Статус задачи с ID: " + idTask + " изменен!");
        } else {
            System.out.println("Статус задачи с ID: " + idTask + " не изменен!");
        }
        System.out.println("Статус задачи: " + managerTask.getListTask().get(idTask).getTaskStatus());
    }








}
