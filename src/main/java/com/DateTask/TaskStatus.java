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



}
