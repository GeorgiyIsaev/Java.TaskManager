package com.dateTask;

import java.util.ArrayList;

public class EpicTask extends Task {
    private ArrayList<SubTask> subTasks;

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }
    public EpicTask( String name, String description){
        super(name, description);
        this.subTasks = new ArrayList<>();
        this.setTypeTask(TaskType.EPIC);
    }
    public EpicTask(Integer id, String name, String description){
        super(id, name, description);
        subTasks = new ArrayList<>();
        this.setTypeTask(TaskType.EPIC);
    }

    public void replacementSameID(SubTask sub){
        if(!findID(sub.getID())) return;
        deleteSubTask(sub);
    }

    public void addSubTask(SubTask sub){
        replacementSameID(sub);
        subTasks.add(sub);
    }
    public void deleteSubTask(SubTask subTask){
        int i = subTasks.indexOf(subTask);
        subTasks.remove(subTask);

    }


    @Override
    public void setStatus(TaskStatus status) {
        //Изменений не происходит!
    }
    public void updateTaskStatus(){
        int count = 0;
        for (SubTask sub : subTasks){
            if (sub.getStatus() == TaskStatus.IN_PROGRESS) {
                this.setStatus(TaskStatus.IN_PROGRESS);
                return;

            }else if (sub.getStatus() == TaskStatus.DONE){
                this.setStatus(TaskStatus.IN_PROGRESS);
                count++;
            }
        }
        if (count == subTasks.size()) {
            this.setStatus(TaskStatus.DONE);
        }
        this.setStatus(TaskStatus.NEW);
    }

    public boolean findID(int idSubTask){
        if(this.getID() == idSubTask)
             return true;
        else{
            for(Task task : subTasks){
                if(task.findID(idSubTask)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getLinkStr(){
        ArrayList<Integer> arr = new ArrayList<>();
        for(SubTask subTask : subTasks){
            arr.add(subTask.getID());
        }
        return arr.toString();
    }
}

