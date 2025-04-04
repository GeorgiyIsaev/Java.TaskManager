package com;


import com.DateTask.EpicTask;
import com.DateTask.SubTask;
import com.DateTask.Task;

import java.util.ArrayList;

public class MyTest {
    public static void main(String[] args) {
        ArrayList<Task> listTask = new ArrayList<>();
        listTask.add(new Task("Простая задач 1", "Описание 1"));
        listTask.add(new Task("Простая задач 2", "Описание 2"));
        listTask.add(new EpicTask("Сложная задач 2", "Сложное описание"));

        EpicTask epicTask = (EpicTask) listTask.get(listTask.size()-1);
        SubTask sub = new SubTask("СабЗадача 1", "дискрипт", epicTask);

        epicTask.addSubTask(sub);
        listTask.add(sub);
        System.out.println(listTask);


        ((SubTask) listTask.get(listTask.size()-1)).setName("Изменено");
        System.out.println("Новый список");
        System.out.println(listTask);
    }
}
