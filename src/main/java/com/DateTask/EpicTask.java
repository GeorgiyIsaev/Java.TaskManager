package com.DateTask;

import java.util.ArrayList;

public class EpicTask extends Task {
    ArrayList<SubTask> subTasks;

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public EpicTask(String name, String description){
       super(name, description);
        subTasks = new ArrayList<>();

    }
    public void addSubTask(String name, String description){
        subTasks.add(new SubTask (name, description));
    }
    public void addSubTask(SubTask sub){
        subTasks.add(sub);
    }
    public void deleteSubTask(SubTask subTask){
        int i = subTasks.indexOf(subTask);
        subTasks.remove(subTask);

    }





    public boolean findID(int id){
        if(this.id == id)
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

