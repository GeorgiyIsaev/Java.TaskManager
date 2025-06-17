package com.controller.controlException;

public class NotExistIdException extends ControlException{
    public NotExistIdException(Integer IdTask) {
        super("NotExistIdException: Задача с ID: " + IdTask + " не существует!");
    }

}
