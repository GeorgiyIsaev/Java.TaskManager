package com.DateTask;

public enum TaskStatus {
    NEW, IN_PROGRESS,DONE;

    @Override
    public String toString() {
        switch (this){
            case NEW -> {return "NEW";}
            case IN_PROGRESS -> {return "IN_PROGRESS";}
            case DONE -> {return "DONE";}
            default -> {return "NON";}
        }
    }
    public static TaskStatus toTaskStatus(String taskStatus){
        if(taskStatus.equalsIgnoreCase("IN_PROGRESS")){
            return TaskStatus.IN_PROGRESS;
        }
        else if(taskStatus.equalsIgnoreCase("DONE")){
            return TaskStatus.DONE;
        }
        return TaskStatus.NEW;
    }
}
