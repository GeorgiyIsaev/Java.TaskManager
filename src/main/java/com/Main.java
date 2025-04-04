package com;


import com.ConsolView.ConsolViev;
import com.DateTask.SubTask;
import com.DateTask.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {



        ArrayList<Task> listTask = new ArrayList<>();
        ConsolViev.run(listTask);

        Task task = new Task();
        System.out.println(task.getID());
        Task task2 = new SubTask("Задача 1", "Мое описание");
        System.out.println(task2.getID());
        System.out.println(task2.getID());

    }
}