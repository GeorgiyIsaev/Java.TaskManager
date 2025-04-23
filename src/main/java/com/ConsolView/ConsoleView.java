package com.ConsolView;

import com.Controller.ControlException.ControlException;
import com.Controller.ManagerFile;
import com.Controller.ManagerTaskInMemory;


import com.DateTask.Task;
import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.TaskStatus;

import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private Scanner in;
    private ManagerTaskInMemory managerTaskInMemory;
    MyCommand myCommand;

    public void setMyCommand(String textCommand) {
        this.myCommand = new MyCommand(textCommand);;
    }

    public ConsoleView(ManagerTaskInMemory managerTaskInMemory) {
        this.managerTaskInMemory = managerTaskInMemory;
        in = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Добро пожаловать в TaskManager!");
        System.out.println("У вас в работе " + managerTaskInMemory.getListTask().size() + " задач.");
        System.out.println("Введите help что бы отобразить доступные команды.");

        boolean isExit = false;
        do {
            try {

                System.out.print("Input command: ");
                String textCommand = in.nextLine();
                setMyCommand(textCommand);
                switch (myCommand.baseCommand()) {
/// //// //// /// /// ОБЩЕЕ
                    case ("exit") -> {
                        isExit = true;
                    }
                    case ("help") -> {
                        help();
                    }
                    case ("save") -> {
                        ManagerFile.save(managerTaskInMemory);
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
                        managerTaskInMemory.deleteALL();
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
        for (Map.Entry<Integer, Task> entry : managerTaskInMemory.getListTask().entrySet()) {
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
        if (!managerTaskInMemory.getListTask().containsKey(id)) {
            System.out.println("ERROR: Задачи с индексом " + id + " не существует!");
        }
        Task task = managerTaskInMemory.getListTask().get(id);
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
        for (Map.Entry<Integer, Task> entry : managerTaskInMemory.getListTask().entrySet()) {
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
        Task task = managerTaskInMemory.addTask(textName, textDescription);
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
        Task task = managerTaskInMemory.addEpic(textName, textDescription);
        System.out.println("Add EpicTask (id = " + task.getID() + "): " + task);
    }
    public void addSubTask() {
        Integer idEpicTask = myCommand.getID();
        if(idEpicTask == null){
            System.out.println("ERROR: вы не ввели id Эпик задачи");
            return;
        }

        if (!managerTaskInMemory.getListTask().get(idEpicTask).getTypeTask().equalsIgnoreCase("EPIC")) {
            System.out.println("ERROR: Задача с id " + idEpicTask + " не ЭПИК");
            return;
        }
        System.out.print("Input Name Sub Task: ");
        String textName = in.nextLine();
        System.out.print("Input Description Sub Task: ");
        String textDescription = in.nextLine();
        Task task = managerTaskInMemory.addSubTaskToEpicID(textName, textDescription, idEpicTask);
        System.out.println("Add SubTask (id = " + task.getID() + "): " + task);
    }

/// /// /// ИЗМЕНЕНИЯ
    public void deleteID() {
        Integer id = myCommand.getID();
        if(id == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        Task task = managerTaskInMemory.deleteIDTask(id);
        System.out.println("ЗАПИСЬ: " + task.getTypeTask() + " " + task + "УДАЛЕНА!");
    }
    public void reNameID() {
        Integer idTask = myCommand.getID();
        if(idTask == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        if (!managerTaskInMemory.getListTask().containsKey(idTask)) {
            System.out.println("ERROR: Задачи с ID: " + idTask + " не существует!");
            return;
        }
        System.out.print("Input NewName Task: ");
        String textName = in.nextLine();
        Task task = managerTaskInMemory.reNameToIDTask(idTask, textName);

        System.out.println(task.getTypeTask() + " с ID: " + idTask + " переименован! -> " + task);
    }
    public void  reDescID() {
        Integer idTask = myCommand.getID();
        if(idTask == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        if (!managerTaskInMemory.getListTask().containsKey(idTask)) {
            System.out.println("ERROR: Задачи с ID: " + idTask + " не существует!");
            return;
        }
        System.out.print("Input NewDescription Task: ");
        String textName = in.nextLine();
        Task task = managerTaskInMemory.reDescToIDTask(idTask, textName);
        System.out.println(task.getTypeTask() + " с ID: " + idTask + " изменил описание! -> " + task);
    }
    public void newStatus() {
        Integer idTask = myCommand.getID();
        if(idTask == null){
            System.out.println("ERROR: вы не ввели id задачи");
            return;
        }
        if (!managerTaskInMemory.getListTask().containsKey(idTask)) {
            System.out.println("ERROR: Задачи с ID: " + idTask + " не существует!");
            return;
        }
        if (managerTaskInMemory.isEpic(idTask)) {
            System.out.println("ERROR: Задачи с ID: " + idTask + " EPIC! Расчет статуса осуществляется автоматически!/n"+
                    "Статус задачи: " + managerTaskInMemory.getListTask().get(idTask).getTaskStatus());
            return;
        }
        switch (myCommand.thirdCommand().toLowerCase()) {
            case "new" -> {
                managerTaskInMemory.reStatus(idTask, TaskStatus.NEW);
            }
            case "prog" -> {
                managerTaskInMemory.reStatus(idTask, TaskStatus.IN_PROGRESS);
            }
            case "done" -> {
                managerTaskInMemory.reStatus(idTask, TaskStatus.DONE);
            }
            default -> {
                System.out.println("ERROR: Значение '" + myCommand.thirdCommand().toUpperCase() + "' не соответствует значению статуса.\n" +
                        " Допустимые значение 'NEW' 'PROG' 'DONE'");
            }
        }
        System.out.println("Статус задачи: " + managerTaskInMemory.getListTask().get(idTask) + " -> " + managerTaskInMemory.getListTask().get(idTask).getTaskStatus());
    }
}
