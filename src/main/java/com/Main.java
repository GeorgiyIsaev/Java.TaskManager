package com;


import com.DateTask.SubTask;
import com.DateTask.Task;

public class Main {
    public static void main(String[] args) {



        Task task = new Task();
        System.out.println(task.getID());
        Task task2 = new SubTask("Задача 1", "Мое описание");
        System.out.println(task2.getID());

    }
}