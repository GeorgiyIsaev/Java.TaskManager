package com.consoleView;

import java.util.Scanner;

public class MyCommand {
    private String command;
 ///   private String nextCommand;
  ///  String[] splitCommand;

    public String getCommand() {
        return command;
    }


    //private String firstCommand;
   // private String secondCommand;
  //  private String thirdCommand;

  //  private String command;
    private Scanner scanner;


//    public MyCommand(String command){
//        this.scanner = new Scanner(System.in);
//   //     this.command = command;
//    }

   public MyCommand(){
       this.scanner = new Scanner(System.in);
  }
    public void input(String title){
        System.out.print(title);
        command = scanner.nextLine();
     //   nextCommand = command;
    //    this.splitCommand = command.split(" ");
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
    //public String secondCommand(){
//
//    //}
//   /// public String thirdCommand(){
//        int THIRD_COMMAND = 1;
//        if(splitCommand.length < THIRD_COMMAND){
//            return "";
//        }
//        nextCommand();
//        return splitCommand[THIRD_COMMAND];
//    //}
    public void nextCommand(){
        int idSpase = command.indexOf(' ')+1;
        if(idSpase < 1) {
            command =  "";
        }
        command = command.substring(idSpase);
    }




    public Integer getID(){
       // int ID_COMMAND = 1;
     //   if(splitCommand.length < ID_COMMAND){
       //     return null;
      //  }
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
