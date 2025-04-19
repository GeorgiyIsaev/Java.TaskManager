package com.Controller.ControlException;

public class NotChangedEpicStatusException  extends ControlException{
    public NotChangedEpicStatusException() {
        super("NotChangedEpicStatusException: Задачи с указанным индексом не ЭПИК!");
    }
    public NotChangedEpicStatusException(Integer IdTask) {
        super("NotChangedEpicStatusException: Задача с ID: " + IdTask + " не является ЭПИКОМ, добавление подзадачи не возможно!");
    }
}

