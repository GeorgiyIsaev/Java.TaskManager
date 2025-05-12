package com.dateTask;

import java.io.Serializable;

public class Task implements Serializable {
    private final int id;
    public int getID(){
        return id;
    }
    protected String name;
    protected String description;
    protected TaskStatus taskStatus;
    public Task(int id,  String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.taskStatus = TaskStatus.NEW;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
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
    public String fullInfo(){
        return "[TASK id " + getID() + "; STATUS-" +
                taskStatus + " NAME: " + name +
                "; Description: " + description + ";]";

    }
    public String getTypeTask(){
        return CONST.TASK_NAME;
    }
    public String getLinkStr(){
        return CONST.NO_REFERENCE;
    }


}
