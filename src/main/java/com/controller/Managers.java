package com.controller;

import com.controller.taskManager.InMemoryTaskManager;
import com.controller.taskManager.TaskManager;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
