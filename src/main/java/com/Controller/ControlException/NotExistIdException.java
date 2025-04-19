package com.Controller.ControlException;

public class NotExistIdException extends ControlException{

    public NotExistIdException() {

        super("NotExistIdException: Задачи с указанным индексом не существует!");
    }

    public NotExistIdException(Integer IdTask) {
        super("NotExistIdException: Задачи с индексом " + IdTask + " не существует!");
    }

}
