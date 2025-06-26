package com.controller;

import com.controller.taskManager.FileBackedTasksManager;
import com.controller.taskManager.InMemoryTaskManager;
import com.controller.taskManager.TaskManager;

import java.io.File;
import java.nio.file.Path;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
//    public static TaskManager getFileBacked() {
//        String sep = File.separator;
//        Path filePath = Path.of("date"+sep+"MyCSV.csv");
//
//        return new FileBackedTasksManager(filePath);
//    }

    public static TaskManager getFileBacked(Path filePath) {
        return new FileBackedTasksManager(filePath);
    }


}
