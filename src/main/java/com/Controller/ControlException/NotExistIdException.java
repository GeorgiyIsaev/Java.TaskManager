package com.Controller.ControlException;

public class NotExistIdException extends ControlException{

    public NotExistIdException() {
        super("NotExistIdException: Нельзя изменить статус Эпика! Статус рассчитывается автоматически!");
    }
    public NotExistIdException(Integer IdTask) {
        super("NotExistIdException: Нельзя изменить статус Эпика с " + IdTask + "! Статус рассчитывается автоматически!");
    }

}
