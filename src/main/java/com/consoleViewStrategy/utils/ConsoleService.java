package com.consoleViewStrategy.utils;

import com.dateTask.Task;

public class ConsoleService implements NotificationService{
    @Override
    public void sendTo(Notification type){
        System.out.println(type);
    }

    @Override
    public void addTo(Notification type, Task task) {
        System.out.println(type);
        System.out.println(TaskToString.CONSOLE_TITLE);
        System.out.println(TaskToString.transform(task));
    }
}
