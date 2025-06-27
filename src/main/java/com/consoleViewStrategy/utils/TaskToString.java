package com.consoleViewStrategy.utils;

import com.dateTask.Task;
import com.dateTask.TaskStatus;

public final class TaskToString {
    private TaskToString(){} //нет конструктора

    public static String FORMAT_PRINT = "%-4s %-8s %-5s %-12s %-25s";
    public static String CONSOLE_TITLE = String.format(FORMAT_PRINT, "ID", "STATUS", "TYPE",  "LINK", "INFORMATION");

    public static String transform(String id, String type, String status, String link, String info){
        return  String.format(FORMAT_PRINT, id, status, type, link, info);
    }
    public static String transform(int id, String type, TaskStatus status, String link, String info){
        return  transform("" + id, type,status.toString(), link, info);
    }
    public static String transform(Task task){
        return  transform(task.getID(), task.getTypeTask(), task.getStatus(),task.getLinkStr(), task.toString());
    }



}
