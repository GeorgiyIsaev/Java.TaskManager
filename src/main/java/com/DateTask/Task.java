package com.DateTask;

public class Task extends IdTask{
    protected String name;
    protected String description;
    protected TaskStatus taskStatus;

    @Override
    public String toString() {
        return " " + taskStatus + "; TASK: " + name + ";";
    }
}
