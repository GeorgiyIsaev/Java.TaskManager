package com;


import com.ConsolView.ConsolViev;
import com.DateTask.SubTask;
import com.DateTask.Task;
import com.Controller.MemoryTask;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer,Task> listTask = MemoryTask.ReadTaskList();
        ConsolViev.run(listTask);

    }
}