package com.consoleViewStrategy.utils;

import com.dateTask.Task;

public interface NotificationService {
    void sendTo(Notification type);
    void addTo(Notification type, Task task);
}
