package com.dateTask;

import java.io.Serializable;

public class Task implements Serializable {
    static public final String NO_REFERENCE = "-";
    private final int id;
    private String name;
    private String description;
    private TaskStatus status;
    private TaskType taskType;

    public Task(String name, String description) {
        this.id = CreateID.INSTANCE.createID();
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW;
        taskType = TaskType.TASK;
    }

    public Task(int id,  String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW;
        taskType = TaskType.TASK;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getID(){
        return id;
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

    public String getTypeTask(){
        return taskType.name();
    }
    protected void setTypeTask(TaskType taskType){
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return getName() +
                " {" + getDescription() + "}";

    }
    public boolean findID(int id){
        return this.id == id;
    }

    public String getLinkStr(){
        return NO_REFERENCE;
    }
}
