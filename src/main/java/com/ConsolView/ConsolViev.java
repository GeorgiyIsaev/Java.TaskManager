package com.ConsolView;

import com.DateTask.EpicTask;
import com.DateTask.Task;
import com.Сontroller.MemoryTask;

import java.util.ArrayList;
import java.util.Scanner;

public  class ConsolViev {

    public static void run(ArrayList<Task> listTask){
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
                    listTask = new ArrayList<>();
                }

                case("save") -> {
                    MemoryTask.WriteTaskList(listTask);
                }

                case("add") -> {
                    String textName = command.substring(i);
                    System.out.print("Input description Task: ");
                    String textDescription = in.nextLine();
                    listTask.add(new Task(textName,textDescription));
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

    public static void printTaskList(ArrayList<Task> listTask){
        System.out.println(listTask);
    }

    public static void addEpicTask(ArrayList<Task> listTask, String textName, Scanner in){
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
        listTask.add(epicTask);
    }

    public static void printID(ArrayList<Task> listTask, String id){
        int idTask = -1;
        int findIdList = -1;
        try {
              idTask = Integer.parseInt(id);
              for (Task task : listTask){
                  if (task.findID(idTask)){
                      //
                  }
              }

        }
        catch (NumberFormatException e) {
            idTask = -1;
        }

       // listTask.get(0)

       System.out.println(listTask);
    }
}
