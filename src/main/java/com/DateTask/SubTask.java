package com.DateTask;

public class SubTask extends Task{
    EpicTask refrains;

    public EpicTask getRefrains() {
        return refrains;
    }

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
                "; Description: " + description + "; toEPIC_ID: " + refrains.getID() + "]";

    }
    public String fullInfo(){
        return "[SubT id " + getID() + "; STATUS-" +
                taskStatus + " NAME: " + name +
                "; Description: " + description + "; toEPIC_ID: " + refrains.getID() + "]";

    }

    @Override
    public String getTypeTask(){
        return "SubTASK";
    }
    @Override
    public String getLinkStr(){
        return this.refrains.getID().toString();
    }
}
