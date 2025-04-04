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
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("[EPIC id ").append(getID()).append("; STATUS-")
                .append(taskStatus).append(" NAME: ").append(name)
                .append("; Description: ").append(description).append(";]\n");
        for(Task task : subTasks){
            text.append("  ").append(task);
            if (task.getID() != subTasks.get(subTasks.size() - 1).getID()){
                text.append("\n");
            }
        }


        return text.toString();
    }
}

