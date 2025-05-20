package com.dateTask;

public class SubTask extends Task{
    private EpicTask refrains;

    public EpicTask getRefrains() {
        return refrains;
    }
    public SubTask(Integer id, String name, String description, EpicTask refrains) {
        super(id,name,description);
        this.refrains = refrains;
    }
    @Override
    public void setStatus(TaskStatus status) {
       this.status = status;
       refrains.updateTaskStatus();
    }




    @Override
    public String getTypeTask(){
        return CONST.SUB_NAME;
    }
    @Override
    public String getLinkStr(){
        return "" + this.refrains.getID();
    }
}
