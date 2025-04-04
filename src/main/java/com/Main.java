package com;


import com.ConsolView.ConsolViev;
import com.DateTask.SubTask;
import com.DateTask.Task;
import com.Сontroller.MemoryTask;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> listTask = MemoryTask.ReadTaskList();

        ConsolViev.run(listTask);

    }
}