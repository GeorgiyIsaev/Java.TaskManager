package com.controller.controlException;

public class ManagerFileException extends ControlException {
    public ManagerFileException(Exception ex) {
        super("ManagerFileException: ошибка при работе с файлом! ["+ ex.getMessage() +"]");
    }

}
