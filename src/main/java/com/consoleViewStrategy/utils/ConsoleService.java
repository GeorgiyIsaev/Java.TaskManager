package com.consoleViewStrategy.utils;

public class ConsoleService implements NotificationService{
    @Override
    public void sendTo(Notification type){
        System.out.println(type);
    }
}
