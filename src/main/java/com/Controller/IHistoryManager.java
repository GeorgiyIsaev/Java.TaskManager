package com.Controller;

import com.DateTask.Task;

import java.util.List;

public interface IHistoryManager {
    void add(Task task);
    List<Task> getHistory();
}
