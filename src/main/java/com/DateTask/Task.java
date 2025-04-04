package com.DateTask;

public class Task extends IdTask{
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
        return " " + taskStatus + "; TASK: " + name + ";";
    }
}
