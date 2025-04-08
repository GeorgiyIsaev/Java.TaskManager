package com.ConsolView;


import com.Controller.ManagerTaskMap;

import com.DateTask.Task;
import com.DateTask.EpicTask;
import com.DateTask.SubTask;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    Scanner in;
    //HashMap<Integer, Task> listTask;
    ManagerTaskMap managerTaskMap = new ManagerTaskMap();

    public ConsoleView(ManagerTaskMap managerTaskMap ){
        this.managerTaskMap = managerTaskMap;
        in = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Добро пожаловать в TaskManager!");
        System.out.println("У вас в работе "+ managerTaskMap.getListTask().size() +" задач.");
        System.out.println("Введите help что бы отобразить доступные команды.");

        boolean isExit = false;
        do{
            try {

            System.out.print("Input command: ");
            String command = in.nextLine();

            String inputCommand;
            int i = command.indexOf(' ');
            if (i > 0) {
                inputCommand = command.substring(0, i).toLowerCase();
            }
            else inputCommand = command.toLowerCase();

            switch (inputCommand) {
                case ("exit") -> {
                    isExit = true;
                }
                case ("help") -> {
                    help();
                }
                case ("save") -> {
                    managerTaskMap.saveToFileTXT();
                }

                /*Отображение*/
                case ("printall") -> {
                    printTaskMap();
                }
                case ("printtask") -> {
                    printTaskMap("TASK");
                    System.out.println("Команда  statnjusdownid не распознана!");
                }
                case ("printepic") -> {
                     printTaskMap("EPIC");
                }
                case ("printsubtask") -> {
                      printTaskMap("SubTASK");
                }
                case ("printid") -> {
                    if (command.length() <= i++) {
                        throw new Exception ("ERROR: вы не ввели id задачи");
                    }
                    printID(command.substring(i));
                }
                case ("printdebug") -> {
                    printDebug();
                }

                /*Добавление*/
                case ("add") -> {
                    if (command.length() <= i++) {
                        throw new Exception ("ERROR: Вы не ввели имя задачи");
                    }
                    String textName = command.substring(i);
                    System.out.print("Input description Task: ");
                    String textDescription = in.nextLine();
                    Task task = managerTaskMap.addTask(textName,textDescription);
                    System.out.println("Add Task (id = "+task.getID() +"): " + task);
                }
                case ("addepic") -> {
                    if (command.length() <= i++) {
                        System.out.println("ERROR: Вы не ввели имя задачи");
                        break;
                    }
                    String textName = command.substring(i);
                    System.out.print("Input description Epic: ");
                    String textDescription = in.nextLine();
                    Task task = managerTaskMap.addEpic(textName,textDescription);
                    System.out.println("Add EpicTask (id = "+task.getID() +"): " + task);
                }
                case ("addsubtasktoid") -> {
                    if (command.length() <= i++) {
                        System.out.println("ERROR: вы не ввели id задачи");
                        break;
                    }
                    int idEpicTask = Integer.parseInt(command.substring(i));
                    if(!managerTaskMap.getListTask().get(idEpicTask).getTypeTask().equalsIgnoreCase("EPIC")){
                        System.out.println("ERROR: Задача с id " + idEpicTask + " не ЭПИК");
                        break;
                    }
                    System.out.print("Input Name Sub Task: ");
                    String textName =  in.nextLine();
                    System.out.print("Input Description Sub Task: ");
                    String textDescription = in.nextLine();
                    Task task = managerTaskMap.addSubTaskToEpicID(textName,textDescription, idEpicTask);
                    System.out.println("Add SubTask (id = "+task.getID() +"): " + task);
                }

                /*Изменение*/
                case ("deleteall") -> {
                    managerTaskMap.deleteALL();
                }
                case ("deleteid") -> {
                    if (command.length() <= i++) {
                        System.out.println("ERROR: вы не ввели id задачи");
                        break;
                    }
                    int idEpicTask = Integer.parseInt(command.substring(i));
                    Task task = managerTaskMap.deleteIDTask(idEpicTask);
                    System.out.println("ЗАПИСЬ: " + task.getTypeTask() + " " + task +  "УДАЛЕНА!");
                }
                case ("renameid") -> {
                    if (command.length() <= i++) {
                        System.out.println("ERROR: вы не ввели id задачи");
                        break;
                    }
                    int idEpicTask = Integer.parseInt(command.substring(i));
                    System.out.print("Input NewName Task: ");
                    String textName =  in.nextLine();
                    Task task = managerTaskMap.reNameToIDTask(idEpicTask, );

                    System.out.println("Команда  statfwefewusdownid не распознана!");
                }
                case ("redescid") -> {
                    System.out.println("Команда  statusdgewgownid не распознана!");
                }
                case ("statusupid") -> {
                    System.out.println("Команда  stdwqdtusdownid не распознана!");
                }
                case ("statusdownid") -> {
                    System.out.println("Команда  statuwdqdsdfweownid не распознана!");
                }

                default -> {
                    System.out.println("Команда не распознана!");
                }
            }

            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        while (!isExit);

    }

    public void help(){
        StringBuilder textHelp = new StringBuilder();
        textHelp.append("ДОСТУПНЫЕ КОМАНДЫ: \n");
        textHelp.append(" \"help\" - показать список команда\n");
        textHelp.append(" \"exit\" - завершить программу\n");
        textHelp.append(" \"save\" - сохранить все записи\n");

        textHelp.append("КОМАНДЫ ДЛЯ ОТОБРАЖЕНИЯ ЗАДАЧ: \n");
        textHelp.append(" \"printAll\" - показать все задачи\n");
        textHelp.append(" \"printEpic\" - показать только ЭПИКИ\n");
        textHelp.append(" \"printSubTask\" - показать только ПОДЗАДАЧИ \n");
        textHelp.append(" \"printTask\" - показать только ОБЫЧНЫЕ ЗАДАЧИ \n");
        textHelp.append(" \"printId 'NUMBER ID'\" - показать задачу по id\n");

        textHelp.append("КОМАНДЫ ДЛЯ ДОБАВЛЕНИЯ ЗАДАЧ: \n");
        textHelp.append(" \"add 'указать имя задач'\" - добавить обычную задачу\n");
        textHelp.append(" \"addEpic 'указать имя задач'\" - добавить задачу c подзадачами\n");
        textHelp.append(" \"addSubTaskToID 'NUMBER ID'\" - добавить подзадачу к Эпику с указанным ID\n");

        textHelp.append("УДАЛЕНИЕ И ИЗМЕНЕНИЕ ЗАДАЧ: \n");
        textHelp.append(" \"deleteAll\" - удалить все задачи\n");
        textHelp.append(" \"deleteID 'NUMBER ID'\" - удалить задачу с ID\n");
        textHelp.append(" \"reNameID 'NUMBER ID'\" – изменить имя задачи с ID\n");
        textHelp.append(" \"reDescID 'NUMBER ID'\" – изменить описание задачи с ID\n");
        textHelp.append(" \"StatusUpID 'NUMBER ID'\"  – повысит статус выполнения задачи с ID\n");
        textHelp.append(" \"StatusDownID 'NUMBER ID'\"  – понизить статус выполнения задачи с ID\n");

        System.out.println(textHelp);
    }



    /// /// /// ВЫВОД - ПРИНТ
    String myFormat = "%-4s %-8s %-12s %-12s %-25s";

    public void printID(String id) throws Exception {
        int idTask = Integer.parseInt(id);
        if (!managerTaskMap.getListTask().containsKey(idTask)){
            throw new Exception("ERROR: Задачи с индексом " + idTask + " не существует!" );
        }

        Task task = managerTaskMap.getListTask().get(idTask);
        StringBuilder consoleTable = new StringBuilder();
        consoleTable.append(String.format(myFormat, "ID", "TYPE", "STATUS", "LINK", "INFORMATION"));
        consoleTable.append("\n");
        consoleTable.append(String.format(myFormat, task.getID(), task.getTypeTask(), task.getTaskStatus(),
                task.getLinkStr(), task));

        if(task.getTypeTask().equalsIgnoreCase("EPIC")){
            for(SubTask subTask : ((EpicTask)task).getSubTasks()){
                consoleTable.append("\n");
                consoleTable.append(String.format(myFormat, subTask.getID(), subTask.getTypeTask(), subTask.getTaskStatus(),
                        subTask.getLinkStr(), subTask));
            }
        }
        System.out.println(consoleTable);
    }
    public void printTaskMap(){
        System.out.println(String.format(myFormat,"ID","TYPE","STATUS","LINK","INFORMATION"));
        Iterator<Map.Entry<Integer, Task>> iterator = managerTaskMap.getListTask().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();
            System.out.println(String.format(myFormat,
                    key,value.getTypeTask(),value.getTaskStatus(),
                    value.getLinkStr(),value));
        }
    }
    public void printTaskMap(String typeTask){
       // "EPIC" "TASK" "SubTASK"
        System.out.println(String.format(myFormat,"ID","TYPE","STATUS","LINK","INFORMATION"));
        for (Map.Entry<Integer, Task> entry : managerTaskMap.getListTask().entrySet()) {
            Task value = entry.getValue();
            if (typeTask.equalsIgnoreCase(value.getTypeTask())) {
                System.out.println(String.format(myFormat,
                        value.getID(), value.getTypeTask(), value.getTaskStatus(),
                        value.getLinkStr(), value));
            }
        }
    }
    public void printDebug() {
        String myFormatDebug = "%3s %4s %8s %12s %12s %s";
        System.out.println(String.format(myFormatDebug, "KEY","ID", "TYPE", "STATUS", "LINK", "INFORMATION"));
        Iterator<Map.Entry<Integer, Task>> iterator = managerTaskMap.getListTask().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();
            System.out.println(String.format(myFormatDebug,
                    key,value.getID(), value.getTypeTask(), value.getTaskStatus(),
                    value.getLinkStr(), value));
        }
    }



}
