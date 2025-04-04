package com.DateTask;

public class SubTask extends Task{
    EpicTask refrains;

    public SubTask(String name, String description) {
        super(name,description);
        this.refrains = null;
    }


    public SubTask(String name, String description, EpicTask refrains) {
        super(name,description);
        this.refrains = refrains;
    }

    @Override
    public String toString() {
        return "[SubT id " + getID() + "; STATUS-" +
                taskStatus + " NAME: " + name +
                "; Description: " + description + ";]";

    }
}
