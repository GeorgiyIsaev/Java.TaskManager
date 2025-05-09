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
    public List<Task> getHistory() {
        return Collections.unmodifiableList(historyList);
    }
}
