package com.ConsolView;

import com.DateTask.EpicTask;
import com.DateTask.Task;
import com.Сontroller.MemoryTask;

import java.util.*;

public  class ConsolViev {

    public static void run(HashMap<Integer,Task> listTask){
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        do{
            System.out.print("Input command: ");
            String command = in.nextLine();

            String inputCommand;
           // String text;
            int i = command.indexOf(' ');
            if (i > 0) {
                 inputCommand = command.substring(0, i).toLowerCase();
                 //text = command.substring(i);
            }
            else inputCommand = command.toLowerCase();

            switch (inputCommand){
                case("exit") -> {
                    isExit = true;
                }
                case("help") -> {
                    help();
                }
                case("printall") -> {
                    printTaskList(listTask);
                }
                case("deleteall") -> {
                    listTask = new HashMap<Integer,Task> ();
                }

                case("save") -> {
                    MemoryTask.WriteTaskList(listTask);
                }

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
                case("printId") -> {


                }
                case("") -> {


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
        textHelp.append("Доступные команды: \n");
        textHelp.append(" \"help\" - показать список команда\n");
        textHelp.append(" \"exit\" - завершить программу\n");
        textHelp.append(" \"save\" - сохранить все записи\n");
        textHelp.append(" \"printAll\" - показать все задачи\n");
        textHelp.append(" \"deleteAll\" - удалить все задачи\n");

        textHelp.append(" \"printId 'NUMBER' \" - показать задачу по id\n");

        textHelp.append(" \"add 'указать имя задач'\" - добавить задачу\n");
        textHelp.append(" \"addEpic 'указать имя задач'\" - добавить задачу c подзадачами\n");

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

    public static void printID(HashMap<Integer,Task> listTask, String id){


       System.out.println(listTask);
    }


    public static void printList(HashMap<Integer, Task> listTask){
        Iterator<Map.Entry<Integer, Task>> iterator = listTask.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }

    public static void printListPlus(HashMap<Integer, Task> listTask){
        Iterator<Map.Entry<Integer, Task>> iterator = listTask.entrySet().iterator();
        String t = "ЛУН";
        System.out.println(String.format("name: %s",t));
        while (iterator.hasNext()) {
            Map.Entry<Integer, Task> entry = iterator.next();
            Integer key = entry.getKey();
            Task value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }

}
