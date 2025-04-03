package com.DateTask;

public class Task extends IdTask{
    private String name;
    private String description;
    private TaskStatus status;
    MultiTask refrains;

    public Task(MultiTask refrains) {
        this.refrains = refrains;
    }


}
