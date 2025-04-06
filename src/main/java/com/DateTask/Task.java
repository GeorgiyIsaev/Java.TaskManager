package com.DateTask;

import java.io.Serializable;

public class Task extends IdTask  implements Serializable {
    protected String name;
    protected String description;
    protected TaskStatus taskStatus;


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
        if(this.taskStatus == TaskStatus.IN_PROGRESS)
            this.taskStatus =TaskStatus.DONE;
        return true;
    }
    public boolean statusDown() {
        if(this.taskStatus == TaskStatus.IN_PROGRESS)
            this.taskStatus =TaskStatus.NEW;
        if(this.taskStatus == TaskStatus.DONE)
            this.taskStatus =TaskStatus.IN_PROGRESS;
        else return false;
        return true;
    }



    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.taskStatus = TaskStatus.NEW;
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
