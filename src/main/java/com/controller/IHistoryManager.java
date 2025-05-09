package com.controller;

import com.dateTask.Task;

import java.util.List;

public interface IHistoryManager {
    void add(Task task);
    List<Task> getHistory();
}
