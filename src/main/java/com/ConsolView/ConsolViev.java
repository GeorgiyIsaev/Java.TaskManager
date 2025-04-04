package com.ConsolView;

import com.DateTask.Task;

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
                 inputCommand = command.substring(0, i);
                 //text = command.substring(i);
            }
            else inputCommand = command;

            switch (inputCommand.toLowerCase()){
                case("end") -> {
                    isExit = true;
                }
                case("help") -> {
                    help();
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
        textHelp.append(" \"add 'указать имя задач'\" - добавить задачу\n");
        textHelp.append(" \"printAll\" - показать все задачи\n");
        textHelp.append(" \"end\" - завершить программу\n");

        System.out.println(textHelp);
    }
}
