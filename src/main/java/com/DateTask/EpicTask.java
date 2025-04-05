package com.DateTask;

import java.util.ArrayList;

public class EpicTask extends Task {
    ArrayList<SubTask> subTasks;
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

    @Override
    public String toString() {
        String text = "[EPIC id " + getID() + "; STATUS-" + taskStatus +
                " NAME: " + name + "; Description: " + description + "; " +
                " COUNT SubTask: " + subTasks.size() + "]";
        return text;
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
        StringBuilder link = new StringBuilder("[");
        for(SubTask subTask : subTasks){
            link.append(subTask.getID()).append(",");
        }
        link.append("]");
        return link.toString();


    }
}

