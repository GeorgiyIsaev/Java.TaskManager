package com.controller.controlException;

public class NotExistIdException extends ControlException{

    public NotExistIdException() {
        super("NotExistIdException: Задача с указанным ID не существует!");
    }
    public NotExistIdException(Integer IdTask) {
        super("NotExistIdException: Задача с ID: " + IdTask + " не существует!");
    }

}
