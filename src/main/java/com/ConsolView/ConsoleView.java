package com.ConsolView;

import com.Controller.ChangeTaskMap;
import com.Controller.MemoryTask;
import com.DateTask.Task;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleView {
    Scanner in;
    HashMap<Integer, Task> listTask;

    public ConsoleView(HashMap<Integer, Task> listTask){
        this.listTask =listTask;
        in = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Добро пожаловать в TaskManager!");
        System.out.println("У вас в работе "+ listTask.size() +" задач.");
        System.out.println("Введите help что бы отобразить доступные команды.");

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
                    System.out.println("Команда  statusdownid не распознана!");
                }

                /*Отображение*/
                case("printall") -> {
                    System.out.println("Команда  statusdownid не распознана!");
                }
                case("printtask") -> {
                    System.out.println("Команда  statnjusdownid не распознана!");
                }
                case("printepic") -> {
                    System.out.println("Команда  statjusdownid не распознана!");
                }
                case("printsubtask") -> {
                    System.out.println("Команда  statjjusdownid не распознана!");
                }
                case("printid") -> {
                    System.out.println("Команда  statujjsdownid не распознана!");
                }
                case("printdebug") -> {
                    System.out.println("Команда  statusdow[nid не распознана!");
                }

                /*Добавление*/
                case("add") -> {
                    System.out.println("Команда  statusdgergownid не распознана!");

                }
                case("addepic") -> {
                    System.out.println("Команда  statusdreownid не распознана!");
                }
                case("addsubtasktoid") -> {
                    System.out.println("Команда  statusjrdownid не распознана!");
                }

                /*Изменение*/
                case("deleteall") -> {
                    System.out.println("Команда  statukydownid не распознана!");
                }
                case("deleteid") -> {
                    System.out.println("Команда  statusdktkownid не распознана!");
                }
                case("renameid") -> {
                    System.out.println("Команда  statfwefewusdownid не распознана!");
                }
                case("redescid") -> {
                    System.out.println("Команда  statusdgewgownid не распознана!");
                }
                case("statusupid") -> {
                    System.out.println("Команда  stdwqdtusdownid не распознана!");
                }
                case("statusdownid") -> {
                    System.out.println("Команда  statuwdqdsdfweownid не распознана!");
                }

                default -> {
                    System.out.println("Команда не распознана!");
                }
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
}
