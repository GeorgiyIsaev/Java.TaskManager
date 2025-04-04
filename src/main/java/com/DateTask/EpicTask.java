package com.DateTask;

import java.util.ArrayList;

public class EpicTask extends Task {
    ArrayList<Task> task;
    public EpicTask(){
        task = new ArrayList<>();
        task.add(new SubTask(this));

    }
}

