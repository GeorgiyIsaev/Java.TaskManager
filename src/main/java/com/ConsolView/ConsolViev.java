package com.ConsolView;

import com.Controller.ChangeTaskMap;
import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.Task;
import com.Controller.MemoryTask;

import java.util.*;

public  class ConsolViev {

    public static void run(HashMap<Integer,Task> listTask){
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        do{
            System.out.print("Input command: ");
            String command = in.nextLine();

            String inputCommand;
            int i = command.indexOf(' ');
            if (i > 0) {
                 inputCommand = command.substring(0, i).toLowerCase();
            }
            else inputCommand = command.toLowerCase();

            switch (inputCommand){
                case("exit") -> {
                    isExit = true;
                }
                case("help") -> {
                    help();
                }
                case("save") -> {
                    MemoryTask.WriteTaskList(listTask);
                }

                /*Отображение*/
                case("printall") -> {
                    printList(listTask);
                }
                case("printtask") -> {
                    printList(listTask, "TASK");
                }
                case("printepic") -> {
                    printList(listTask, "EPIC");
                }
                case("printsubtask") -> {
                    printList(listTask, "SubTASK");
                }
                case("printid") -> {
                    if(command.length() <= i++){
                        System.out.println("Команда не распознана!");
                        break;
                    }
                    printID(listTask, command.substring(i));


                    System.out.println(printID(listTask, command.substring(i)));
                }
                case("printdebug") -> {
                    printListDebug(listTask);
                }




                /*Добавление*/
                case("add") -> {
                    String textName = command.substring(i);
                    System.out.print("Input description Task: ");
                    String textDescription = in.nextLine();
                    Task task =  new Task(textName,textDescription);
                    listTask.put(task.getID(),task);

                }
                case("addepic") -> {
                    String textName = command.substring(i);
                    addEpicTask(listTask, textName, in);
                }
                case("addsubtasktoid") -> {
                    if(command.length() <= i++){
                        System.out.println("Команда не распознана!");
                        break;
                    }
                    System.out.println(addSubTaskToEpic(listTask, command.substring(i), in)); ////***/
                }

                /*Изменение*/
                case("deleteall") -> {
                    listTask = new HashMap<Integer,Task> ();
                }
                case("deleteid") -> {
                    if(command.length() <= i++){
                        System.out.println("Команда не распознана!");
                        break;
                    }
                    String textName = command.substring(i);
                    String result =  deleteID(listTask, textName);
                    System.out.println(result);
                }
                case("renameid") -> {
                    if(command.length() <= i++){
                        System.out.println("Команда не распознана!");
                        break;
                    }
                    int idTask =  StringToIntID(command.substring(i));
                    if (!listTask.containsKey(idTask)) {
                        System.out.println("Этого индекса нет в списке");
                        break;
                    }
                    System.out.print("Input NewName Task: ");
                    String newName = in.nextLine();
                    String result =  ChangeTaskMap.reNameTaskToID(listTask, idTask, newName);
                    System.out.println(result);
                }
                case("redescid") -> {
                    if(command.length() <= i++){
                        System.out.println("Команда не распознана!");
                        break;
                    }
                    int idTask =  StringToIntID(command.substring(i));
                    if (!listTask.containsKey(idTask)) {
                        System.out.println("Этого индекса нет в списке");
                        break;
                    }
                    System.out.print("Input NewDescription Task: ");
                    String newName = in.nextLine();
                    String result =  ChangeTaskMap.reDescTaskToID(listTask, idTask, newName);
                    System.out.println(result);
                }

                case("restatusid") -> {
                    System.out.println("Функция restatusid  пока нет"); ////***/
                }

                default -> {
                    System.out.println("Команда не распознана!");
                }
            }




        }
        while (!isExit);

    }

    public static void help(){
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
        textHelp.append(" \"reStatusID 'NUMBER ID' (NEW, PROG, DONE)\" – изменить статус задачи с ID\n");


        System.out.println(textHelp);
    }

    public static void printTaskList(HashMap<Integer,Task> listTask){
        printList(listTask);
    }

