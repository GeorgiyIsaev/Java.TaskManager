package com.Controller.ControlException;

public class ManagerFileException extends ControlException {
    public ManagerFileException() {
        super("ManagerFileException: Ошибка при работе с файлом!");
    }
    public ManagerFileException(Exception ex) {
        super("ManagerFileException: ошибка при работе с файлом! ["+ ex.getMessage() +"]");
    }

}
