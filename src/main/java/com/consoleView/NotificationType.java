package com.consoleView;

public enum NotificationType {

    DELETE_ALL("Все содержимое Task Manager удалено!"),
    SAVE("Содержимое Task Manager сохранено в файл!"),
    EXIT("Спасибо за работу!");

    private final String description;

    NotificationType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
