package com.DateTask;

import java.io.Serializable;

public class Task extends IdTask  implements Serializable {
    protected String name;
    protected String description;
    protected TaskStatus taskStatus;


    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.taskStatus = TaskStatus.NEW;
    }

    @Override
    public String toString() {
        return " " + taskStatus + "; TASK id" + getID() +": " + name + "; " + description +";";
    }
}
