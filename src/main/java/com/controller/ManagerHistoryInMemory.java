package com.controller;

import com.dateTask.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerHistoryInMemory implements IHistoryManager{
    private List<Task> historyList;

    public ManagerHistoryInMemory(){
        this.historyList = new ArrayList<>();
    }


    @Override
    public void add(Task task) {
        if (historyList.size() >=10){
            historyList.remove(0);
        }
        historyList.add(task);
    }

    @Override
    public void remove(int id) {
        Task task = getTask(id);
        if(task != null) {
            historyList.remove(task);
        }
    }
    public Task getTask(int id){
        Task task= null;
        for (Task t : historyList) {
            if (t.getID() == id) {
                task = t;
                break;
            }
        }
        return task;
    }

    @Override
    public List<Task> getHistory() {
        return Collections.unmodifiableList(historyList);
    }
}
