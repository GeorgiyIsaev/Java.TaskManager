package com.Controller.ControlException;

public class NotEpicException extends ControlException{
    public NotEpicException() {
        super("NotEpicException: Задачи с указанным индексом не ЭПИК!");
    }
    public NotEpicException(Integer IdTask) {
        super("NotEpicException: Задача с ID: " + IdTask + " не является ЭПИКОМ, добавление подзадачи не возможно!");
    }
}
