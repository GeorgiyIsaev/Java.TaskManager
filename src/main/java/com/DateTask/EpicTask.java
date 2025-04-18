package com.DateTask;

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
    public void addSubTask(Integer id, String name, String description){
        subTasks.add(new SubTask (id, name, description));
    }
    public void addSubTask(SubTask sub){
        subTasks.add(sub);
    }
    public void deleteSubTask(SubTask subTask){
        int i = subTasks.indexOf(subTask);
        subTasks.remove(subTask);

    }


    @Override
    public void setTaskStatus(TaskStatus taskStatus) {
    }
    public void updateTaskStatus(){
        taskStatus = TaskStatus.NEW;
        int count = 0;
        for (SubTask sub : subTasks){
            if (sub.getTaskStatus() == TaskStatus.IN_PROGRESS) {
                taskStatus = TaskStatus.IN_PROGRESS;

            }else if (sub.getTaskStatus() == TaskStatus.DONE){
                taskStatus = TaskStatus.IN_PROGRESS;
                count++;
            }
        }
        if (count == subTasks.size()) {
            taskStatus = TaskStatus.DONE;
        }
    }





    @Override
    public boolean statusUp() {
        return false;
    }
    @Override
    public boolean statusDown() {
        return false;
    }




    public boolean findID(int id){
        if(this.getID() == id)
             return true;
        else{
            for(Task task : subTasks){
                if(task.findID(id)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String fullInfo(){

        StringBuilder text = new StringBuilder("[EPIC id " + getID() + "; STATUS-" +
                taskStatus + " NAME: " + name +
                "; Description: " + description + "; toEPIC_ID: " + +subTasks.size() + "]\n");
        for(SubTask subTask : subTasks){
            text.append(subTask).append("\n");
        }
        return text.toString();

    }

    @Override
    public String getTypeTask(){
        return "EPIC";
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

