package com.consoleView;

public class MyCommand {
    private String command;
    public MyCommand(String command){
        this.command = command;
    }


    public String baseCommand(){
        String inputCommand;
        int i = command.indexOf(' ');
        if (i > 0) {
            inputCommand = command.substring(0, i).toLowerCase();
        } else inputCommand = command.toLowerCase();
        return inputCommand;
    }
    public String secondCommand(){
        int idSeparator =  command.indexOf(' ');
        if (command.length() <= idSeparator++) {
            return null;
        }
        return command.substring(idSeparator);
    }
    public String thirdCommand(){
        command = secondCommand();
        return secondCommand();
    }
    public Integer getID(){
        String[] sp = command.split(" ");
        if (sp.length < 1){
            return null;
        }
        Integer i;
        try {
            i = Integer.parseInt(sp[1]);
        } catch (NumberFormatException e) {
            i = null;
        }
        return i;
    }
}
