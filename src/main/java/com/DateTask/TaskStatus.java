package com.DateTask;

public enum TaskStatus {
    NEW("NEW"), IN_PROGRESS("IN_PROG"),DONE ("DONE");

    private final String name;
    TaskStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    public static TaskStatus toTaskStatus(String taskStatus){
        if(taskStatus.equalsIgnoreCase("IN_PROGRESS")
                ||taskStatus.equalsIgnoreCase("IN_PROG")){
            return TaskStatus.IN_PROGRESS;
        }
        else if(taskStatus.equalsIgnoreCase("DONE")){
            return TaskStatus.DONE;
        }
        return TaskStatus.NEW;
    }
}
