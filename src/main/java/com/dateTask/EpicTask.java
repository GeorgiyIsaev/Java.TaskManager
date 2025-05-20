package com.dateTask;

import java.util.ArrayList;

public class EpicTask extends Task {
    private ArrayList<SubTask> subTasks;

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public EpicTask(Integer id, String name, String description){
       super(id, name, description);
        subTasks = new ArrayList<>();

    }

    public void addSubTask(SubTask sub){
        subTasks.add(sub);
    }
    public void deleteSubTask(SubTask subTask){
        int i = subTasks.indexOf(subTask);
        subTasks.remove(subTask);

    }


    @Override
    public void setStatus(TaskStatus status) {
    }
    public void updateTaskStatus(){
        status = TaskStatus.NEW;
        int count = 0;
        for (SubTask sub : subTasks){
            if (sub.getStatus() == TaskStatus.IN_PROGRESS) {
                status = TaskStatus.IN_PROGRESS;

            }else if (sub.getStatus() == TaskStatus.DONE){
                status = TaskStatus.IN_PROGRESS;
                count++;
            }
        }
        if (count == subTasks.size()) {
            status = TaskStatus.DONE;
        }
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
    public String getTypeTask(){
        return CONST.EPIC_NAME;
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