    public static void addEpicTask(HashMap<Integer,Task> listTask, String textName, Scanner in){
        System.out.print("Input description Epic Task: ");
        String textDescription = in.nextLine();

        EpicTask epicTask = new EpicTask(textName,textDescription);
        String console = "";
        int count = 1;
        System.out.print("Input Name "+ count + " SubTask: ");
        console = in.nextLine();
        do {
            System.out.print("Input Description "+ count + " SubTask: ");
            textDescription = in.nextLine();
            epicTask.addSubTask(console, textDescription);
            System.out.print("Input Name "+ count + "SubTask or Input 'END' to complete: ");
            console = in.nextLine();
            count++;
        }while(!console.equalsIgnoreCase("end"));

        listTask.put(epicTask.getID(),epicTask);
    }
    public static String addSubTaskToEpic(HashMap<Integer,Task> listTask, String textConsole, Scanner in){
        int idTask = StringToIntID(textConsole);
        if (!listTask.containsKey(idTask))
            return "Этого индекса нет в списке";
        if (!listTask.containsKey(idTask))
            return "Этого индекса нет в списке";
        if(!listTask.get(idTask).getTypeTask().equalsIgnoreCase("EPIC"))
            return "Задача с ID: " + idTask + " не является ЭПИКОМ, добавление подзадачи не возможно!";

        System.out.print("Input Name SubTask: ");
        String name = in.nextLine();
        System.out.print("Input Description SubTask: ");
        String description = in.nextLine();
        return ChangeTaskMap.addSubTaskToEpic(listTask, idTask, name, description);
    }


    static String myFormat = "%-4s %-8s %-12s %-12s %-25s";
    public static String printID(HashMap<Integer,Task> listTask, String id){

        int idTask = StringToIntID(id);
        if (!listTask.containsKey(idTask))
            return "Этого индекса нет в списке";
        Task task = listTask.get(idTask);

        String consoleTable = "";
        consoleTable += String.format(myFormat,"ID","TYPE","STATUS","LINK","INFORMATION");
        consoleTable += "\n";
        consoleTable += String.format(myFormat, task.getID(), task.getTypeTask(), task.getTaskStatus(),
                task.getLinkStr(), task);

        if(task.getTypeTask().equalsIgnoreCase("EPIC")){
            for(SubTask subTask : ((EpicTask)task).getSubTasks()){
                consoleTable += "\n";
                consoleTable += String.format(myFormat, subTask.getID(), subTask.getTypeTask(), subTask.getTaskStatus(),
                        subTask.getLinkStr(), subTask);
            }
        }

        return consoleTable;
    }


    public static void printList(HashMap<Integer, Task> listTask){
       // String myFormat = "%3s %4s %8s %12s %12s %25s";
        System.out.println(String.format(myFormat,"ID","TYPE","STATUS","LINK","INFORMATION"));

        Iterator<Map.Entry<Integer, Task>> iterator = listTask.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();
            System.out.println(String.format(myFormat,
                    key,value.getTypeTask(),value.getTaskStatus(),
                    value.getLinkStr(),value));

        }
    }

    public static void printList(HashMap<Integer, Task> listTask, String type){
       // "EPIC" "TASK" "SubTASK"

       // String myFormat = "%3s %4s %8s %12s %12s %25s";
        System.out.println(String.format(myFormat,"ID","TYPE","STATUS","LINK","INFORMATION"));

        Iterator<Map.Entry<Integer, Task>> iterator = listTask.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();

            if(type.equalsIgnoreCase(value.getTypeTask())) {
                System.out.println(String.format(myFormat,
                        value.getID(), value.getTypeTask(), value.getTaskStatus(),
                        value.getLinkStr(), value));
            }
        }
    }
    public static void printListDebug(HashMap<Integer, Task> listTask){
        String myFormatDebug = "%3s %4s %8s %12s %12s %s";
        System.out.println(String.format(myFormatDebug,"KEY","ID","TYPE","STATUS","LINK","INFORMATION"));

        Iterator<Map.Entry<Integer, Task>> iterator = listTask.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();
            System.out.println(String.format(myFormatDebug,
                    key,value.getID(),value.getTypeTask(),value.getTaskStatus(),
                    value.getLinkStr(),value));

        }
    }

    public static String deleteID(HashMap<Integer,Task> listTask, String id){
        int i = StringToIntID(id);
        if (i<1) return "Неверно указан id объекта!";

        return    ChangeTaskMap.deleteID(listTask, i);
    }

    public static int StringToIntID(String id){
        int i;
        try {
            i = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            i = -1;
        }
        return i;
    }

}
