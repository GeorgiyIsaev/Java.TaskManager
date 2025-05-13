package com.dateTask;

public class SubTask extends Task{
    private EpicTask refrains;

    public EpicTask getRefrains() {
        return refrains;
    }
//    public SubTask(Integer id, String name, String description) {
//        super(id,name,description);
//        this.refrains = null;
//    }
    public SubTask(Integer id, String name, String description, EpicTask refrains) {
        super(id,name,description);
        this.refrains = refrains;
    }
    @Override
    public void setTaskStatus(TaskStatus taskStatus) {
       this.taskStatus = taskStatus;
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
