package com.consoleView;

import com.dateTask.Task;
import com.dateTask.TaskStatus;

public final class ConsoleUtils {
    private ConsoleUtils(){} //нет конструктора

    public static String FORMAT_PRINT = "%-4s %-8s %-5s %-12s %-25s";
    public static String CONSOLE_TITLE = String.format(FORMAT_PRINT, "ID", "STATUS", "TYPE",  "LINK", "INFORMATION");

    public static String getTaskString(String id, String type, String status, String link, String info){
        return  String.format(FORMAT_PRINT, id, status, type, link, info);
    }
    public static String getTaskString(int id, String type, TaskStatus status, String link, String info){
        return  getTaskString("" + id, type,status.toString(), link, info);
    }
    public static String getTaskString(Task task){
        return  getTaskString(task.getID(), task.getTypeTask(), task.getStatus(),task.getLinkStr(), task.toString());
    }



}
