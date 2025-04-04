package com.DateTask;

import java.util.ArrayList;

public class EpicTask extends Task {
    ArrayList<Task> task;
    public EpicTask(String name, String description){
       super(name, description);
        task = new ArrayList<>();
      //  task.add(new SubTask(this));

    }
    public void addSubTask(String name, String description){
        task.add(new SubTask (name, description));
    }
}

