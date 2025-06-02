package com.consoleView;

import java.util.Scanner;

public class MyCommand {
    private String command;
    public String getCommand() {
        return command;
    }
    private Scanner scanner;
    public MyCommand(){
       this.scanner = new Scanner(System.in);
    }
    public void input(String title){
        System.out.print(title);
        command = scanner.nextLine();
    }
    public void close(){
        this.scanner.close();
    }
    public String baseCommand(){
        int idSpase = command.indexOf(' ');
        if(idSpase < 0) {
            return command;
        }
        String baseCommand =  command.substring(0, idSpase);
        nextCommand();
        return baseCommand;
    }
    public void nextCommand(){
        int idSpase = command.indexOf(' ')+1;
        command = command.substring(idSpase);
    }

    public Integer getID(){
        Integer i;
        String IdString = baseCommand();
        try {
            i = Integer.parseInt(IdString);
        } catch (NumberFormatException e) {
            i = null;
        }
        return i;
    }
}
