package com.controller.controlException;

public class ControlException extends RuntimeException{

    public ControlException() {
        super();
    }

    public ControlException(String message) {
        super(message);
    }
}
