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

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.taskStatus = TaskStatus.NEW;
    }

    @Override
    public String toString() {
        return "[" + taskStatus + "; TASK id" + getID() +": " + name + "; " + description +"];";
    }
    public boolean findID(int id){
        return this.id == id;
    }


}
