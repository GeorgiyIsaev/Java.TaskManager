package com.dateTask;

public class CONST {
    private CONST(){}
    public static String EPIC_NAME = "EPIC";
    public static String TASK_NAME = "TASK";
    public static String SUB_NAME = "SUB";

    public static String NO_REFERENCE = "-";


    public static String FORMAT_PRINT = "%-4s %-8s %-5s %-12s %-25s";
    public static String CONSOLE_TITLE = String.format(FORMAT_PRINT, "ID", "STATUS", "TYPE",  "LINK", "INFORMATION");

    public static String getTaskString(String id, String type, String status, String link, String info){
        return  String.format(FORMAT_PRINT, id, status, type, link, info);
    }
    public static String getTaskString(int id, String type, TaskStatus status, String link, String info){
      return  getTaskString("" + id, type,status.toString(), link, info);
    }

    public static String getTaskString(Task task){
        return  getTaskString(task.getID(), task.getTypeTask(), task.getTaskStatus(),task.getLinkStr(), task.toString());
    }

}
