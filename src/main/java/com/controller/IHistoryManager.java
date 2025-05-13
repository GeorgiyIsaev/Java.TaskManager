package com.controller;

import com.dateTask.Task;

import java.util.List;

public interface IHistoryManager {
    public List<Task> getHistory();
    public void add(Task task);
    public void remove(int id);
}
