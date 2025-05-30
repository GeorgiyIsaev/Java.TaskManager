package com.dateTask;

import java.io.Serializable;

public class Task implements Serializable {
    private final int id;
    public int getID(){
        return id;
    }
    protected String name;
    protected String description;
    protected TaskStatus status;
    public Task(int id,  String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return name +
                " {" + description + "}";

    }
    public boolean findID(int id){
        return this.id == id;
    }
    public String getTypeTask(){
        return TypeTask.TASK_NAME;
    }
    public String getLinkStr(){
        return TypeTask.NO_REFERENCE;
    }
}
