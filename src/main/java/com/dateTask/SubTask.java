package com.dateTask;

public class SubTask extends Task{
    private EpicTask refrains;

    public EpicTask getRefrains() {
        return refrains;
    }

    public SubTask(String name, String description, EpicTask refrains) {
        super(name,description);
        this.refrains = refrains;
        this.setTypeTask(TaskType.SUBTASK);
    }
//    public SubTask(Integer id, String name, String description, EpicTask refrains) {
//        super(id,name,description);
//        this.refrains = refrains;
//    }
    @Override
    public void setStatus(TaskStatus status) {
       super.setStatus(status);
       refrains.updateTaskStatus();
    }
    @Override
    public String getLinkStr(){
        return "" + this.refrains.getID();
    }
}
