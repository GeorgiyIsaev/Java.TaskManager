package com.DateTask;

import java.util.ArrayList;
import java.util.List;

public class MultiTask {
    ArrayList<Task> task;
    public MultiTask(){
        task = new ArrayList<>();
        task.add(new Task(this));

    }
}
