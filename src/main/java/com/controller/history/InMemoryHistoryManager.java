package com.controller.history;

import com.dateTask.Task;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history;

    public InMemoryHistoryManager(){
        this.history = new LinkedList<>();
    }


    @Override
    public void add(Task task) {
        Task taskFind = getTask(task.getID());
        if(taskFind != null){
            history.remove(taskFind);
        }
        if (history.size() >=10){
            history.remove(0);
        }
        history.add(task);
    }

    @Override
    public void remove(int id) {
        Task task = getTask(id);
        if(task != null) {
            history.remove(task);
        }
    }

    @Override
    public void removeAll() {
        this.history.clear();
    }

    public Task getTask(int id){
        Task task= null;
        for (Task t : history) {
            if (t.getID() == id) {
                task = t;
                break;
            }
        }
        return task;
    }

    @Override
    public List<Task> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
