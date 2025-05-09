package com.dateTask;

import java.io.Serializable;

public class Task implements Serializable {
    private Integer id;
    public Integer getID(){
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


    public boolean statusUp() {
        if(this.taskStatus == TaskStatus.NEW)
            this.taskStatus =TaskStatus.IN_PROGRESS;
        else if(this.taskStatus == TaskStatus.IN_PROGRESS)
            this.taskStatus =TaskStatus.DONE;
        else return false;
        return true;
    }
    public boolean statusDown() {
        if(this.taskStatus == TaskStatus.IN_PROGRESS)
            this.taskStatus =TaskStatus.NEW;
        else if(this.taskStatus == TaskStatus.DONE)
            this.taskStatus =TaskStatus.IN_PROGRESS;
        else return false;
        return true;
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
        return "TASK";
    }
    public String getLinkStr(){
        return "-";
    }


}
