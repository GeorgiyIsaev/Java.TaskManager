package com.consoleView;

public class NotificationService {

    public void sendToConsole(NotificationType type){
        System.out.println(type.getDescription());
    }
}
