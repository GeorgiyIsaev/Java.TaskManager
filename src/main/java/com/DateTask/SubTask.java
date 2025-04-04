package com.DateTask;

public class SubTask extends Task{
    EpicTask refrains;
    public SubTask(EpicTask refrains) {
        this.refrains = refrains;
    }
    public SubTask(String name, String description) {
        this.name = name;
        this.description = description;
        this.taskStatus = TaskStatus.NEW;
        this.refrains = null;
    }

    public SubTask(String name, String description, EpicTask refrains) {
        this(name, description);
        this.refrains = refrains;
    }
}
